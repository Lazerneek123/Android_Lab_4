package com.example.socialnetworkteo.adapter

import android.content.Context
import com.example.socialnetworkteo.models.User
import com.example.socialnetworkteo.viewModel.FriendsViewModel

interface OnUserClick {
    fun onClick(userId: Int)
    fun onLongClick(user: User, viewModel: FriendsViewModel, context: Context)
}