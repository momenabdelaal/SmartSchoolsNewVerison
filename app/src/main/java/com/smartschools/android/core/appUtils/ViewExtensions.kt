package com.smartschools.android.core.appUtils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.constraintlayout.widget.Group
import androidx.databinding.BindingAdapter

import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.google.android.material.snackbar.Snackbar
import com.smartschools.android.R
import java.io.File

fun View.show() {
  visibility = View.VISIBLE
  if (this is Group) {
    this.requestLayout()
  }
}

fun View.hide() {
  visibility = View.GONE
  if (this is Group) {
    this.requestLayout()
  }
}

fun View.invisible() {
  visibility = View.INVISIBLE
  if (this is Group) {
    this.requestLayout()
  }
}

@BindingAdapter("goneUnless")
fun View.goneUnless(visible: Boolean) {
  visibility = if (visible) View.VISIBLE else View.GONE
  if (this is Group) {
    this.requestLayout()
  }
}

fun View.enable() {
  isEnabled = true
  alpha = 1f
}

fun View.disable() {
  isEnabled = false
  alpha = 0.5f
}

fun View.showSnackBar(message: String, retryActionName: String? = null, action: (() -> Unit)? = null) {
  val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)

  action?.let {
    snackBar.setAction(retryActionName) {
      it()
    }
  }

  snackBar.show()
}

//@BindingAdapter("loadImage")
//fun ImageView.loadImage(imageUrl: String?) {
//  if (imageUrl != null && imageUrl.isNotEmpty()) {
//    load(imageUrl) {
//      crossfade(true)
//      placeholder(R.color.backgroundGray)
//      error(R.drawable.bg_no_image)
//    }
//  } else {
//    setImageResource(R.drawable.bg_no_image)
//  }
//}
//
//@BindingAdapter("loadCircleImage")
//fun ImageView.loadCircleImage(imageUrl: String?) {
//  if (imageUrl != null && imageUrl.isNotEmpty()) {
//    load(imageUrl) {
//      crossfade(true)
//      placeholder(R.color.backgroundGray)
//      error(R.drawable.bg_no_image)
//      transformations(
//        CircleCropTransformation()
//      )
//    }
//  } else {
//    load(R.drawable.bg_no_image) {
//      crossfade(true)
//      transformations(
//        CircleCropTransformation()
//      )
//    }
//  }
//}



fun ImageView.setLocalImage(file: File, applyCircle: Boolean = false) {
  val glide = Glide.with(this).load(file)
  if (applyCircle) {
    glide.apply(RequestOptions.circleCropTransform()).into(this)
  } else {
    glide.into(this)
  }
}

fun Fragment.hideKeyboard() {
  view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
  hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
  val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
  inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}