package com.example.guessword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

 class GameViewModel:ViewModel() {

     // The current word mutable
     val _word = MutableLiveData<String>()
     // The current word immutable

     val word :LiveData<String>
             get()=_word


     // The current score mutable
     val _score = MutableLiveData<Int>()

     // The current score immutable
     val score: LiveData<Int>
     get() = _score




     // Countdown time
     private val _eventGameFinish = MutableLiveData<Boolean>()
     val eventGameFinish: LiveData<Boolean>
         get() = _eventGameFinish




     // The list of words - the front of the list is the next word to guess
     private lateinit var wordList: MutableList<String>

     init {

         // The current word

          _word.value= ""

         // The current score
          _score.value= 0

             Log.i("GameViewModel", "GameViewModel Created!")


         resetList()
         nextWord()

     }

     /**
      * Resets the list of words and randomizes the order
      */
     private fun resetList() {
         wordList = mutableListOf(
             "queen",
             "hospital",
             "basketball",
             "cat",
             "change",
             "snail",
             "soup",
             "calendar",
             "sad",
             "desk",
             "guitar",
             "home",
             "railway",
             "zebra",
             "jelly",
             "car",
             "crow",
             "trade",
             "bag",
             "roll",
             "bubble"
         )
         wordList.shuffle()
     }


     /** Methods for buttons presses **/

      fun onSkip() {
         _score.value=(score.value)?.minus(1)
         nextWord()

     }

      fun onCorrect() {
          _score.value=(score.value)?.plus(1)
         nextWord()

     }



     /**
      * Moves to the next word in the list
      */
      fun nextWord() {
         if (wordList.isEmpty()) {
             onGameFinish()
         } else {
             //Select and remove a _word from the list
             _word.value = wordList.removeAt(0)
         }

     }

     /** Method for the game completed event **/

     fun onGameFinishComplete() {
         _eventGameFinish.value = false
     }

     /** Method for the game completed event **/
     fun onGameFinish() {
         _eventGameFinish.value = true
     }


     override fun onCleared() {
    super.onCleared()
    Log.i("GameViewModel", "GameViewModel destroyed!")
}

 }


