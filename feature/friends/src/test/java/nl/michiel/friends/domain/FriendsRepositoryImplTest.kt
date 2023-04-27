package nl.michiel.friends.domain

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import nl.michiel.friends.data.Friend
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class FriendsRepositoryImplTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val user = RandomUser(
        RandomUserName("john", "doe"),
        RandomUserDob(33),
        RandomUserPicture("picture url")
    )

    private val friend = Friend(0, "john", "picture url")

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getFriends() = runTest {
        val service = mock<RandomUserService>()
        whenever(service.getFriends(anyInt(), anyInt())).doReturn(RandomUserResult(List(10) { user }))

        val repository = FriendsRepositoryImpl(service)
        val items: Flow<PagingData<Friend>> = repository.getFriends()

        val itemsSnapshot = items.asSnapshot(this) {
            scrollTo(23)
        }
        assert(itemsSnapshot.size > 23) { "enough results" }
        assertEquals("return mocked user's name", friend.name, itemsSnapshot[0].name)
        assertEquals("return mocked user's photo", friend.photoUrl, itemsSnapshot[0].photoUrl)
    }
}
