package com.lmndevelopers.notemaker.view

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import com.lmndevelopers.notemaker.R
import kotlinx.android.synthetic.main.home_frag.view.*


class Home : Fragment() {
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
        var v = inflater!!.inflate(R.layout.home_frag,container,false)

        var list = listener.getModel().getNoteList()

        if(list.isEmpty())
            list.add("simple")
        v.lview.adapter = ArrayAdapter<String>(activity,android.R.layout.simple_list_item_1,list)
        v.lview.deferNotifyDataSetChanged()

        v.fab.setOnClickListener {
            //Toast.makeText(activity,"fab clicked",Toast.LENGTH_LONG).show()
           Snackbar.make(it,"fab clicked",Snackbar.LENGTH_LONG).setAction("Action",null).show()

          var frag=fragmentManager
          var tran = frag.beginTransaction()
          tran.replace(R.id.frag1,MakeFrag())
           tran.commit()
        }

        v.lview.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(activity,"${ list.get(i)}",Toast.LENGTH_LONG).show()
        }


        return v!!
    }
}