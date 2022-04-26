package com.beautyparlour.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.beautyparlour.R


class LogoutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_logout, container, false)
//        val builder = AlertDialog.Builder(requireActivity())
//        builder.setPositiveButton("Yes"){dialogInterface, which ->
//            Toast.makeText(context,"clicked yes",Toast.LENGTH_LONG).show()
//        }
//
//        builder.setNegativeButton("No"){dialogInterface, which ->
//            Toast.makeText(context,"clicked No",Toast.LENGTH_LONG).show()
//        }
//
//        // Create the AlertDialog
//        val alertDialog: AlertDialog = builder.create()
//        // Set other dialog properties
//        alertDialog.setCancelable(false)
//        alertDialog.show()

        return view
    }


}