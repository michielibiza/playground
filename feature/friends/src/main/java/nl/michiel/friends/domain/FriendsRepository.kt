package nl.michiel.friends.domain

import nl.michiel.friends.data.Friend

class FriendsRepository {
    private val dataSource = FriendRemoteDataSource()

    suspend fun getFriends(): List<Friend> {
        return dataSource.getFriends()
    }
}
