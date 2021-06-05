package com.bangkit.gamastik.ui.feature.quiz.quiztest

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bangkit.gamastik.R
import com.bangkit.gamastik.data.model.quiz.Question
import com.bangkit.gamastik.databinding.ActivityQuizTestBinding
import com.bangkit.gamastik.ui.base.BaseActivity
import com.bangkit.gamastik.ui.feature.quiz.quizscore.QuizScoreActivity
import com.bangkit.gamastik.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizTestActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityQuizTestBinding
    private val quizViewModel: QuizTestViewModel by viewModels()
    private var mQuestionQuiz: ArrayList<Question>? = null
    private var mCurrentPosition: Int = 1
    private var mSelectedChoicePosition: String = ""
    private var mCorrectAnswer: Int = 0
    private var isClickable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        getQuestion()

        binding.tvAnswerA.setOnClickListener(this)
        binding.tvAnswerB.setOnClickListener(this)
        binding.tvAnswerC.setOnClickListener(this)
        binding.tvAnswerD.setOnClickListener(this)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_answer_a -> {
                if (isClickable) {
                    setSelectedChoiceView(binding.tvAnswerA, "A")
                }
            }
            R.id.tv_answer_b -> {
                if (isClickable) {
                    setSelectedChoiceView(binding.tvAnswerB, "B")
                }
            }
            R.id.tv_answer_c -> {
                if (isClickable) {
                    setSelectedChoiceView(binding.tvAnswerC, "C")
                }
            }
            R.id.tv_answer_d -> {
                if (isClickable) {
                    setSelectedChoiceView(binding.tvAnswerD, "D")
                }
            }
            R.id.btn_submit -> {
                if (mSelectedChoicePosition == "") {
                    mCurrentPosition++
                    isClickable = true
                    getAnswer()
                } else {
                    checkAnswer()
                    if (mCurrentPosition == mQuestionQuiz!!.size) {
                        binding.btnSubmit.text = getString(R.string.finish)
                        isClickable = false
                    } else {
                        isClickable = false
                        binding.btnSubmit.text = getString(R.string.next_question)
                    }
                    mSelectedChoicePosition = ""
                }
            }
        }
    }

    private fun getQuestion() {
        quizViewModel.quizQuestion.observe(this, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.apply {
                        pbQuiz.visibility = View.VISIBLE
                        clQuiz.visibility = View.INVISIBLE
                    }
                }
                Resource.Status.SUCCESS -> {
                    binding.apply {
                        pbQuiz.visibility = View.GONE
                        clQuiz.visibility = View.VISIBLE
                    }
                    val data = it.data?.data
                    if (data != null) {
                        mQuestionQuiz = ArrayList()
                        mQuestionQuiz?.addAll(data)
                        setQuestion()
                    }
                }
                Resource.Status.ERROR -> {
                    binding.apply {
                        pbQuiz.visibility = View.GONE
                    }
                    Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setQuestion() {
        val question = mQuestionQuiz!![mCurrentPosition - 1]
        setDefaultChoiceView()
        binding.apply {
            if (mCurrentPosition == mQuestionQuiz!!.size) {
                btnSubmit.text = getString(R.string.finish)
            } else {
                isClickable = true
                btnSubmit.text = getString(R.string.text_submit)
            }
            tvQuizQuestion.text = question.question
            tvAnswerA.text = question.choiceA
            tvAnswerB.text = question.choiceB
            tvAnswerC.text = question.choiceC
            tvAnswerD.text = question.choiceD
        }
    }

    private fun setDefaultChoiceView() {
        val options = ArrayList<TextView>()

        options.add(0, binding.tvAnswerA)
        options.add(1, binding.tvAnswerB)
        options.add(2, binding.tvAnswerC)
        options.add(3, binding.tvAnswerD)

        binding.btnSubmit.setOnClickListener(this)

        for (option in options) {
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.bg_choice_default)
        }
    }

    private fun setSelectedChoiceView(tv: TextView, selectedChoice: String) {
        setDefaultChoiceView()
        mSelectedChoicePosition = selectedChoice
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.bg_choice_selected)
    }

    private fun answerView(answer: String?, drawableView: Int) {
        when (answer) {
            "A" -> {
                binding.tvAnswerA.background = ContextCompat.getDrawable(this, drawableView)
            }
            "B" -> {
                binding.tvAnswerB.background = ContextCompat.getDrawable(this, drawableView)
            }
            "C" -> {
                binding.tvAnswerC.background = ContextCompat.getDrawable(this, drawableView)
            }
            "D" -> {
                binding.tvAnswerD.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun getAnswer() {
        if (mCurrentPosition <= mQuestionQuiz!!.size) {
            setQuestion()
        } else {
            val intent = Intent(this, QuizScoreActivity::class.java)
            intent.putExtra(QuizScoreActivity.CORRECT_ANSWER, mCorrectAnswer)
            intent.putExtra(QuizScoreActivity.TOTAL_QUESTIONS, mQuestionQuiz!!.size)
            startActivity(intent)
            finish()
        }
    }

    private fun checkAnswer() {
        val question = mQuestionQuiz?.get(mCurrentPosition - 1)
        if (question!!.answer!!.uppercase() != mSelectedChoicePosition.uppercase()) {
            answerView(mSelectedChoicePosition, R.drawable.bg_choice_wrong)
        } else {
            mCorrectAnswer++
        }
        answerView(question.answer!!.uppercase(), R.drawable.bg_choice_correct)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}