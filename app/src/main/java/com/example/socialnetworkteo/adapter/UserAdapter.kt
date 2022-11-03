package com.example.socialnetworkteo.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.socialnetworkteo.R
import com.example.socialnetworkteo.activities.MainActivity
import com.example.socialnetworkteo.databinding.ItemUserBinding
import com.example.socialnetworkteo.models.User
import com.example.socialnetworkteo.viewModel.FriendsViewModel

class UserAdapter(activity: FragmentActivity, viewModel: FriendsViewModel, context: Context) :
    ListAdapter<User, UserAdapter.ItemViewHolder>(object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
    }) {

    companion object {
        @JvmStatic
        var activityFriendsFragment: FragmentActivity? = null
    }

    init {
        activityFriendsFragment = activity
    }

    private var viewModelFragment: FriendsViewModel = viewModel
    private var contextFragment: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_user, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), holder)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), OnUserClick {
        private var binding: ItemUserBinding = ItemUserBinding.bind(itemView)
        private val userName: TextView = binding.userName
        private val userOnlineStatus: TextView = binding.userOnline
        private val userImageView: ImageView = binding.userImage

        override fun onClick(userId: Int) {
            itemView.setOnClickListener {
                (activityFriendsFragment as MainActivity).openFriendMainActivity(userId)
            }
        }

        override fun onLongClick(user: User, viewModel: FriendsViewModel, context: Context) {
            itemView.setOnLongClickListener {
                val builder = android.app.AlertDialog.Builder(context)
                builder.setTitle("Delete user?")
                    .setPositiveButton("Yes") { dialog, _ ->
                        dialog.dismiss()
                        viewModel.deleteUser(user)
                        activityFriendsFragment!!.recreate()

                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
                true
            }
        }

        fun bind(item: User, holder: ItemViewHolder) {
            userName.text = item.name
            userOnlineStatus.text = item.online
            userOnlineStatus.setColor(item)
            userImageView.setImageResource(item.photo)

            item.id.let { holder.onClick(it) }

            holder.onLongClick(item, viewModelFragment, contextFragment)
        }
    }

    private fun TextView.setColor(it: User) {
        if (it.online != "Online") {
            this.setTextColor(Color.rgb(255, 0, 10))
        }
    }
}

