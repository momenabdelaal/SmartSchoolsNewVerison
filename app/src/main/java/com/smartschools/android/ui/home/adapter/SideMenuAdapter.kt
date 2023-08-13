package com.smartschools.android.ui.home.adapter

import android.annotation.SuppressLint
import android.app.Application
import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.smartschools.android.R
import com.smartschools.android.core.network.MyApplication
import com.smartschools.android.data.model.dashboard.DashboardItem
import com.smartschools.android.data.model.side_menu.SideMenuItem
import com.smartschools.android.databinding.ItemDashboardBinding
import com.smartschools.android.databinding.ItemSideMenuBinding
import com.smartschools.android.di.NetworkModule.photoUrl


class SideMenuAdapter(
    private val itemClick: (SideMenuItem) -> Unit,
) : ListAdapter<SideMenuItem, SideMenuAdapter.ViewHolder>(DiffCallback) {

    private val fullList: MutableList<SideMenuItem> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun submitFullList(list: MutableList<SideMenuItem>) {
        fullList.clear()
        fullList.addAll(list)
        super.submitList(fullList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSideMenuBinding.inflate(LayoutInflater.from(parent.context)),
            itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(
        private val binding: ItemSideMenuBinding,
        private val itemClick: (SideMenuItem) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SideMenuItem) {
            binding.tvItemName.text = item.itemName
            binding.ivItem.setImageResource(item.coverId)



//            Glide.with(MyApplication.appContext).load(photoUrl+item.icon).error(R.drawable.ic_globel)
//                .dontAnimate()
//                .apply(RequestOptions().override(120, 120))
//                .into(binding.ivIcon)


//
            binding.linerNavgation.setOnClickListener {
                itemClick(item)
            }
//            binding.ivFavorites.setOnClickListener {
//                favouriteProjectClick(project)
//            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<SideMenuItem>() {
        override fun areItemsTheSame(
            oldItem: SideMenuItem,
            newItem: SideMenuItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SideMenuItem,
            newItem: SideMenuItem
        ): Boolean {
            return oldItem.itemId == newItem.itemId
        }
    }
    fun reset() {
        fullList.clear()
        notifyDataSetChanged()
    }



}
