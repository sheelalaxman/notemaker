package com.lmndevelopers.notemaker.view

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.lmndevelopers.notemaker.R
import com.lmndevelopers.notemaker.bean.DataStorage
import com.lmndevelopers.notemaker.model.model
import kotlinx.android.synthetic.main.register_frag.*

class RegisterFrag : Fragment() {
    lateinit var listener : RegisterApi

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if (activity is RegisterApi) {
            listener = activity as RegisterApi
        } else {
            // Throw an error!
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var v= inflater!!.inflate(R.layout.register_frag,container,false)
        var model= listener.getModel()

        v.findViewById<Button>(R.id.save).setOnClickListener {

            if(name.text.isNotEmpty() && email.text.isNotEmpty() && mobile.text.isNotEmpty() && password.text.isNotEmpty()) {
                var data = DataStorage(0, name.text.toString(), email.text.toString(), mobile.text.toString(), password.text.toString())
                model.insertDetails(data)
            }
            else
            {
                Toast.makeText(activity, "Enter fields  ", Toast.LENGTH_LONG).show()
            }

        }






        return v!!
    }

}