package com.sahuayo.productos.Registro

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import com.google.android.material.snackbar.Snackbar
import com.sahuayo.productos.R
import com.sahuayo.productos.SQlite.DataBaseManager
import com.sahuayo.productos.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register), View.OnClickListener {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    val db = DataBaseManager()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGuardar.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(p0: View?) {

        if(validarv()){
            if (activity?.let {
                    db.WriteDataBase(
                        it,
                        binding.edtClaveProducto.text.toString(),
                        binding.edtNombreProducto.text.toString(),
                        binding.spnEstado.selectedItem.toString()
                    )
                } == true) {
                Log.d(ContentValues.TAG, "Se inserto el dato")
                Snackbar.make(
                    binding.root,
                    "Registro insertado correctamente",
                    Snackbar.LENGTH_SHORT
                ).show()
                binding.edtClaveProducto.setText("")
                binding.edtNombreProducto.setText("")
                binding.spnEstado.setSelection(0)
            } else {
                Log.d(ContentValues.TAG, "NO Se inserto el dato")
                Snackbar.make(
                    binding.root,
                    "Registro NO insertado",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }else {

            Snackbar.make(
                binding.root,
                "Inserta los datos que faltan",
                Snackbar.LENGTH_SHORT
            ).show()

        }
    }

   fun validarv(): Boolean{
       var codigo = binding.edtClaveProducto.text.toString()
       var nombre = binding.edtNombreProducto.text.toString()
       var estado = binding.spnEstado.selectedItem.toString()

       var retorno = true

       if (codigo.isEmpty()){
           binding.edtClaveProducto.setError("El campo no puede estar vacio")
           retorno= false
       }
       if (nombre.isEmpty()){
           binding.edtNombreProducto.setError("El campo no puede estar vacio")
           retorno= false
       }
       if (estado.equals("Seleccione"))
           retorno = false
       return retorno
   }






}