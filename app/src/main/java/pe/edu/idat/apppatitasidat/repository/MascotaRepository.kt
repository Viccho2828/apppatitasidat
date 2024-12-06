package pe.edu.idat.apppatitasidat.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.edu.idat.apppatitasidat.retrofit.PatitasCliente
import pe.edu.idat.apppatitasidat.retrofit.request.RequestVoluntario
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseMascota
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseRegistro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MascotaRepository {
    var responseMascota =
        MutableLiveData<List<ResponseMascota>>()
    var responseRegistro =
        MutableLiveData<ResponseRegistro>()
    fun listarMascota()
        : MutableLiveData<List<ResponseMascota>>{
        val call: Call<List<ResponseMascota>> = PatitasCliente
            .retrofitService.listarMascota();
        call.enqueue(object : Callback<List<ResponseMascota>>{
            override fun onResponse(
                p0: Call<List<ResponseMascota>>,
                p1: Response<List<ResponseMascota>>
            ) {
                responseMascota.value = p1.body()
            }
            override fun onFailure(p0: Call<List<ResponseMascota>>, p1: Throwable) {
                Log.e("ErrorAPIMascota",
                    p1.message.toString())
            }

        })
        return  responseMascota
    }
    fun registrarVoluntario(idpersona: Int)
            : MutableLiveData<ResponseRegistro>{
        val call: Call<ResponseRegistro> = PatitasCliente
            .retrofitService.registrarVoluntario(
                RequestVoluntario(idpersona)
            )
        call.enqueue(object : Callback<ResponseRegistro>{
            override fun onResponse(p0: Call<ResponseRegistro>, p1: Response<ResponseRegistro>) {
                responseRegistro.value = p1.body()
            }
            override fun onFailure(p0: Call<ResponseRegistro>, p1: Throwable) {
                Log.e("ErrorAPIMascota",
                    p1.message.toString())
            }
        })
        return  responseRegistro
    }
}