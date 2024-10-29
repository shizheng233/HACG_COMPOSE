package com.shihcheeng.hacgcompose.utils.extra

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

private val rMagnet =
    """(?<=[^\da-z])([a-z0-9]{40}|[a-z0-9]{32})(?=[^\da-z])""".toRegex(RegexOption.IGNORE_CASE)
val rBaidu = """\b([a-z0-9]{8})\b\s+\b([a-z0-9]{4})\b""".toRegex(RegexOption.IGNORE_CASE)

fun String.magnet(): Sequence<String> = rMagnet
    .findAll(this)
    .map { it.value } + rBaidu.findAll(this)
    .map { m -> "${m.groups[1]!!.value},${m.groups[2]!!.value}" }

fun Context.openMagnet(magnet: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(magnet))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
    }
}

fun <K, V> Map<K, V>.findKeyByValue(value: V): K? {
    for ((k, v) in entries) {
        if (v == value) {
            return k
        }
    }
    return null
}