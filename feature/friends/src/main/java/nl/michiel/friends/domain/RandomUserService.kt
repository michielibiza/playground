package nl.michiel.friends.domain

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserService {
    @GET("api?seed=aa")
    suspend fun getFriends(
        @Query("page") page: Int = 1,
        @Query("results") results: Int = 10,
    ): RandomUserResult
}

fun createService(): RandomUserService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    return retrofit.create()
}

data class RandomUserResult(
    val results: List<RandomUser>
)

data class RandomUser(
    val name: RandomUserName,
    val dob: RandomUserDob,
    val picture: RandomUserPicture,
)

data class RandomUserName(val first: String, val last: String)
data class RandomUserDob(val age: Int)
data class RandomUserPicture(val large: String)
