package io.github.mikan.sample.mutton.articles

import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import io.github.mikan.sample.mutton.network.model.Item
import io.github.ryunen344.mutton.Action
import io.github.ryunen344.mutton.Effect
import io.github.ryunen344.mutton.State
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime

sealed class ArticlesState : State() {
    data object Idle : ArticlesState()
    data object Loading : ArticlesState()
    data class Error(val message: String) : ArticlesState()
    data class Success(
        val articles: List<Article>,
        val isLoading: Boolean = false,
    ) : ArticlesState()
}

sealed class ArticlesAction : Action() {
    data object Load : ArticlesAction()
    data object Success : ArticlesAction()
    data object Failure : ArticlesAction()
}

sealed class ArticlesUiAction : ArticlesAction() {
    data class Click(val itemId: String) : ArticlesUiAction()
    data class AddLike(val itemId: String) : ArticlesUiAction()
    data class RemoveLike(val itemId: String) : ArticlesUiAction()
}

sealed class ArticlesEffect : Effect() {
    data class NavigateToDetail(val itemId: String) : ArticlesEffect()
}

data class Article(
    val id: String,
    val author: Author,
    val title: String,
    val likesCount: Int,
    val tags: List<String>,
    val createdAt: String,
    val updatedAt: String,
    val isLike: Boolean,
)

data class Author(
    val photoUrl: String,
    val name: String?,
    val group: String?,
)

fun Item.toArticle(isLike: Boolean): Article {
    return Article(
        id = id,
        author = Author(
            photoUrl = user.profileImageUrl,
            name = user.name,
            group = user.organization,
        ),
        title = title,
        likesCount = likesCount,
        tags = tags.map { it.name },
        createdAt = createdAt.formatDate(),
        updatedAt = updatedAt.formatDate(),
        isLike = isLike,
    )
}

@OptIn(FormatStringsInDatetimeFormats::class)
private fun String.formatDate(): String {
    val instant = Instant.parse(this)
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val formatter = LocalDateTime.Format {
        byUnicodePattern("yyyy/MM/dd")
    }
    return formatter.format(localDateTime)
}

class ArticleParameterProvider : CollectionPreviewParameterProvider<Article>(
    listOf(
        Article(
            id = "1",
            author = Author(
                photoUrl = "",
                name = null,
                group = null,
            ),
            title = "タイトル",
            likesCount = 0,
            tags = emptyList(),
            createdAt = "2025/01/01",
            updatedAt = "2025/01/10",
            isLike = false,
        ),
        Article(
            id = "1",
            author = Author(
                photoUrl = "",
                name = "山田太郎",
                group = "fuga株式会社",
            ),
            title = "タイトル",
            likesCount = 100,
            tags = listOf(
                "android",
                "compose"
            ),
            createdAt = "2025/01/01",
            updatedAt = "2025/01/10",
            isLike = true,
        ),
        Article(
            id = "1",
            author = Author(
                photoUrl = "",
                name = "山田太郎",
                group = "fuga株式会社",
            ),
            title = List(100) { "hoge" }.joinToString(""),
            likesCount = 10000,
            tags = listOf(
                "android",
                "compose",
                "vscode",
                "mcp",
                "hilt",
                "DevOps",
            ),
            createdAt = "2025/01/01",
            updatedAt = "2025/01/10",
            isLike = true,
        ),
    ),
)
