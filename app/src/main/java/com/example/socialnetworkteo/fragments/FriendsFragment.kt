package com.example.socialnetworkteo.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.socialnetworkteo.R
import com.example.socialnetworkteo.adapter.UserAdapter
import com.example.socialnetworkteo.databinding.FragmentFriendsBinding
import com.example.socialnetworkteo.viewModel.FriendsViewModel

class FriendsFragment : Fragment() {
    private lateinit var viewModel: FriendsViewModel
    private var binding: FragmentFriendsBinding? = null
    private var root: View? = null
    private var packageName: String? = null

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this)[FriendsViewModel::class.java]

        root = inflater.inflate(R.layout.fragment_friends, null)
        binding = FragmentFriendsBinding.bind(root!!)
        packageName = activity?.packageName

        viewModel.getAllUsers()

        val recyclerView: RecyclerView = binding!!.userListRecyclerView
        val adapter = activity?.let { UserAdapter(it, viewModel, requireContext()) }
        recyclerView.adapter = adapter

        viewModel.usersList.observe(viewLifecycleOwner) {
            it?.let {
                adapter!!.submitList(it)
            }
        }

        val textView = binding!!.textFriends
        textView.text = resources.getString(R.string.friends_fragment_inscription)

        return root!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}