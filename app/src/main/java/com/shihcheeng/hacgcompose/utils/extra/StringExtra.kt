package com.shihcheeng.hacgcompose.utils.extra

private val rMagnet =
    """(?<=[^\da-z])([a-z0-9]{40}|[a-z0-9]{32})(?=[^\da-z])""".toRegex(RegexOption.IGNORE_CASE)
val rBaidu = """\b([a-z0-9]{8})\b\s+\b([a-z0-9]{4})\b""".toRegex(RegexOption.IGNORE_CASE)

fun String.magnet(): Sequence<String> = rMagnet.findAll(this)
    .map { it.value } + rBaidu
    .findAll(this)
    .map { m -> "${m.groups[1]!!.value},${m.groups[2]!!.value}" }