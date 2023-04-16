package nl.michiel.friends.domain

import nl.michiel.friends.data.Friend
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class FriendRemoteDataSource {
    private val service = createService()

    suspend fun getFriends() = service.getFriends().results.map { it.toFriend() }

    private fun createService(): RandomUserService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create()
    }
}

private fun RandomUser.toFriend() =
    Friend(id.name, "${name.first} ${name.last}", picture.large)
