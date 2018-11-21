package com.lmndevelopers.notemaker.view

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.lmndevelopers.notemaker.R
import kotlinx.android.synthetic.main.login_frag.*

class LoginFrag: Fragment()
{

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
        var v = inflater!!.inflate(R.layout.login_frag,container,false)

        v.findViewById<Button>(R.id.login).setOnClickListener {

           var model= listener.getModel()


            if(lemail.text.isNotEmpty() && lpass.text.isNotEmpty()) {

                if (model.validate(lemail.text.toString(), lpass.text.toString()) == true) {
                    Toast.makeText(activity, "Login as ${lemail.text.toString()} ", Toast.LENGTH_LONG).show()

                    var fragment = fragmentManager
                    var tran =fragment.beginTransaction()
                    tran.replace(R.id.frag1, Home())
                    tran.commit()


                }
                else
                {
                    Toast.makeText(activity, "Invalid Details  ", Toast.LENGTH_LONG).show()
                }
            }
            else
            {
                Toast.makeText(activity, "Enter fields  ", Toast.LENGTH_LONG).show()
            }
        }
        v.findViewById<Button>(R.id.register).setOnClickListener {

            var fragment = fragmentManager
            var tran =fragment.beginTransaction()
            tran.replace(R.id.frag1, RegisterFrag())
            tran.commit()
        }
        return v!!
    }

}