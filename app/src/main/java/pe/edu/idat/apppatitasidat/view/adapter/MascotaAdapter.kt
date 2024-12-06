package pe.edu.idat.apppatitasidat.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.idat.apppatitasidat.databinding.ItemMascotaBinding
import pe.edu.idat.apppatitasidat.retrofit.response.ResponseMascota

class MascotaAdapter(
    private var listaMascota: List<ResponseMascota>)
    : RecyclerView.Adapter<MascotaAdapter.ViewHolder>()
{
        inner class ViewHolder(val binding:
                               ItemMascotaBinding)
            :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int):
            MascotaAdapter.ViewHolder {
        val binding = ItemMascotaBinding
            .inflate(LayoutInflater.from(parent.context),
                parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder:
                                  MascotaAdapter.ViewHolder, position: Int) {
        with(holder){
            with(listaMascota[position]){
                binding.tvnommascota.text = nommascota
                binding.tvlugar.text = lugar
                binding.tvcontacto.text = contacto
                binding.tvfechaperdida.text = fechaperdida
                Glide.with(holder.itemView.context)
                    .load(urlimagen)
                    .into(binding.ivmascota)

            }
        }
    }

    override fun getItemCount() = listaMascota.size
}