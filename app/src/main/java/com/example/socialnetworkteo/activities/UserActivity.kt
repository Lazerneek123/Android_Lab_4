package com.example.socialnetworkteo.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.socialnetworkteo.databinding.ActivityUserBinding
import com.example.socialnetworkteo.viewModel.UserViewModel

class UserActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: ActivityUserBinding
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        id = intent.extras?.getInt(MainActivity.EXTRA_MESSAGE)!!
        viewModel.loadUserData(id)

        viewModel.user.observe(this) {
            binding.userName.text = it.name
            binding.userPost.text = it.post
            binding.userAge.text = it.age.toString()
            binding.userImage.setImageResource(it.photo)
            binding.userTitle.text = it.hobby
            binding.userEmail.text = it.email
            binding.detailsTextAbout.text = it.description
        }
    }

    fun editUser(view: View) {
        val intent = Intent(this, EditUserActivity::class.java)
        intent.putExtra(MainActivity.EXTRA_MESSAGE, id)
        startActivity(intent)
        finish()
    }
}
