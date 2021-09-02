package com.itsazza.mobz.menu.potion

fun getPotionDuration(time: Int): String {
    return if (time >= 3600) {
        "%02d:%02d:%02d".format(time / 3600, time / 60 % 60, time % 60)
    } else {
        "%02d:%02d".format(time / 60, time % 60)
    }
}
