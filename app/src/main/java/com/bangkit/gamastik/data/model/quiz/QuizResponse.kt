package com.bangkit.gamastik.data.model.quiz

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuizResponse(

    @field:SerializedName("data")
    val data: List<Question>? = null,

    @field:SerializedName("message")
    val message: String? = null
) : Parcelable

@Parcelize
data class Question(

    @field:SerializedName("number_question")
    val numberQuestion: Int? = null,

    @field:SerializedName("question")
    val question: String? = null,

    @field:SerializedName("choice_a")
    val choiceA: String? = null,

    @field:SerializedName("choice_b")
    val choiceB: String? = null,

    @field:SerializedName("choice_c")
    val choiceC: String? = null,

    @field:SerializedName("choice_d")
    val choiceD: String? = null,

    @field:SerializedName("answer")
    val answer: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("id")
    val id: Int? = null

) : Parcelable
