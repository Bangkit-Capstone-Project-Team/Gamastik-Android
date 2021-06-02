package com.bangkit.gamastik.ui.feature.quiz.quizscore

import android.os.Bundle
import com.bangkit.gamastik.databinding.ActivityQuizScoreBinding
import com.bangkit.gamastik.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizScoreActivity : BaseActivity() {

    private lateinit var binding : ActivityQuizScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val correctQuestion = intent.getIntExtra(CORRECT_ANSWER, 0)
        val totalScore = correctQuestion * 100

        binding.tvScoreResult.text = totalScore.toString()

        binding.circularProgressBar.apply {
            progressMax = 100f
            setProgressWithAnimation(totalScore.toFloat(), 1000)
        }

    }

    companion object {
        const val TOTAL_QUESTIONS = "total_question"
        const val CORRECT_ANSWER = "correct_answer"
    }
}