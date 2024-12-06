package pe.edu.idat.apppatitasidat.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.apppatitasidat.R
import pe.edu.idat.apppatitasidat.databinding.FragmentMascotaBinding
import pe.edu.idat.apppatitasidat.view.adapter.MascotaAdapter
import pe.edu.idat.apppatitasidat.viewmodel.MascotaViewModel


class MascotaFragment : Fragment() {
    private var _binding: FragmentMascotaBinding? = null
    private val binding get() = _binding!!
    private lateinit var mascotaViewModel: MascotaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMascotaBinding.inflate(
            inflater, container, false
        )
        binding.rvmascota.layoutManager = LinearLayoutManager(
            requireActivity())
        mascotaViewModel = ViewModelProvider(requireActivity())
            .get(MascotaViewModel::class.java)
        listarMascotas()
        return binding.root
    }

    fun listarMascotas(){
        mascotaViewModel.listarMascotas().observe(
            viewLifecycleOwner, Observer {
                response ->
                binding.rvmascota.adapter =
                    MascotaAdapter(response)
            }
        )
    }


}