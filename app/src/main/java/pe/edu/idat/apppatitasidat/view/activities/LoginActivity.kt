package pe.edu.idat.apppatitasidat.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pe.edu.idat.apppatitasidat.R
import pe.edu.idat.apppatitasidat.databinding.ActivityLoginBinding
import pe.edu.idat.apppatitasidat.db.entity.PersonaEntity
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseLogin
import pe.edu.idat.apppatitasidat.utilitarios.AppMensaje
import pe.edu.idat.apppatitasidat.utilitarios.TipoMensaje
import pe.edu.idat.apppatitasidat.viewmodel.AuthViewModel
import pe.edu.idat.apppatitasidat.viewmodel.PersonaViewModel

class LoginActivity : AppCompatActivity(),
    View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var personaViewModel: PersonaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(
            layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)
        //Persistencia con Room en SQLite
        personaViewModel = ViewModelProvider(this)
            .get(PersonaViewModel::class.java)
        personaViewModel.eliminartodo()
        //Persistencia con Room en SQLite
        binding.btnlogin.setOnClickListener(this)
        binding.btnregistrar.setOnClickListener(this)
        authViewModel.responseLogin.observe(
            this, Observer {
                response -> obtenerDatosLogin(response)
            }
        )

    }
    private fun obtenerDatosLogin(
        responseLogin: ResponseLogin) {
        if(responseLogin.rpta){
            //Persistencia de datos con Room en SQLite
            val nuevaPersona = PersonaEntity(
                responseLogin.idpersona.toInt(),
                responseLogin.nombres,
                responseLogin.apellidos,
                responseLogin.email,
                responseLogin.celular,
                responseLogin.usuario,
                responseLogin.password,
                responseLogin.esvoluntario)
            personaViewModel.insertar(nuevaPersona)
            //Persistencia de datos con Room en SQLite
            startActivity(Intent(applicationContext,
                HomeActivity::class.java))
            finish()
        }else{
            AppMensaje.mensaje(binding.root,
                responseLogin.mensaje,
                TipoMensaje.ERROR)
        }
        binding.btnlogin.isEnabled = true
        binding.btnregistrar.isEnabled = true
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.btnlogin -> autenticarUsuario()
            R.id.btnregistrar -> startActivity(
                Intent(applicationContext,
                    RegistroActivity::class.java))
        }
    }

    private fun autenticarUsuario() {
        binding.btnregistrar.isEnabled = false
        binding.btnlogin.isEnabled = false
        if(validarUsuarioPassword()){
            authViewModel.autenticarUsuario(
                binding.etusuario.text.toString(),
                binding.etpassword.text.toString())
        }else{
            AppMensaje.mensaje(binding.root,
                "Ingrese usuario y/o password",
                TipoMensaje.ERROR)
            binding.btnregistrar.isEnabled = true
            binding.btnlogin.isEnabled = true
        }

    }
    private fun validarUsuarioPassword():Boolean{
        var okValidacion = true
        if(binding.etusuario.text.toString().trim().isEmpty()){
            binding.etusuario.isFocusableInTouchMode = true
            binding.etusuario.requestFocus()
            okValidacion = false
        }else if(binding.etpassword.text.toString().trim().isEmpty()){
            binding.etpassword.isFocusableInTouchMode = true
            binding.etpassword.requestFocus()
            okValidacion = false
        }
        return okValidacion
    }
}