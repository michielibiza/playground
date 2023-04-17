package nl.michiel.friends.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import nl.michiel.friends.domain.FriendsRepository

class FriendsViewModel(
    private val repository: FriendsRepository
) : ViewModel() {

    fun getFriends() = repository.getFriends().cachedIn(viewModelScope)
}
