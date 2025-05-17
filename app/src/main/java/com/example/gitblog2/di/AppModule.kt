package com.example.gitblog2.di

import com.example.gitblog2.data.local.RoomFactory
import com.example.gitblog2.data.remote.BlogRemoteDataSource
import com.example.gitblog2.data.remote.BlogRemoteDataSourceImpl
import com.example.gitblog2.data.remote.HttpClientFactory
import com.example.gitblog2.data.repository.BlogRepositoryImpl
import com.example.gitblog2.domain.repository.BlogRepository
import com.example.gitblog2.presentation.viewModels.BlogDetailViewModel
import com.example.gitblog2.presentation.viewModels.BlogListViewModel
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val koinModule = module {
    single { HttpClientFactory.create(OkHttp.create()) }
    singleOf(::BlogRemoteDataSourceImpl) { bind<BlogRemoteDataSource>() }
    singleOf(::BlogRepositoryImpl) { bind<BlogRepository>() }
    single { RoomFactory.create(get()).dao() }
    viewModelOf(::BlogListViewModel)
    viewModelOf(::BlogDetailViewModel)

}
