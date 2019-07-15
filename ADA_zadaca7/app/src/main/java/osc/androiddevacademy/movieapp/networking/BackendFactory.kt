package osc.androiddevacademy.movieapp.networking

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import osc.androiddevacademy.movieapp.common.API_KEY
import osc.androiddevacademy.movieapp.common.AUTHENTICATION
import osc.androiddevacademy.movieapp.common.BASE_URL
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractor
import osc.androiddevacademy.movieapp.networking.interactors.MovieInteractorImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackendFactory {

    private var retrofit: Retrofit? = null
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private fun provideAuthenticationInterceptor() = Interceptor {
        var request = it.request()
        val url = request.url().newBuilder().addQueryParameter(AUTHENTICATION, API_KEY).build()
        request = request.newBuilder().url(url).build()
        it.proceed(request)
    }

    private val httpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(provideAuthenticationInterceptor())
            .build()

    private val client: Retrofit? = if (retrofit == null) createRetrofit() else retrofit

    private fun createRetrofit(): Retrofit? {
        retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    private fun getService(): MovieApiService = this.client!!.create(MovieApiService::class.java)

    fun getMovieInteractor(): MovieInteractor = MovieInteractorImpl(getService())
}