package com.smartschools.android.core.appUtils

import android.content.ComponentName
import android.content.Context
import android.content.CursorLoader
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Looper
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.smartschools.android.R


fun Context.isNetworkConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo?.isConnectedOrConnecting ?: false
}

fun Context.toggleAndroidComponent(componentClass: Class<*>, enable: Boolean) {
    val componentName = ComponentName(this, componentClass)

    val newState = if (enable)
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    else
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED

    packageManager.setComponentEnabledSetting(componentName, newState, PackageManager.DONT_KILL_APP)
}

/**
 * Default short toast
 */
fun Context.toast(any: Any, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, any.toString(), duration).show()
}

/**
 * Default short toast
 */
fun Context.toast(@StringRes resString: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(resString), duration)
}

/**
 * Long duration toast
 */
fun Context.longToast(any: Any) {
    toast(any.toString(), Toast.LENGTH_LONG)
}

/**
 * Long duration toast
 */
fun Context.longToast(@StringRes stringRes: Int) {
    toast(getString(stringRes), Toast.LENGTH_LONG)
}

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    context?.toast(message, duration)
}

fun Fragment.toast(@StringRes resString: Int, duration: Int = Toast.LENGTH_SHORT) {
    context?.toast(getString(resString), duration)
}

fun Fragment.longToast(@StringRes stringRes: Int) {
    context?.toast(getString(stringRes), Toast.LENGTH_LONG)
}
fun Fragment.snackBarSuccess(message:String){
    Snackbar.make(
        requireView(),
        message,
        Snackbar.LENGTH_LONG
    ).setBackgroundTint(requireContext().resources.getColor(R.color.colorGreen)).show()

}
fun Fragment.snackBarFailure(message:String){
    Snackbar.make(
        requireView(),
        message,
        Snackbar.LENGTH_LONG
    ).setBackgroundTint(requireContext().resources.getColor(R.color.colorPrimary)).show()

}

fun Context.getPath(contentUri: Uri): String {
    val proj = arrayOf(MediaStore.Images.Media.DATA)
    val result: String

    if (Looper.myLooper() == null) {
        Looper.prepare()
    }
    val cursorLoader = CursorLoader(
            this,
            contentUri, proj, null, null, null)
    val cursor = cursorLoader.loadInBackground()

    result = if (cursor != null) {
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        cursor.getString(column_index)
    } else ({
        contentUri.path
    }).toString()

    return result
}

fun Context.getColorCompat(@ColorRes resId: Int): Int {
    return ContextCompat.getColor(this, resId)
}

fun drawQR(ivBarcode: ImageView, userId: String?) {
    val writer = QRCodeWriter()
    val bitMatrix = writer.encode(userId, BarcodeFormat.QR_CODE, 70, 70)
    val width = bitMatrix.width
    val height = bitMatrix.height
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
    for (x in 0 until width) {
        for (y in 0 until height) {
            bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
        }
    }
    ivBarcode.setImageBitmap(bitmap)
}




