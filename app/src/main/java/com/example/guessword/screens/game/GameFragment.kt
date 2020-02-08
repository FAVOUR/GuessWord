package com.example.guessword.screens.game


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.example.guessword.R
import com.example.guessword.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment(){

    // The current word

    private var word = ""

    // The current score
    private var score = 0
    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    private lateinit var binding: FragmentGameBinding

    private lateinit  var viewmodel:GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        Log.i("Started the ","ViewModelProviders.of")
       viewmodel = ViewModelProviders.of(this).get(GameViewModel::class.java)




        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }
        updateScoreText()
        updateWordText()
        return binding.root

    }


    /** Methods for buttons presses **/

    private fun onSkip() {
        score--
        viewmodel.nextWord()
        updateWordText()
        updateScoreText()
    }

    private fun onCorrect() {
        score++
        viewmodel.nextWord()
                 updateWordText()
         updateScoreText()
    }



    /** Methods for updating the UI **/

    private fun updateWordText() {
        binding.wordText.text = viewmodel.word
    }

    private fun updateScoreText() {
        binding.scoreText.text = viewmodel.score.toString()
    }



}