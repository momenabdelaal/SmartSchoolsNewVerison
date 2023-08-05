package com.smartschools.android.data.model.side_menu

import android.content.Context
import com.smartschools.android.R
import com.smartschools.android.core.network.MyApplication

class SideMenuItem(
    val itemId: Int,
    val itemName: String,
    val Group: String,
    val itemNotifications: Int,
    val coverId: Int,
    val color_background: Int


) {
    constructor() : this(0, "", "", 0, 0, 0)

    init {

    }

    fun initListStudents(
        context: Context
    ): List<SideMenuItem> {
        val navItemList: MutableList<SideMenuItem> = ArrayList()
        navItemList.add(
            SideMenuItem(
                45, context.getString(R.string.back_to_main),
                "", 0, R.drawable.ic_dashboard_home, R.color.white
            )
        )
        navItemList.add(
            SideMenuItem(
                29,  context.getString(R.string.membership_card),
                "student", 0, R.drawable.ic_card, R.color.white
            )
        )

        navItemList.add(
            SideMenuItem(
                31, context.getString(R.string.app_settings),
                "student", 0, R.drawable.ic_account_settings, R.color.colorWhite
            )
        )
        navItemList.add(
            SideMenuItem(
                32, context.getString(R.string.rating_app),
                "student", 0, R.drawable.ic_quality, R.color.colorOfGray
            )
        )

        navItemList.add(
            SideMenuItem(
                33, context.getString(R.string.about_us),
                "student", 0, R.drawable.ic_headset, R.color.colorWhite
            )
        )
        navItemList.add(
            SideMenuItem(
                5, context.getString(R.string.discount_partners),
                "student", 0, R.drawable.ic_sale, R.color.colorOfGray
            )
        )
//
        navItemList.add(
            SideMenuItem(
                6, context.getString(R.string.logout),
                "student", 0, R.drawable.ic_logout, R.color.colorWhite
            )
        )
        return navItemList
    }

    public fun initListTeacher(
        navItemList: MutableList<SideMenuItem>,
        context: Context
    ): List<SideMenuItem> {

        navItemList.add(
            SideMenuItem(
                16, context.getString(R.string.irregularities_student),
                "teacher", 0, R.drawable.ic_sanction_student, R.color.white
            )
        )

        navItemList.add(
            SideMenuItem(
                41, context.getString(R.string.smart_pass),
                "teacher", 0, R.drawable.ic_scan_pass, R.color.colorOfGray
            )
        )


        navItemList.add(
            SideMenuItem(
                29, context.getString(R.string.membership_card),
                "teacher", 0, R.drawable.ic_card, R.color.white
            )
        )
//
//
        navItemList.add(
            SideMenuItem(
                31, context.getString(R.string.app_settings),
                "teacher", 0, R.drawable.ic_account_settings, R.color.colorWhite
            )
        )
        navItemList.add(
            SideMenuItem(
                32, context.getString(R.string.rating_app),
                "teacher", 0, R.drawable.ic_quality, R.color.colorOfGray
            )
        )

        navItemList.add(
            SideMenuItem(
                33, context.getString(R.string.about_us),
                "teacher", 0, R.drawable.ic_headset, R.color.colorWhite
            )
        )
        navItemList.add(
            SideMenuItem(
                5, context.getString(R.string.discount_partners),
                "teacher", 0, R.drawable.ic_sale, R.color.colorOfGray
            )
        )
        navItemList.add(
            SideMenuItem(
                40, context.getString(R.string.logout),
                "teacher", 0, R.drawable.ic_logout, R.color.colorWhite
            )
        )



        return navItemList
    }
}