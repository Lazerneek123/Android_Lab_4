package com.example.socialnetworkteo.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.socialnetworkteo.R
import com.example.socialnetworkteo.adapter.UserAdapter
import com.example.socialnetworkteo.databinding.ActivityAddUserBinding
import com.example.socialnetworkteo.models.User
import com.example.socialnetworkteo.viewModel.AddUserViewModel

class AddUserActivity : AppCompatActivity() {
    private lateinit var viewModel: AddUserViewModel
    private lateinit var binding: ActivityAddUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AddUserViewModel::class.java]

        binding.userAddImage.setImageResource(R.drawable.icon_my)
        binding.buttonSave.setOnClickListener {
            insertNewUser()
            UserAdapter.activityFriendsFragment!!.recreate()
            finish()
        }
    }

    private fun insertNewUser() {
        var newUserName = binding.textAddUserName.text.toString()
        if(newUserName == ""){
            newUserName = "New user"
        }
        val newUserPost = binding.textAddUserPost.text.toString()
        var newUserAge = binding.textAddUserAge.text.toString()
        if(newUserAge == ""){
            newUserAge = "0"
        }
        val newEmail = binding.textAddUserEmail.text.toString()
        val newHobby = binding.textAddUserHobby.text.toString()
        val newDescription = binding.textAddUserDescription.text.toString()

        val user = User(
            newUserName,
            newUserPost,
            newUserAge.toInt(),
            onlineStatus[(0..4).random()],
            newEmail,
            R.drawable.icon_my,
            newHobby,
            newDescription
        )
        viewModel.insertUser(user)
    }

    private val onlineStatus = listOf(
        (1..59).random().toString() + " min ago",
        "Online",
        (1..23).random().toString() + " hours ago",
        "Yesterday",
        (1..11).random().toString() + " month ago"
    )
}