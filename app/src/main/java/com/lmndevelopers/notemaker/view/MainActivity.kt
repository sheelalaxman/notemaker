package com.lmndevelopers.notemaker.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import com.lmndevelopers.notemaker.R
import com.lmndevelopers.notemaker.model.model
import java.io.File

class MainActivity : RegisterApi,AppCompatActivity() {

    override var email: String
        get() = ""
        set(value) {}
    var modelobj : model? = null
    override fun registerResult(mssg: String) {


        if(mssg == "record inserted sucessfully")
        {
            var fragment = fragmentManager
            var tran =fragment.beginTransaction()
            tran.replace(R.id.frag1, LoginFrag())
            tran.commit()

        }
        Toast.makeText(this,mssg,Toast.LENGTH_LONG)
    }

    override fun saveNote(mssg: String) {

        if(mssg == "note inserted sucessfully")
        {
            var fragment = fragmentManager
            var tran =fragment.beginTransaction()
            tran.replace(R.id.frag1, Home())
            tran.commit()

        }
        Toast.makeText(this,mssg,Toast.LENGTH_LONG)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var f= File("storage/emulated/0/notemaker")

        if(!f.exists()) {
            f.mkdir() //directory is created;
        }
        modelobj = model(this)
        var fragment = fragmentManager
        var tran =fragment.beginTransaction()
        tran.add(R.id.frag1, LoginFrag())
        tran.commit()
    }
    override fun getModel() : model{
        return  modelobj!!
    }
}
