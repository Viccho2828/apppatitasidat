package pe.edu.idat.apppatitasidat.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pe.edu.idat.apppatitasidat.R
import pe.edu.idat.apppatitasidat.databinding.ActivityRegistroBinding
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseRegistro
import pe.edu.idat.apppatitasidat.utilitarios.AppMensaje
import pe.edu.idat.apppatitasidat.utilitarios.TipoMensaje
import pe.edu.idat.apppatitasidat.viewmodel.AuthViewModel

class RegistroActivity : AppCompatActivity(),
    View.OnClickListener {
    private lateinit var binding : ActivityRegistroBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)
        binding.btnirlogin.setOnClickListener(this)
        binding.btnregistrarme.setOnClickListener(this)
        authViewModel.responseRegistro.observe(this,
            Observer {
                response ->
                obtenerResultadoRegistro(response)
            })
    }

    private fun obtenerResultadoRegistro(response: ResponseRegistro) {
        if(response.rpta){
            setearControles()
        }
        AppMensaje.mensaje(binding.root,
            response.mensaje, TipoMensaje.INFO)
        estadoBotones(true)
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.btnirlogin -> startActivity(
                Intent(applicationContext,
                    LoginActivity::class.java))
            R.id.btnregistrarme -> registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        estadoBotones(false)
        authViewModel.registroUsuario(
            binding.etnomusuario.text.toString(),
            binding.etapeusuario.text.toString(),
            binding.etemailusuario.text.toString(),
            binding.etcelusuario.text.toString(),
            binding.etusureg.text.toString(),
            binding.etpassreg.text.toString()
        )
    }
    private fun setearControles(){
        binding.etusureg.setText("")
        binding.etpassreg.setText("")
        binding.etrepassreg.setText("")
        binding.etnomusuario.setText("")
        binding.etapeusuario.setText("")
        binding.etcelusuario.setText("")
        binding.etemailusuario.setText("")

    }
    private fun estadoBotones(estado: Boolean){
        binding.btnirlogin.isEnabled = estado
        binding.btnregistrarme.isEnabled= estado
    }
}