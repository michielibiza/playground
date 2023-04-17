package nl.michiel.friends.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import nl.michiel.friends.data.Friend

interface FriendsRepository {
    fun getFriends(): Flow<PagingData<Friend>>
}

class FriendsRepositoryImpl(private val service: RandomUserService) : FriendsRepository {

    private val pager = Pager(PagingConfig(10)) {
        FriendPagingSource(service)
    }

    override fun getFriends() = pager.flow
}
