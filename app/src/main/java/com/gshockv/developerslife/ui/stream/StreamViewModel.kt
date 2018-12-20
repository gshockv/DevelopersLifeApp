package com.gshockv.developerslife.ui.stream

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gshockv.developerslife.data.GifStream
import com.gshockv.developerslife.data.StreamRepository
import com.gshockv.developerslife.data.StreamType

class StreamViewModel : ViewModel() {
    private val streamRepo = StreamRepository
    private val streamLiveData = MutableLiveData<GifStream>()
    private var streamType = StreamType.LATEST

    fun loadStream(stream: StreamType) : MutableLiveData<GifStream> {
        this.streamType = stream
        streamLiveData.value = streamRepo.loadStream(stream)
        return streamLiveData
    }

    fun currentStreamType() = streamType
}
