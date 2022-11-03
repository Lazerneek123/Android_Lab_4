package com.example.socialnetworkteo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.socialnetworkteo.database.UserDatabase
import com.example.socialnetworkteo.models.User
import com.example.socialnetworkteo.models.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FriendsViewModel(application: Application) : AndroidViewModel(application) {

    private var usersData: UserData = UserData()

    private val _usersList = MutableLiveData<List<User>>()
    val usersList: LiveData<List<User>> = _usersList

    private val database = UserDatabase.getInstance(application).userDatabaseDao

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.Main) {
            if (database.listEmpty() == null) {
                for (userOne in usersData.userList.value!!) {
                    database.insert(userOne)
                }
            }
            _usersList.value = database.getAllUsers()
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.Main) {
            database.delete(user)
        }
    }
}
