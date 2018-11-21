package com.lmndevelopers.notemaker.view

import com.lmndevelopers.notemaker.model.model

interface RegisterApi {

    var email : String
    fun registerResult(s : String)
    fun saveNote(s : String)
    fun getModel() : model
}