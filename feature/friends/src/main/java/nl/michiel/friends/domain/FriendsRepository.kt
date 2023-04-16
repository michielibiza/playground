package nl.michiel.friends.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig

class FriendsRepository {
    private val service = createService()
    private val pager = Pager(PagingConfig(10)) {
        FriendPagingSource(service)
    }

    fun getFriends() = pager.flow
}
