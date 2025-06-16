package io.github.mikan.sample.mutton.domain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface DomainModule {
    @Binds
    fun bindArticleRepository(impl: DefaultArticleRepository): ArticleRepository
}