package nl.michiel.friends.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import nl.michiel.friends.data.Friend
import nl.michiel.friends.viewmodel.FriendsViewModel
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

private val badgeSize = 160.dp

@Composable
fun FriendsScreen(
    viewModel: FriendsViewModel = koinViewModel()
) {

    val friends = viewModel.getFriends().collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(badgeSize),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        when (val state = friends.loadState.refresh) {
            is LoadState.Error -> {
                banner { Text("error: ${state.error.message}") }
                Timber.w(state.error, "refresh error")
            }
            is LoadState.Loading -> banner { Loader("loading...") }
            else -> Unit
        }

        items(friends.itemCount) { index ->
            friends[index]?.let { FriendView(it) }
        }

        when (val state = friends.loadState.append) {
            is LoadState.Error -> {
                banner { Text("error: ${state.error.message}") }
                Timber.w(state.error, "load more error")
            }
            is LoadState.Loading -> banner { Loader("loading more...") }
            else -> Unit
        }
    }
}

private fun LazyGridScope.banner(content: @Composable LazyGridItemScope.() -> Unit) {
    item(span = { GridItemSpan(maxLineSpan) }, content = content)
}

@Composable
fun Loader(message: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircularProgressIndicator()
        Spacer(Modifier.width(8.dp))
        Text(message)
    }
}

@Composable
fun FriendView(friend: Friend) {
    Surface(shape = CircleShape, modifier = Modifier
        .aspectRatio(1f)
        .clipToBounds()) {
        AsyncImage(
            model = friend.photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Box(contentAlignment = BottomCenter) {
            Text(
                text = friend.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
                    .background(Color.White.copy(alpha = 0.3f)),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Preview
@Composable
fun PreviewFriend() {
    FriendView(
        friend = Friend(
            1,
            "John Doe",
            "https://www.himalmag.com/wp-content/uploads/2019/07/sample-profile-picture.png"
        )
    )
}
