package com.sahuayo.productos.Update

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.sahuayo.productos.R
import com.sahuayo.productos.SQlite.DataBaseManager
import com.sahuayo.productos.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment(R.layout.fragment_update), View.OnClickListener {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    val db = DataBaseManager()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBuscar.setOnClickListener(this)
        binding.btnGuardar.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnBuscar.id -> {
                val result = context?.let {
                    db.SearchCodigoDataBase(
                        it,
                        binding.edtClaveProducto.text.toString()
                    )
                }
                if (result != null) {
                    if (result.isEmpty()) {
                        Snackbar.make(
                            binding.root,
                            "No existe un registro coin este ID",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    } else {
                        binding.edtClaveProducto.setEnabled(false)
                        binding.edtNombreProducto.setEnabled(true)
                        binding.edtNombreProducto.setText(result[0])
                        if (result[1].equals("Activo")) {
                            binding.spnEstado.setSelection(1)
                        } else {
                            binding.spnEstado.setSelection(2)
                        }
                    }
                }
            }

            binding.btnGuardar.id -> {

                if (validarv()) {
                    if (activity?.let {
                            db.UpdateProductoDataBase(
                                it,
                                binding.edtClaveProducto.text.toString(),
                                binding.edtNombreProducto.text.toString(),
                                binding.spnEstado.selectedItem.toString(),
                                0
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
                        binding.edtClaveProducto.setEnabled(true)
                        binding.edtNombreProducto.setEnabled(false)
                    } else {
                        Log.d(ContentValues.TAG, "NO Se inserto el dato")
                        Snackbar.make(
                            binding.root,
                            "Registro NO insertado",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                } else {

                    Snackbar.make(
                        binding.root,
                        "Inserta los datos que faltan",
                        Snackbar.LENGTH_SHORT
                    ).show()

                }

            }
        }
    }

    fun validarv(): Boolean {
        var codigo = binding.edtClaveProducto.text.toString()
        var nombre = binding.edtNombreProducto.text.toString()
        var estado = binding.spnEstado.selectedItem.toString()

        var retorno = true

        if (codigo.isEmpty()) {
            binding.edtClaveProducto.setError("El campo no puede estar vacio")
            retorno = false
        }
        if (nombre.isEmpty()) {
            binding.edtNombreProducto.setError("El campo no puede estar vacio")
            retorno = false
        }
        if (estado.equals("Seleccione"))
            retorno = false
        return retorno
    }


}