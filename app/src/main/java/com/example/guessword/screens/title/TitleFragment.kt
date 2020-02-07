package com.example.guessword.screens.title


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.guessword.R
import com.example.guessword.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 */
class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding :FragmentTitleBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_title,container,false)


        binding.playButton.setOnClickListener {view ->

            findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }


        return binding.root

    }



}
