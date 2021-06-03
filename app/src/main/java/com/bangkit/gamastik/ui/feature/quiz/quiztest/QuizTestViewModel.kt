package com.bangkit.gamastik.ui.feature.quiz.quiztest

import androidx.lifecycle.ViewModel
import com.bangkit.gamastik.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizTestViewModel @Inject constructor(
    repository: AppRepository
): ViewModel() {

    val quizQuestion = repository.getQuizQuestion()

}