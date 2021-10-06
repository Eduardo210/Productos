package com.sahuayo.productos.SQlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import java.lang.reflect.Array

class DataBaseManager {

    fun WriteDataBase(context: Context, codigo: String, descripcion: String, estado: String, ): Boolean {
        val admin = AdminSQLiteOpenHelper(context, "administracion", null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("codigo", codigo)
        registro.put("descripcion", descripcion)
        registro.put("estado", estado)
        registro.put("eliminado", 0)
        return bd.insert("productos", null, registro) != null
        bd.close()
    }

    fun SearchCodigoDataBase(context: Context, codigo:String): ArrayList<String> {
        var datos = ArrayList<String>()
        val admin = AdminSQLiteOpenHelper(context, "administracion", null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("select descripcion,estado, eliminado from productos where codigo=${codigo} and eliminado = 0" , null)
        if (fila.moveToFirst()) {
            datos.add(fila.getString(0))
            datos.add(fila.getString(1))
            datos.add(fila.getString(2))
        }
        bd.close()
        return datos
    }

    fun SearchDescripcionDataBase(context: Context, descripcion: String): Cursor?{
        val admin = AdminSQLiteOpenHelper(context, "administracion", null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("select codigo,estatus,eliminado from productos where descripcion='${descripcion}' and eliminado = 0", null)
        bd.close()
        return fila
    }

    fun UpdateProductoDataBase(context: Context, codigo: String,descripcion: String, estado: String, eliminado: Int): Boolean{
        val admin = AdminSQLiteOpenHelper(context, "administracion", null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("descripcion", descripcion)
        registro.put("estado", estado)
        registro.put("eliminado",eliminado)
        val cant = bd.update("productos", registro, "codigo=${codigo}", null)
        bd.close()
        return cant == 1
    }

    fun DeleteLogicoDataBase(context: Context, codigo: String, eliminado: Int): Boolean{
        val admin = AdminSQLiteOpenHelper(context, "administracion", null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("eliminado",eliminado)
        val cant = bd.update("productos", registro, "codigo=${codigo}", null)
        bd.close()
        return cant == 1
    }

    fun DeleteDefinitivoDataBase(context: Context, codigo: String,):Boolean{
        val admin = AdminSQLiteOpenHelper(context, "administracion", null, 1)
        val bd = admin.writableDatabase
        val cant = bd.delete("productos", "codigo=${codigo}", null)
        bd.close()

        return cant == 1
    }




}