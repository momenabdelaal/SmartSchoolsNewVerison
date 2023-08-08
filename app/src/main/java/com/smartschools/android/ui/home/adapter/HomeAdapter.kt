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
import com.smartschools.android.databinding.ItemDashboardBinding
import com.smartschools.android.di.NetworkModule.photoUrl


class HomeAdapter(
    private val itemClick: (DashboardItem) -> Unit,
) : ListAdapter<DashboardItem, HomeAdapter.ViewHolder>(DiffCallback) {

    private val fullList: MutableList<DashboardItem> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun submitFullList(list: MutableList<DashboardItem>) {
        fullList.clear()
        fullList.addAll(list)
        super.submitList(fullList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDashboardBinding.inflate(LayoutInflater.from(parent.context)),
            itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(
        private val binding: ItemDashboardBinding,
        private val itemClick: (DashboardItem) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DashboardItem) {
            binding.tvTitle.text = item.text

            Glide.with(MyApplication.appContext).load(photoUrl+item.icon).error(R.drawable.ic_globel)
                .dontAnimate()
                .apply(RequestOptions().override(120, 120))
                .into(binding.ivIcon)


//
            binding.linearItem.setOnClickListener {
                itemClick(item)
            }
//            binding.ivFavorites.setOnClickListener {
//                favouriteProjectClick(project)
//            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<DashboardItem>() {
        override fun areItemsTheSame(
            oldItem: DashboardItem,
            newItem: DashboardItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: DashboardItem,
            newItem: DashboardItem
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }
    fun reset() {
        fullList.clear()
        notifyDataSetChanged()
    }



}
