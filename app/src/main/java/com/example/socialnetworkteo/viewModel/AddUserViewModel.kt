package com.example.socialnetworkteo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialnetworkteo.database.UserDatabase
import com.example.socialnetworkteo.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddUserViewModel(application: Application) : AndroidViewModel(application) {

    private val database = UserDatabase.getInstance(application).userDatabaseDao

    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.Main) {
            database.insert(user)
        }
    }
}