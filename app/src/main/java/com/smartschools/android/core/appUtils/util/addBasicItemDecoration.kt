package com.smartschools.android.core.appUtils.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartschools.android.R


fun RecyclerView.addBasicItemDecoration(dimensionId : Int = R.dimen.item_decoration_small_margin){
    while (this.itemDecorationCount > 0) {
        this.removeItemDecorationAt(0)
    }

    this.addItemDecoration(
        MarginItemDecoration(
            spaceSize = resources.getDimensionPixelSize(dimensionId),
            spanCount = (this.layoutManager as GridLayoutManager).spanCount
        )
    )
}