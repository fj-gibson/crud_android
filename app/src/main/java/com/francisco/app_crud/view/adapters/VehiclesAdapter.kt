package com.francisco.app_crud.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.francisco.app_crud.R
import com.francisco.app_crud.databinding.ListElementBinding
import com.francisco.app_crud.model.Vehicle
import com.francisco.app_crud.view.activities.MainActivity

class VehiclesAdapter(private val context: Context,val vehicles: ArrayList<Vehicle>) : RecyclerView.Adapter<VehiclesAdapter.ViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    class ViewHolder(view: ListElementBinding): RecyclerView.ViewHolder(view.root){
        val tvBrand = view.tvBrand
        val tvVelocity = view.tvVelocity
        val tvKm = view.tvKm
        val image = view.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListElementBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvBrand.text = vehicles[position].brand
        holder.tvVelocity.text = vehicles[position].velocity
        holder.tvKm.text = vehicles[position].km
        if(vehicles[position].brand=="KIA"){
            holder.image.setImageResource(R.drawable.kia)
        }
        if(vehicles[position].brand=="Mazda"){
            holder.image.setImageResource(R.drawable.mazda)
        }
        if(vehicles[position].brand=="Tesla"){
            holder.image.setImageResource(R.drawable.tesla)
        }
        if(vehicles[position].brand=="Ford"){
            holder.image.setImageResource(R.drawable.ford_logo)
        }
        if(vehicles[position].brand=="Toyota"){
            holder.image.setImageResource(R.drawable.toyota)
        }
        if(vehicles[position].brand=="BMW"){
            holder.image.setImageResource(R.drawable.bwm_logo)
        }



        //Para los clicks de cada elemento viewholder

        holder.itemView.setOnClickListener {
            //Manejar el click
            if(context is MainActivity) context.selectedVehicle(vehicles[position])
        }

    }

    override fun getItemCount(): Int {
        return vehicles.size
    }
}