package com.goodwy.commons.extensions

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import com.goodwy.commons.R
import com.goodwy.commons.helpers.LBC_ARC
import com.goodwy.commons.helpers.LBC_IOS
import com.goodwy.commons.helpers.LBC_ORIGINAL

fun Resources.getColoredBitmap(resourceId: Int, newColor: Int): Bitmap {
    val drawable = getDrawable(resourceId)
    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.colorFilter = PorterDuffColorFilter(newColor, PorterDuff.Mode.SRC_IN)
    drawable.draw(canvas)
    return bitmap
}

fun Resources.getColoredDrawable(drawableId: Int, colorId: Int, alpha: Int = 255) = getColoredDrawableWithColor(drawableId, getColor(colorId), alpha)

fun Resources.getColoredDrawableWithColor(drawableId: Int, color: Int, alpha: Int = 255): Drawable {
    val drawable = getDrawable(drawableId)
    drawable.mutate().applyColorFilter(color)
    drawable.mutate().alpha = alpha
    return drawable
}

fun Resources.hasNavBar(): Boolean {
    val id = getIdentifier("config_showNavigationBar", "bool", "android")
    return id > 0 && getBoolean(id)
}

fun Resources.getNavBarHeight(): Int {
    val id = getIdentifier("navigation_bar_height", "dimen", "android")
    return if (id > 0 && hasNavBar()) {
        getDimensionPixelSize(id)
    } else
        0
}

fun getSettingsIcon(id: Int): Int {
    return when (id) {
        1 -> R.drawable.ic_settings_hexagon
        2 -> R.drawable.ic_settings_cog_vector
        3 -> R.drawable.ic_settings
        4 -> R.drawable.ic_settings_gear
        5 -> R.drawable.ic_settings_toggles
        else -> R.drawable.ic_more_horiz
    }
}

fun getContactsColorListIcon(id: Int): Int {
    return when (id) {
        LBC_ORIGINAL -> R.drawable.ic_color_list
        LBC_IOS -> R.drawable.ic_color_list_ios
        LBC_ARC -> R.drawable.ic_color_list_arc
        else -> R.drawable.ic_color_list_android
    }
}
