package io.github.mikan.sample.mutton.domain

import io.github.mikan.sample.mutton.network.model.Comment
import io.github.mikan.sample.mutton.network.model.Item
import io.github.mikan.sample.mutton.network.remote.UserApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DefaultArticleRepository @Inject constructor(
    private val userApi: UserApi,
) : ArticleRepository {
    private var items = emptyList<Item>()
    override suspend fun getArticles(): List<Item> {
        if (items.isNotEmpty()) return items
        items = userApi.getItems(perPage = 10).body()
        return items
    }

    override suspend fun getArticle(itemId: String): Item? {
        val item = userApi.getItem(itemId).body()
        return item
    }

    override suspend fun addLike(itemId: String) {
        userApi.createItemLike(itemId)
    }

    override suspend fun removeLike(itemId: String) {
        userApi.deleteItemLike(itemId)
    }

    override suspend fun isItemStock(itemId: String): Boolean {
        return userApi.isItemStock(itemId).success
    }

    override suspend fun isItemLiked(itemId: String): Boolean {
        return userApi.isItemLike(itemId).success
    }

    override suspend fun getComments(itemId: String): List<Comment> {
        val comments = userApi.getItemComments(itemId).body()
        return comments
    }
}