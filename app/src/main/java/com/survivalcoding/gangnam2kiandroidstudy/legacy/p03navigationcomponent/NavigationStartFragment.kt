package com.survivalcoding.gangnam2kiandroidstudy.legacy.p03navigationcomponent

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.survivalcoding.gangnam2kiandroidstudy.R

class NavigationStartFragment : Fragment(R.layout.fragment_navigation_start) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.start_text_view).setOnClickListener {
            findNavController().navigate(route = Route.TargetScreen("Hello"))
        }
    }
}