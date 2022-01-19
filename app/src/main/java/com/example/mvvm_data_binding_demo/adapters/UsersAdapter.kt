package com.example.mvvm_data_binding_demo.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_data_binding_demo.data.Users
import com.example.mvvm_data_binding_demo.databinding.MainUserItemBinding
import com.example.mvvm_data_binding_demo.ui.DetailsActivity
import org.greenrobot.eventbus.EventBus

class UsersAdapter(private val context: Context, private val allUsers: List<Users>) :
    RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {
    lateinit var listItemBinding: MainUserItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        listItemBinding = MainUserItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(allUsers[position], context)
    }

    override fun getItemCount(): Int {
        return allUsers.size
    }

    inner class MyViewHolder(binding: MainUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Users, context: Context) {
            listItemBinding.listItemUser = item
            listItemBinding.root.setOnClickListener {
                EventBus.getDefault().postSticky(item)
                context.startActivity(Intent(context, DetailsActivity::class.java))
            }
        }
    }


}