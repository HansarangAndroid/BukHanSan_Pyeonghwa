package com.example.model

import com.example.model.github.GithubApiService
import com.example.model.home.SearchUserApiService
import com.example.model.login.LoginApiService
import com.example.model.signup.SignUpApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GithubRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SoptRetrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    private val okHttpInterceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

    private val baseClient = OkHttpClient.Builder()
        .addInterceptor(okHttpInterceptor)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .build()

            chain.proceed(request)
        }
        return baseClient.newBuilder().addNetworkInterceptor(interceptor).build()
    }


    @Provides
    @Singleton
    @GithubRetrofit
    fun provideGitHubRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.GITHUB_API_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    @SoptRetrofit
    fun provideSoptRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SOPT_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGitHubApiService(@GithubRetrofit retrofit: Retrofit): SearchUserApiService =
        retrofit.create(SearchUserApiService::class.java)

    @Provides
    @Singleton
    fun provideGithubRepoApiService(@GithubRetrofit retrofit: Retrofit): GithubApiService =
        retrofit.create(GithubApiService::class.java)

    @Provides
    @Singleton
    fun provideSignUpApiService(@SoptRetrofit retrofit: Retrofit): SignUpApiService =
        retrofit.create(SignUpApiService::class.java)

    @Provides
    @Singleton
    fun provideLoginApiService(@SoptRetrofit retrofit: Retrofit): LoginApiService =
        retrofit.create(LoginApiService::class.java)

}