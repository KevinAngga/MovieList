package angga.com.movielist.network.service

import angga.com.movielist.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {

    private val httpClient : OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(30, TimeUnit.SECONDS)

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    fun retrofit() : Retrofit {
        val client = httpClient.build()
        return retrofitBuilder.client(client).build()
    }

    fun<S> createService(serviceClass: Class<S>) : S {
        val retrofit = retrofit()
        return retrofit.create(serviceClass)
    }
}