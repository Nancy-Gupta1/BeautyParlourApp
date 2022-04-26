package com.beautyparlour.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.beautyparlour.R
import com.beautyparlour.models.FeedbackData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_feedback.*
import kotlinx.android.synthetic.main.fragment_feedback.view.*


class FeedbackFragment : Fragment(), AdapterView.OnItemSelectedListener {
    var username = arrayOf("Nancy", "Neha", "Simran", "Purvi")
    var item: String = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feedback, container, false)
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.Spinner_Items,
                R.layout.color_spinner_layout
            ).also { adapter ->
//            ArrayAdapter.createFromResource(requireContext(), R.layout.color_spinner_layout, R.array.Spinner_Items)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                val spinner = view.findViewById<Spinner>(R.id.edtfeedbackname)
                spinner.adapter = adapter
            }
        view?.btnSumbit?.setOnClickListener {
            val spinner = view.findViewById<Spinner>(R.id.edtfeedbackname)
            val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("Feedback")
            val userkey = ref.push().key
            val userdata = FeedbackData(

                username = spinner.selectedItem.toString(),
                userfeedback = edtfeedbackMessage.text.toString(),
                rating = ratingbar.rating.toString()
            )

            ref.child(userkey!!).setValue(userdata).addOnCompleteListener {
                Toast.makeText(activity, "message successfully send", Toast.LENGTH_LONG).show()
                val rating = "Rating :: " + ratingbar.rating.toString()
                Toast.makeText(activity, "5" + "\n" + rating, Toast.LENGTH_LONG).show()
            }
        }
        //initView(view)
        return view
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //   Toast.makeText(activity,username[position],Toast.LENGTH_SHORT).show()
        item = parent?.getItemAtPosition(position).toString()
        //val text: String = parent?.getItemAtPosition(position).toString()

        Toast.makeText(activity, item, Toast.LENGTH_SHORT).show()


    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}