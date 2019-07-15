package hr.ferit.brunozoric.taskie.di

import hr.ferit.brunozoric.taskie.BuildConfig
import hr.ferit.brunozoric.taskie.common.*
import hr.ferit.brunozoric.taskie.networking.TaskieApiService

import hr.ferit.brunozoric.taskie.prefs.provideSharedPrefs
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val prefs = provideSharedPrefs()


val networkingModule = module {

    //GSON
    single { GsonConverterFactory.create() as Converter.Factory }

    //INTERCEPTORS
    single(named(LOGGING_INTERCEPTOR)){
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    single(named(AUTH_INTERCEPTOR)){
        Interceptor{
            val request = it.request().newBuilder().addHeader(KEY_AUTHORIZATION, prefs.getUserToken())
                .build()
            it.proceed(request)
        }
    }

    //OKHTTPCLIENT
    single {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG){
                addInterceptor(get(named(LOGGING_INTERCEPTOR)))
                addInterceptor(get(named(AUTH_INTERCEPTOR)))
            }

        }.build()
    }

    //RETROFIT
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(get())
            .build()
    }

    single { get<Retrofit>().create(TaskieApiService::class.java) }
}

