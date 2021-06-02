package com.bangkit.gamastik.ui.main.quiz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bangkit.gamastik.R
import com.bangkit.gamastik.ui.base.BaseFragment
import com.bangkit.gamastik.ui.feature.quiz.quiztest.QuizTestActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_quiz.*

@AndroidEntryPoint
class QuizFragment : BaseFragment() {

    private lateinit var quizViewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        quizViewModel =
            ViewModelProvider(this).get(QuizViewModel::class.java)
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.hide()

        btn_play_quiz.setOnClickListener {
            val intent = Intent(context, QuizTestActivity::class.java)
            startActivity(intent)
        }
    }
}