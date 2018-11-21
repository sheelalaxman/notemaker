package com.lmndevelopers.notemaker.view

import android.app.ActionBar
import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toolbar
import com.lmndevelopers.notemaker.R
import com.lmndevelopers.notemaker.bean.NoteStorage
import com.lmndevelopers.notemaker.model.model
import kotlinx.android.synthetic.main.make.*
import kotlinx.android.synthetic.main.make.view.*

class MakeFrag: Fragment() {

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

        var v=inflater!!.inflate(R.layout.make,container,false)

        activity.setActionBar(v.tbar)

       v.findViewById<Button>(R.id.save).setOnClickListener {
           var note = NoteStorage(0, v.title.text.toString(),v.tview.text.toString(),listener.email)
           var model = listener.getModel()
           model.insertNote(note)
           Snackbar.make(v,"titlle : ${ v.title.text} \n text : ${ v.tview.text}",Snackbar.LENGTH_LONG).show()
        }





        return v
    }

}