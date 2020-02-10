package com.example.guessword.screens.game


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment

import com.example.guessword.R
import com.example.guessword.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment(){


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

        viewmodel.score.observe(viewLifecycleOwner, Observer {newScore ->
            binding.scoreText.text = newScore.toString()

        })

        //Used to connect viewModel with  the view using data binding
        binding.gameViewmodel=viewmodel


        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

//        viewmodel.word.observe(viewLifecycleOwner, Observer {newWord ->
//            binding.wordText.text = newWord.toString()
//
//        })

//         Observer for the Game finished event
        viewmodel.eventGameFinish.observe(viewLifecycleOwner, Observer<Boolean> { hasFinished ->
            if (hasFinished) gameFinished()
        })

//        binding.endGameButton.setOnClickListener { onEndGame() }


//
//        binding.correctButton.setOnClickListener { onCorrect() }
//        binding.skipButton.setOnClickListener { onSkip() }
//        updateScoreText()
//        updateWordText()
        return binding.root

    }


//    /** Methods for buttons presses **/
//
//    private fun onSkip() {
//
//        viewmodel.onSkip()
////        updateWordText()
////        updateScoreText()
//    }
//
//    private fun onCorrect() {
//        viewmodel.onCorrect()
////                 updateWordText()
////         updateScoreText()
//    }
//



    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment()
        action.score = viewmodel.score.value?:0
        NavHostFragment.findNavController(this).navigate(action)
        viewmodel.onGameFinishComplete()

    }

    private fun onEndGame() {
        gameFinished()
    }



}