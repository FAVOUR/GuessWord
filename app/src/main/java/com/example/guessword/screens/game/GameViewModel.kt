package com.example.guessword.screens.game

import android.os.CountDownTimer
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


     // Countdown time
     private val _currentTime = MutableLiveData<Long>()
     val currentTime: LiveData<Long>
         get() = _currentTime

     // The list of words - the front of the list is the next word to guess
     private lateinit var wordList: MutableList<String>

     private val timer: CountDownTimer


     init {

         Log.i("GameViewModel", "GameViewModel Created!")


         // The current word

          _word.value= ""

         // The current score
          _score.value= 0




         // Creates a timer which triggers the end of the game when it finishes
         timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

             override fun onTick(millisUntilFinished: Long) {
                 _currentTime.value = millisUntilFinished/ONE_SECOND
                 Log.i("CountDownTimer  ",  _currentTime.value.toString())

             }

             override fun onFinish() {
                 _currentTime.value = DONE
                 onGameFinish()
             }

         }

         timer.start()


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
//             onGameFinish()
             resetList()

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
         timer.cancel()
    Log.i("GameViewModel", "GameViewModel destroyed!")
}

     companion object {

         // Time when the game is over
         private const val DONE = 0L

         // Countdown time interval
         private const val ONE_SECOND = 1000L

         // Total time for the game
         private const val COUNTDOWN_TIME = 60000L

     }

 }


