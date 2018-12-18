package com.gshockv.developerslife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_stream.*


class StreamFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stream, container, false)
        view.findViewById<Button>(R.id.buttonShowDetails).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.detailsFragment, null)
        )
        return view
    }
}
