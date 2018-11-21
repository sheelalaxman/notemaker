package com.lmndevelopers.notemaker.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.lmndevelopers.notemaker.bean.DataStorage
import com.lmndevelopers.notemaker.bean.NoteStorage
import com.lmndevelopers.notemaker.presenter.presenterApi
import com.lmndevelopers.notemaker.view.MainActivity
import com.lmndevelopers.notemaker.view.RegisterApi
import com.lmndevelopers.notemaker.view.RegisterFrag

class model : presenterApi {
    lateinit var dbase : SQLiteDatabase
    lateinit var mactivity : MainActivity
   lateinit var api : RegisterApi


    constructor(v : MainActivity)
    {
      mactivity = v
      api = v as RegisterApi
      dbase = v.openOrCreateDatabase("notemaker", Context.MODE_PRIVATE, null)
          //var _id : Int,var name : String,var email: String,var mobile: Int,var password : String
      dbase.execSQL("create table if not exists details(_id integer primary key autoincrement,name varchar(30),email varchar(20),mobile varchar(10),password varchar(8))")
      dbase.execSQL("create table if not exists note(_id integer primary key autoincrement,title varchar(30),email varchar(20),notedata text)")
    }
    override fun insertDetails(data:DataStorage) {
      var cv=ContentValues()
      cv.put("name",data.name)
      cv.put("email",data.email)
      cv.put("mobile",data.mobile)
      cv.put("password",data.password)
      var status= dbase.insert("details",null,cv)
      when(status)
      {
          -1.toLong() -> {mactivity.registerResult("record failed to insert")}
          else -> { mactivity.registerResult("record inserted sucessfully")}
      }
    }

    override fun validate(email: String, password: String) : Boolean{


         api.email = email

         var c= dbase.query("details", arrayOf("_id"),"email=? and password=?", arrayOf(email,password),null,null,null)

       while(c.moveToNext())
       {
          if(c.getInt(0) > 0)
          {
              return  true
          }
       }
        
        return false
    }

    override fun insertNote(obj: NoteStorage) {
        var cv=ContentValues()
        cv.put("title",obj.title)
        cv.put("email",obj.email)
        cv.put("notedata",obj.datatext)
        var status= dbase.insert("note",null,cv)
        when(status)
        {
            -1.toLong() -> {mactivity.saveNote("note failed to save")}
            else -> { mactivity.saveNote("note inserted sucessfully")}
        }
    }

    override fun getNoteList(): MutableList<String> {
       var list = mutableListOf<String>()

       var c= dbase.query("note", arrayOf("title"),"email=?",arrayOf(api.email),null,null,null)

        while(c.moveToNext())
        {
            list.add(c.getString(0))
        }
        return list
    }
}