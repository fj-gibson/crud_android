package com.francisco.app_crud.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.francisco.app_crud.R
import com.francisco.app_crud.databinding.ActivityMainBinding
import com.francisco.app_crud.db.DbHelper
import com.francisco.app_crud.db.DbVehicles
import com.francisco.app_crud.model.Vehicle
import com.francisco.app_crud.view.adapters.VehiclesAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listVehicles: ArrayList<Vehicle>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dbHelper = DbHelper(this)
        val db = dbHelper.writableDatabase
        if(db!=null){
            Toast.makeText(this,R.string.success_db,Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,R.string.error_db,Toast.LENGTH_SHORT).show()
        }
        val dbVehicles = DbVehicles(this)
        listVehicles = dbVehicles.getVehicles()

        val vehiclesAdapter = VehiclesAdapter(this,listVehicles)
        binding.rvCar.layoutManager = LinearLayoutManager(this)
        binding.rvCar.adapter = vehiclesAdapter

    }

    fun click(view: View) {
        startActivity(Intent(this,InsertActivity::class.java))
        finish()
    }

    fun selectedVehicle(vehicle: Vehicle) {
        //Manejamos el click del elemento en el recycler view
        val intent = Intent(this,DetailsActivity::class.java)
        intent.putExtra("ID",vehicle.id)
        startActivity(intent)
        finish()
    }
}