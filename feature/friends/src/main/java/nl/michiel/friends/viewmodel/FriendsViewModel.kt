package nl.michiel.friends.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import nl.michiel.friends.domain.FriendsRepository

class FriendsViewModel : ViewModel() {
    private val repository = FriendsRepository()

    fun getFriends() = repository.getFriends().cachedIn(viewModelScope)
}
