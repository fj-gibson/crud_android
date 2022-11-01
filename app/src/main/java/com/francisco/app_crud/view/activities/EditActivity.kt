package com.francisco.app_crud.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.francisco.app_crud.R
import com.francisco.app_crud.databinding.ActivityEditBinding
import com.francisco.app_crud.db.DbVehicles
import com.francisco.app_crud.model.Vehicle

class EditActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityEditBinding
    private  lateinit var dbVehicles : DbVehicles
    var vehicle: Vehicle? = null
    var id = 0
    var brand:String = "KIA"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val  spinner = binding.spinner
        //Esto lo deje Hard ya que las marcas siempre son iguales
        val values = listOf("KIA","Mazda","Toyota","BMW","Tesla","Ford")
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,values)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                brand = values[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        val bundle = intent.extras
        if(bundle!=null){
            id = bundle.getInt("ID",0)
        }

        dbVehicles = DbVehicles(this)
        vehicle = dbVehicles.getVehicle(id)
        vehicle?.let {
            with(binding){
                tietModel.setText(it.model)
                tietVelocity.setText(it.velocity)
                tietKm.setText(it.km)
                brand = it.brand
            }
        }
    }

    fun click(view: View) {
        with(binding){
            when{
                tietModel.text.toString().isEmpty() -> {
                    tietModel.error = getString(R.string.field_request)
                    Toast.makeText(this@EditActivity,R.string.please_request_fields,Toast.LENGTH_SHORT).show()
                }

                tietVelocity.text.toString().isEmpty() -> {
                    tietVelocity.error = getString(R.string.field_request)
                    Toast.makeText(this@EditActivity,R.string.please_request_fields,Toast.LENGTH_SHORT).show()
                }

                tietKm.text.toString().isEmpty() -> {
                    tietKm.error = getString(R.string.field_request)
                    Toast.makeText(this@EditActivity,R.string.please_request_fields,Toast.LENGTH_SHORT).show()
                }
                else -> {
                    if(dbVehicles.updateGame(id,brand,tietModel.text.toString(),tietVelocity.text.toString(),tietKm.text.toString())){
                        Toast.makeText(this@EditActivity, R.string.updated, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@EditActivity, DetailsActivity::class.java)
                        intent.putExtra("ID", id)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this@EditActivity, R.string.error_register_updated, Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,DetailsActivity::class.java))
    }
}