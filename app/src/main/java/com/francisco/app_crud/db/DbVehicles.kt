package com.francisco.app_crud.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.widget.Toast
import com.francisco.app_crud.model.Vehicle

class DbVehicles(private val context: Context) : DbHelper(context) {

    public fun insertVehicle(brand:String,model:String,velocity:String,km:String) : Long {
        val dbHelper = DbHelper(context)
        val  db = dbHelper.writableDatabase
        var id:Long = 0
        try {
            val values = ContentValues()
            values.put("brand",brand)
            values.put("model",model)
            values.put("velocity",velocity)
            values.put("km",km)
            id = db.insert("vehicle",null,values)
        }catch (e:Exception){
            Toast.makeText(context,"Error al insertar",Toast.LENGTH_SHORT).show()
        }finally {
            db.close()
        }
        return  id
    }

    fun getVehicles():ArrayList<Vehicle>{
        val dbHelper = DbHelper(context)
        val  db = dbHelper.writableDatabase
        var listVehicles = ArrayList<Vehicle>()
        var vehiclesTmp: Vehicle? = null
        var cursorVehicle: Cursor? = null

        cursorVehicle = db.rawQuery("SELECT * FROM vehicle",null)

        if(cursorVehicle.moveToFirst()){
            do{
                vehiclesTmp = Vehicle(cursorVehicle.getInt(0), cursorVehicle.getString(1), cursorVehicle.getString(2), cursorVehicle.getString(3), cursorVehicle.getString(4))
                listVehicles.add(vehiclesTmp)
            }while (cursorVehicle.moveToNext())
        }
        cursorVehicle.close()
        return  listVehicles
    }

    fun getVehicle(id:Int): Vehicle?{
        val dbHelper = DbHelper(context)
        val  db = dbHelper.writableDatabase

        var vehicle:Vehicle? = null

        var cursorVehicle: Cursor? = null
        cursorVehicle = db.rawQuery("SELECT * FROM vehicle WHERE id = $id LIMIT 1 ",null)

        if(cursorVehicle.moveToFirst()){
            vehicle = Vehicle(cursorVehicle.getInt(0), cursorVehicle.getString(1), cursorVehicle.getString(2), cursorVehicle.getString(3), cursorVehicle.getString(4))
        }
        cursorVehicle.close()
        return  vehicle
    }

    fun updateGame(id: Int, brand: String,model: String, velocity: String, km: String): Boolean{
        var banderaCorrecto = false

        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        try{
            db.execSQL("UPDATE vehicle SET brand = '$brand',model = '$model', velocity = '$velocity', km = '$km' WHERE id = $id")
            banderaCorrecto = true
        }catch(e: Exception){
            //Manejo de la excepción
            println(e)
        }finally {
            db.close()
        }
        return banderaCorrecto
    }

    fun deleteGame(id: Int): Boolean{
        var banderaCorrecto = false

        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        try{
            db.execSQL("DELETE FROM vehicle WHERE id = $id")
            banderaCorrecto = true
        }catch(e: Exception){

        }finally {
            db.close()
        }

        return banderaCorrecto
    }

}