package com.example.socialnetworkteo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.socialnetworkteo.database.UserDatabase
import com.example.socialnetworkteo.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditUserViewModel(application: Application) : AndroidViewModel(application) {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val database = UserDatabase.getInstance(application).userDatabaseDao

    fun loadUserData(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _user.value = database.getId(id)
        }
    }

    fun updateUserInfo(user: User) {
        viewModelScope.launch(Dispatchers.Main) {
            database.update(user)
        }
    }
}