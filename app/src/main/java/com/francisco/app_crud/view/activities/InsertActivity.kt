package com.francisco.app_crud.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.francisco.app_crud.databinding.ActivityInsertBinding
import com.francisco.app_crud.db.DbVehicles
import com.francisco.app_crud.R

class InsertActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityInsertBinding
    var brand:String = "KIA"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
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
    }

    fun click(view: View) {
        val dbVehicles = DbVehicles(this)
        with(binding){
            when{
                tietModel.text.toString().isEmpty() -> {
                    tietModel.error = getString(R.string.field_request)
                    Toast.makeText(this@InsertActivity,R.string.please_request_fields,Toast.LENGTH_SHORT).show()
                }

                tietVelocity.text.toString().isEmpty() -> {
                    tietVelocity.error = getString(R.string.field_request)
                    Toast.makeText(this@InsertActivity,R.string.please_request_fields,Toast.LENGTH_SHORT).show()
                }

                tietKm.text.toString().isEmpty() -> {
                    tietKm.error = getString(R.string.field_request)
                    Toast.makeText(this@InsertActivity,R.string.please_request_fields,Toast.LENGTH_SHORT).show()
                }
                else ->{
                    val id = dbVehicles.insertVehicle(brand,tietModel.text.toString(),tietVelocity.text.toString(),tietKm.text.toString())
                    if(id>0){
                        Toast.makeText(this@InsertActivity,R.string.success_register,Toast.LENGTH_SHORT).show()
                        tietModel.setText("")
                        tietVelocity.setText("")
                        tietKm.setText("")
                        tietModel.requestFocus()
                    }else{
                        Toast.makeText(this@InsertActivity,R.string.error_register,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
    }
}