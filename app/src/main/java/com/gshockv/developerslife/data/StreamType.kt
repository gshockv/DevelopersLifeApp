package com.gshockv.developerslife.data

import com.gshockv.developerslife.R

enum class StreamType(val type: String) {
    LATEST("latest"),
    HOT("hot"),
    TOP("top"),
    RANDOM("random")
}

val StreamType.header : Int
    get() = when (this) {
        StreamType.LATEST -> R.string.header_latest
        StreamType.HOT -> R.string.header_hot
        StreamType.TOP -> R.string.header_top
        StreamType.RANDOM -> R.string.header_random
    }
