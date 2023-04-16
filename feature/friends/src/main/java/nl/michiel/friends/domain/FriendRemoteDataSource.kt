package nl.michiel.friends.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import nl.michiel.friends.data.Friend
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class FriendPagingSource(private val service: RandomUserService) : PagingSource<Int, Friend>() {
    override fun getRefreshKey(state: PagingState<Int, Friend>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.let {
                it.prevKey?.plus(1) ?: it.nextKey?.minus(1)
            }
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Friend> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val response = service.getFriends(page = page, pageSize)

            LoadResult.Page(
                data = response.results.mapIndexed { index, randomUser -> randomUser.toFriend(page * pageSize + index) },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

fun createService(): RandomUserService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    return retrofit.create()
}

private fun RandomUser.toFriend(id: Int) =
    Friend(id, "${name.first} ${name.last}", picture.large)
