package com.smartschools.android.ui.users.student.assignments.adapter

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
import com.smartschools.android.data.model.student.assignment.AssignmentStudentData
import com.smartschools.android.databinding.ItemAssignmentsStudentBinding
import com.smartschools.android.databinding.ItemDashboardBinding
import com.smartschools.android.di.NetworkModule.photoUrl


class StudentAssignmentsAdapter(
    private val itemClick: (AssignmentStudentData) -> Unit,
) : ListAdapter<AssignmentStudentData, StudentAssignmentsAdapter.ViewHolder>(DiffCallback) {

    private val fullList: MutableList<AssignmentStudentData> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun submitFullList(list: MutableList<AssignmentStudentData>) {
        fullList.clear()
        fullList.addAll(list)
        super.submitList(fullList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAssignmentsStudentBinding.inflate(LayoutInflater.from(parent.context)),
            itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(
        private val binding: ItemAssignmentsStudentBinding,
        private val itemClick: (AssignmentStudentData) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AssignmentStudentData) {
            binding.tvTitle.text = item.title

//            Glide.with(MyApplication.appContext).load(photoUrl+item.icon).error(R.drawable.ic_globel)
//                .dontAnimate()
//                .apply(RequestOptions().override(120, 120))
//                .into(binding.ivIcon)


//
//            binding.clickCardView.setOnClickListener {
//                itemClick(project)
//            }
//            binding.ivFavorites.setOnClickListener {
//                favouriteProjectClick(project)
//            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<AssignmentStudentData>() {
        override fun areItemsTheSame(
            oldItem: AssignmentStudentData,
            newItem: AssignmentStudentData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: AssignmentStudentData,
            newItem: AssignmentStudentData
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }
    fun reset() {
        fullList.clear()
        notifyDataSetChanged()
    }



}
