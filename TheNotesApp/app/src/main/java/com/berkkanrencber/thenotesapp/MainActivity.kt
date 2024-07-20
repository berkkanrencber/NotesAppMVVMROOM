package com.berkkanrencber.thenotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.berkkanrencber.thenotesapp.database.NoteDatabase
import com.berkkanrencber.thenotesapp.repository.NoteRepository
import com.berkkanrencber.thenotesapp.viewmodel.NoteViewModel
import com.berkkanrencber.thenotesapp.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
    }

    private fun setupViewModel(){
        val noteRepository = NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory = NoteViewModelFactory(application,noteRepository)
        noteViewModel = ViewModelProvider(this,viewModelProviderFactory)[NoteViewModel::class.java]
    }
}