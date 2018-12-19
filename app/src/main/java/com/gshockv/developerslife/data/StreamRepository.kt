package com.gshockv.developerslife.data

object StreamRepository {


    fun loadStream(stream: StreamType) : GifStream {
        return fakeStream()
    }

    private fun fakeStream() = GifStream(items = arrayListOf(
        GifItem(1, " Gif Item #1", 11, 0),
        GifItem(2, " Gif Item #2", 0, 10),
        GifItem(3, " Gif Item #3", 1, 3),
        GifItem(4, " Gif Item #4", 898, 20),
        GifItem(5, " Gif Item #5", 56, 8),
        GifItem(6, " Gif Item #6", 7, 34),
        GifItem(7, " Gif Item #7", 11, 22),
        GifItem(8, " Gif Item #8", 12, 11),
        GifItem(9, " Gif Item #9", 1, 12),
        GifItem(10, " Gif Item #10", 99, 0)
    ))
}
