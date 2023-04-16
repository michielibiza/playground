package nl.michiel.friends.view

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import nl.michiel.friends.viewmodel.FriendsViewModel

@Composable
fun FriendsScreen(viewModel: FriendsViewModel = FriendsViewModel()) {

    val friends = viewModel.getFriends().collectAsLazyPagingItems()

    LazyColumn {
        items(friends, { it.name }) {
            it?.let { friend -> Text(friend.name, Modifier.height(48.dp)) }
                ?: Text("null")
        }
    }
}
