package com.smartschools.android.ui.basic.welcome_screens.pager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.smartschools.android.R
import java.util.*


class ViewPagerAdapter(val context: Context, val imageList: List<SliderModel>) : PagerAdapter() {
    // on below line we are creating a method
    // as get count to return the size of the list.
    override fun getCount(): Int {
        return imageList.size
    }

    // on below line we are returning the object
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    // on below line we are initializing
    // our item and inflating our layout file
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // on below line we are initializing
        // our layout inflater.
        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // on below line we are inflating our custom
        // layout file which we have created.
        val itemView: View = mLayoutInflater.inflate(R.layout.item_view_pager, container, false)

        // on below line we are initializing
        // our image view with the id.
        val imageView: ImageView = itemView.findViewById<View>(R.id.ivImage) as ImageView
        val tvTitle: TextView = itemView.findViewById<View>(R.id.tvTitle) as TextView
        val tvDescription: TextView = itemView.findViewById<View>(R.id.tvDescription) as TextView

        // on below line we are setting
        // image resource for image view.

        tvTitle.text = imageList[position].title
        tvDescription.text = imageList[position].description
        imageView.setImageDrawable(imageList[position].image)
//        Glide.with(context).load(imageList[position]).into(imageView)

        imageView.setOnClickListener {
//            initDialog(imageList[position])

        }
        // on the below line we are adding this
        // item view to the container.
        Objects.requireNonNull(container).addView(itemView)

        // on below line we are simply
        // returning our item view.
        return itemView
    }





    // on below line we are creating a destroy item method.
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        // on below line we are removing view
        container.removeView(`object` as LinearLayout)
    }
}
