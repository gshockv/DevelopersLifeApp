package com.gshockv.developerslife.ui.stream

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.gshockv.developerslife.R
import com.gshockv.developerslife.data.GifStream
import com.gshockv.developerslife.data.StreamType
import com.gshockv.developerslife.data.header
import com.gshockv.developerslife.ui.stream.view.MarginItemDecoration
import com.gshockv.developerslife.ui.utils.GlideApp
import com.gshockv.developerslife.ui.utils.MyGlideExtension
import kotlinx.android.synthetic.main.card_gif_item.view.*
import kotlinx.android.synthetic.main.fragment_stream.*

class StreamFragment : Fragment() {
    private val viewModel: StreamViewModel by lazy {
        ViewModelProviders.of(activity!!).get(StreamViewModel::class.java)
    }

    private val menuDialog = BottomNavigationDialog.newInstance().apply {
        onStreamSelected = { type ->
            loadStream(type)
        }
    }

    private lateinit var streamAdapter: StreamListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stream, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        bottomAppBar.setNavigationOnClickListener {
//            menuDialog.apply {
//                selectedStream = viewModel.currentStreamType()
//            }.show(fragmentManager!!, BottomNavigationDialog.TAG)
//        }

        recyclerViewStream.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerViewStream.setHasFixedSize(true)
        recyclerViewStream.addItemDecoration(
            MarginItemDecoration(
                resources.getDimension(R.dimen.default_item_padding).toInt()
            )
        )

//        fabReload.setColorFilter(Color.WHITE)
    }

    override fun onResume() {
        super.onResume()

        loadStream(viewModel.currentStreamType())
    }

    private fun loadStream(type: StreamType) {
        textViewHeader.setText(type.header)

        viewModel.loadStream(type).observe(this, Observer { stream ->
            stream?.let {
                streamAdapter = StreamListAdapter(it)
                recyclerViewStream.adapter = streamAdapter
            }
        })
    }

    private inner class StreamListAdapter(val stream: GifStream) : RecyclerView.Adapter<ItemViewHolder>() {
        override fun getItemCount() = stream.items.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_gif_item, parent, false))
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind()
        }
    }

    private inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() = with(itemView) {

            imageViewPreview.let {
                GlideApp.with(itemView.context)
                    .load(R.drawable.img_item_placeholder)
                    .into(it)

                it.setOnClickListener {
                    Toast.makeText(itemView.context, "Item Clicked...", Toast.LENGTH_SHORT).show()
                }
                it.setOnLongClickListener {
                    Toast.makeText(itemView.context, "LONG CLICK", Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }
    }
}
