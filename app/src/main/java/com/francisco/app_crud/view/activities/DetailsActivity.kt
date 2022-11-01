package com.francisco.app_crud.view.activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.francisco.app_crud.R
import com.francisco.app_crud.databinding.ActivityDetailsBinding
import com.francisco.app_crud.db.DbVehicles
import com.francisco.app_crud.model.Vehicle

class DetailsActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityDetailsBinding
    private  lateinit var dbVehicles : DbVehicles
    var vehicle:Vehicle? = null
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                txtBrand.setText(it.brand)
                tietModel.inputType = InputType.TYPE_NULL
                tietVelocity.inputType = InputType.TYPE_NULL
                tietKm.inputType = InputType.TYPE_NULL
            }
        }
    }

    fun click(view: View) {
        when(view.id){
            R.id.btnEdit ->{
                val intent = Intent(this,EditActivity::class.java)
                intent.putExtra("ID",id)
                startActivity(intent)
                finish()
            }
            R.id.btnDelete ->{
                AlertDialog.Builder(this)
                    .setTitle(R.string.confirmation)
                    .setMessage("¿Realmente deseas eliminar el juego ${vehicle?.model}?")
                    .setPositiveButton(R.string.accept, DialogInterface.OnClickListener { dialog, which ->
                        if(dbVehicles.deleteGame(id)){
                            Toast.makeText(this@DetailsActivity, R.string.destroy_success, Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@DetailsActivity, MainActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this@DetailsActivity, R.string.destroy_error, Toast.LENGTH_SHORT).show()
                        }
                    })
                    .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    })
                    .show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
    }
}