package com.example.firstapp.Fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels

import com.example.firstapp.LifecycleData.DynamicObjects
import com.example.firstapp.LifecycleData.LifeData
import com.example.firstapp.LifecycleData.Transition

import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentQuizBinding


class QuizFrag : Fragment() {
    lateinit var binding: FragmentQuizBinding
    private var rightAnswer: String? = null
    private val dynamicObjects: DynamicObjects by activityViewModels()
    private val lifeData: LifeData by activityViewModels()
    private val transition: Transition by activityViewModels()
    private var rightAnswerCount = 0
    private var quizCount = 1
    val testsData = arrayOf(
        //Тест 1
       arrayOf(
           arrayOf ("t1Question1", "t1Choice1","Choice2","Choice3","Choice4"),
           arrayOf("t1Question2", "t1Choice1","Choice2","Choice3","Choice4"),
           arrayOf("t1Question3", "t1Choice1","Choice2","Choice3","Choice4"),
           arrayOf("t1Question4", "t1Choice1","Choice2","Choice3","Choice4"),
           arrayOf("t1Question5", "t1Choice1","Choice2","Choice3","Choice4"),
           arrayOf("t1Question6", "t1Choice1","Choice2","Choice3","Choice4"),
           arrayOf("t1Question7", "t1Choice1","Choice2","Choice3","Choice4"),
           arrayOf("t1Question8", "t1Choice1","Choice2","Choice3","Choice4"),
           arrayOf("t1Question9", "t1Choice1","Choice2","Choice3","Choice4"),
           arrayOf("t1Question10", "t1Choice1","Choice2","Choice3","Choice4")
        ),

        //Тест 2
       arrayOf(
           arrayOf("t2Question1", "t2Choice1","Choice2","Choice3","Choice4"),
           arrayOf("t2Question2", "t2Choice1","Choice2","Choice3","Choice4"),
           arrayOf("t2Question3", "t2Choice1","Choice2","Choice3","Choice4"),
           arrayOf("t2Question4", "t2Choice1","Choice2","Choice3","Choice4"),
           arrayOf("t2Question5", "t2Choice1","Choice2","Choice3","Choice4"),
           arrayOf("t2Question6", "t2Choice1","Choice2","Choice3","Choice4"),
           arrayOf("t2Question7", "t2Choice1","Choice2","Choice3","Choice4"),

        ),

        //Тест 3
        arrayOf(
            arrayOf("t3Question1", "t3Choice1","Choice2","Choice3","Choice4"),
            arrayOf("t3Question2", "t3Choice1","Choice2","Choice3","Choice4"),
        ),

        //Тест 4
        arrayOf(
            arrayOf("t4Question1", "t4Choice1","Choice2","Choice3","Choice4"),
            arrayOf("t4Question2", "t4Choice1","Choice2","Choice3","Choice4"),
            arrayOf("t4Question3", "t4Choice1","Choice2","Choice3","Choice4"),
            arrayOf("t4Question4", "t4Choice1","Choice2","Choice3","Choice4"),
            arrayOf("t4Question5", "t4Choice1","Choice2","Choice3","Choice4"),
            arrayOf("t4Question6", "t4Choice1","Choice2","Choice3","Choice4")
        )
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btChoice1.setOnClickListener{
            checkAnswer(binding.btChoice1)
        }
        binding.btChoice2.setOnClickListener{
            checkAnswer(binding.btChoice2)
        }
        binding.btChoice3.setOnClickListener{
            checkAnswer(binding.btChoice3)
        }
        binding.btChoice4.setOnClickListener{
            checkAnswer(binding.btChoice4)
        }


        showNextQuiz()
    }

    fun showNextQuiz(){

        val testNum = dynamicObjects.dynamicTest.value!!.Num
        //Pick one quiz set.
        var questions = testsData[testNum]
        var quiz = questions[quizCount-1]

        // Update countLabel
        binding.countLabel.text = getString(R.string.count_label, quizCount)

        //Set question and right answer.
        binding.txtQuest.text = quiz[0]
        rightAnswer = testsData[testNum][0][1]

        //Remove Question text
        quiz = quiz.copyOfRange(1, quiz.size)


        //Shuffle answer & choices
        quiz.shuffle()


        //Set choices
        binding.btChoice1.text = quiz[0]
        binding.btChoice2.text = quiz[1]
        binding.btChoice3.text = quiz[2]
        binding.btChoice4.text = quiz[3]

        questions = questions.copyOfRange(1, questions.size)
    }

    fun checkAnswer(view:View){
        val testNum = dynamicObjects.dynamicTest.value!!.Num
        val answerBtn: Button = view.findViewById(view.id)
        val btnText = answerBtn.text.toString()

        if (btnText == rightAnswer){
            //Correct
            rightAnswerCount++
        }
        checkQuizCount(testNum)

//        val alertTitle: String
//
//        else{
//            //Wrong
//            alertTitle = "Wrong"
//        }
//
//        //Create Dialog
//        AlertDialog.Builder(context)
//            .setTitle(alertTitle)
//            .setMessage("Answer: $rightAnswer")
//            .setPositiveButton("OK"){dialoginterface,i ->
//
//            }
//            .setCancelable(false)
//            .show()


    }

    fun checkQuizCount(testNum:Int){
        val QUIZ_COUNT = testsData[testNum].size
        if (QUIZ_COUNT == quizCount){
            // Show Result
            quizCount = 1
            lifeData.testScore.value = rightAnswerCount
            transition.goToResult.value = true
        }
        else{
            quizCount++
            showNextQuiz()
        }

    }



    companion object {
        @JvmStatic
        fun newInstance() = QuizFrag()
    }

}
