package com.example.socialnetworkteo.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.socialnetworkteo.adapter.UserAdapter
import com.example.socialnetworkteo.databinding.ActivityEditUserBinding
import com.example.socialnetworkteo.models.User
import com.example.socialnetworkteo.viewModel.EditUserViewModel

class EditUserActivity : AppCompatActivity() {
    private lateinit var viewModel: EditUserViewModel
    private lateinit var binding: ActivityEditUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[EditUserViewModel::class.java]

        val id = intent.extras?.getInt("id")!!
        viewModel.loadUserData(id)

        viewModel.user.observe(this) {
            binding.userEditImage.setImageResource(it.photo)
            binding.textEditUserName.setText(it.name)
            binding.textEditUserPost.setText(it.post)
            binding.textEditUserAge.setText(it.age.toString())
            binding.textEditUserEmail.setText(it.email)
            binding.textEditUserHobby.setText(it.hobby)
            binding.textEditUserDescription.setText(it.description)
        }

        binding.buttonUpdate.setOnClickListener {
            updateUserInfo(id)
            UserAdapter.activityFriendsFragment!!.recreate()
            finish()
        }
    }

    private fun updateUserInfo(id: Int) {
        val newUser = viewModel.user.value!!
        val newUserName = binding.textEditUserName.text.toString()
        val newUserPost = binding.textEditUserPost.text.toString()
        val newUserAge = binding.textEditUserAge.text.toString().toInt()
        val newEmail = binding.textEditUserEmail.text.toString()
        val newHobby = binding.textEditUserHobby.text.toString()
        val newDescription = binding.textEditUserDescription.text.toString()

        val user = User(
            newUserName,
            newUserPost,
            newUserAge,
            newUser.online,
            newEmail,
            newUser.photo,
            newHobby,
            newDescription
        )
        user.id = id
        viewModel.updateUserInfo(user)
    }
}