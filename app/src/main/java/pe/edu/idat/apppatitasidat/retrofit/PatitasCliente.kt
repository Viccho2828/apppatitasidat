package pe.edu.idat.apppatitasidat.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object PatitasCliente {

    private var okHttpClient = OkHttpClient.Builder()
        .addInterceptor(RetrofitInterceptor())
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .build()

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl("https://app.juankuga.com/wspatitas/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: PatitasServicio by lazy {
        buildRetrofit().create(PatitasServicio::class.java)
    }
}