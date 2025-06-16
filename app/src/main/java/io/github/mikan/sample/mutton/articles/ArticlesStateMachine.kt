package io.github.mikan.sample.mutton.articles

import io.github.mikan.sample.mutton.domain.ArticleRepository
import io.github.ryunen344.mutton.EffectHandle
import io.github.ryunen344.mutton.Graph
import io.github.ryunen344.mutton.StateMachine
import javax.inject.Inject

class ArticlesStateMachine @Inject constructor(
    private val articleRepository: ArticleRepository,
) : StateMachine<ArticlesState, ArticlesAction, ArticlesEffect>(
    initialState = ArticlesState.Idle,
    graph = Graph {
        state<ArticlesState.Idle> {
            action<ArticlesAction.Load> { prev, action ->
                transition(ArticlesState.Loading)
            }
        }
        state<ArticlesState.Loading> {
            action<ArticlesAction.Success> { prev, action ->
                transition(ArticlesState.Success(articles = articles))
            }
            action<ArticlesAction.Failure> { prev, action ->
                transition(ArticlesState.Error("Failed to load articles"))
            }
        }
        state<ArticlesState.Success> {
        }
    },
    effectHandle = EffectHandle { effect, prev, next, dispatch ->
    }
)