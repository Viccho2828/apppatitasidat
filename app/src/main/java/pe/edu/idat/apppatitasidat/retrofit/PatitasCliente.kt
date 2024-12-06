package pe.edu.idat.apppatitasidat.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PatitasCliente {

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl("https://app.juankuga.com/wspatitas/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: PatitasServicio by lazy {
        buildRetrofit().create(PatitasServicio::class.java)
    }
}