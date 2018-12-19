package com.gshockv.developerslife.ui.stream

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gshockv.developerslife.R
import com.gshockv.developerslife.data.StreamType
import kotlinx.android.synthetic.main.fragment_stream.*

class StreamFragment : Fragment() {
    private val viewModel : StreamViewModel by lazy {
        ViewModelProviders.of(activity!!).get(StreamViewModel::class.java)
    }

    private var currentStream = StreamType.LATEST

    private val menuDialog = BottomNavigationDialog.newInstance().apply {
        onStreamSelected = { type ->
            currentStream = type
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stream, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomAppBar.setNavigationOnClickListener {
            menuDialog.apply {
                selectedStream = currentStream
            }.show(fragmentManager!!, BottomNavigationDialog.TAG)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadStream(StreamType.LATEST).observe(this, Observer { stream ->
            stream?.let {
                Toast.makeText(context, "LATEST loaded", Toast.LENGTH_SHORT).show()
                it.items.forEach { item ->
                    Log.d("GifItem", "Item: $item")
                }
            }
        })
    }
}
