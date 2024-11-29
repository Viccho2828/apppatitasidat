package pe.edu.idat.apppatitasidat.retrofit.response

import android.provider.ContactsContract.CommonDataKinds.Email

data class ResponseLogin(
    var rpta: Boolean,
    var idpersona: String,
    var nombres: String,
    var apellidos: String,
    var email: String,
    var celular: String,
    var usuario: String,
    var password : String,
    var esvoluntario: String,
    var mensaje: String
)
