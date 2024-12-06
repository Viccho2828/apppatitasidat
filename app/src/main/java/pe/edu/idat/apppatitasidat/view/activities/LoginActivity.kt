package pe.edu.idat.apppatitasidat.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pe.edu.idat.apppatitasidat.R
import pe.edu.idat.apppatitasidat.databinding.ActivityLoginBinding
import pe.edu.idat.apppatitasidat.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity(),
    View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(
            layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)
        binding.btnlogin.setOnClickListener(this)
        binding.btnregistrar.setOnClickListener(this)
        authViewModel.autenticarUsuario(
            binding.etusuario.text.toString(),
            binding.etpassword.text.toString())
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