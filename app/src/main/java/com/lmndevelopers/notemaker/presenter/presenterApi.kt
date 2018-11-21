package com.lmndevelopers.notemaker.presenter

import com.lmndevelopers.notemaker.bean.DataStorage
import com.lmndevelopers.notemaker.bean.NoteStorage

interface presenterApi {
    fun insertDetails(obj : DataStorage)
    fun validate(email : String,password : String) : Boolean
    fun insertNote(obj : NoteStorage)
    fun getNoteList() : MutableList<String>
}