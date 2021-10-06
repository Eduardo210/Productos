package com.sahuayo.productos.Delete

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.sahuayo.productos.R
import com.sahuayo.productos.SQlite.DataBaseManager
import com.sahuayo.productos.databinding.FragmentDeleteBinding
import com.sahuayo.productos.databinding.FragmentUpdateBinding

class deleteFragment : Fragment(R.layout.fragment_delete), View.OnClickListener {

    private var _binding : FragmentDeleteBinding? = null
    private val binding get() = _binding!!

    val db = DataBaseManager()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDeleteBinding.inflate(inflater,container,false)
        return binding.root    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBuscar.setOnClickListener(this)
        binding.btnGuardar.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnBuscar.id -> {
                if (validarv()) {
                    if (activity?.let {
                            db.DeleteLogicoDataBase(
                                it,
                                binding.edtClaveProducto.text.toString(),1
                            )
                        } == true) {
                        Log.d(ContentValues.TAG, "Se inserto el dato")
                        Snackbar.make(
                            binding.root,
                            "Registro eliminado correctamente",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        binding.edtClaveProducto.setText("")
                    } else {
                        Log.d(ContentValues.TAG, "NO se encontro el dato")
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

            binding.btnGuardar.id -> {

                if (validarv()) {
                    if (activity?.let {
                            db.DeleteDefinitivoDataBase(
                                it,
                                binding.edtClaveProducto.text.toString(),
                            )
                        } == true) {
                        Log.d(ContentValues.TAG, "Se inserto el dato")
                        Snackbar.make(
                            binding.root,
                            "Registro eliminado correctamente",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        binding.edtClaveProducto.setText("")
                    } else {
                        Log.d(ContentValues.TAG, "NO se encontro el dato")
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

        var retorno = true

        if (codigo.isEmpty()) {
            binding.edtClaveProducto.setError("El campo no puede estar vacio")
            retorno = false
        }
        return retorno
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}