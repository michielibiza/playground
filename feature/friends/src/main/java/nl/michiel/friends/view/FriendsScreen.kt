package nl.michiel.friends.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import nl.michiel.friends.data.Friend
import nl.michiel.friends.viewmodel.FriendsViewModel
import timber.log.Timber

@Composable
fun FriendsScreen(viewModel: FriendsViewModel = FriendsViewModel()) {

    val friends = viewModel.getFriends().collectAsLazyPagingItems()

    LazyColumn {
        when (val state = friends.loadState.refresh) {
            is LoadState.Error -> {
                item { Text("error: ${state.error.message}") }
                Timber.w(state.error, "refresh error")
            }
            is LoadState.Loading -> item { Loader("loading...") }
            else -> Unit
        }

        items(friends, { it.name }) { friend ->
            friend?.let { FriendView(it) }
        }

        when (val state = friends.loadState.append) {
            is LoadState.Error -> {
                item { Text("error: ${state.error.message}") }
                Timber.w(state.error, "load more error")
            }
            is LoadState.Loading -> item { Loader("loading more...") }
            else -> Unit
        }
    }
}

@Composable
fun Loader(message: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(message)
        CircularProgressIndicator()
    }
}

@Composable
fun FriendView(friend: Friend) {
    Text(friend.name, Modifier.height(48.dp))
}
