package com.bangkit.gamastik.utils

import com.bangkit.gamastik.data.model.quiz.Question

object Constants {
    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val question1 = Question(
            1,
            "Which internet company began life as an online bookstore called 'Cadabra' ?",
            "ebay",
            "Shopify",
            "Amazon",
            "Overstock",
            "A",
            "",
            "",
            1
        )
        questionsList.add(question1)

        val question2 = Question(
            1,
            "Which of the following languages is used as a scripting language in the Unity 3D game engine?",
            "Java",
            "C#",
            "C++",
            "Objective-C",
            "B",
            "",
            "",
            1
        )
        questionsList.add(question2)

        val question3 = Question(
            1,
            "Which of these people was NOT a founder of Apple Inc?",
            "Jonathan Ive",
            "Steve Jobs",
            "Ronald Wayne",
            "Steve Wozniak",
            "A",
            "",
            "",
            1
        )
        questionsList.add(question3)

        val question4 = Question(
            1,
            "What does the term GPU stand for?",
            "Graphite Producing Unit",
            "Gaming Processor Unit",
            "Graphical Proprietary Unit",
            "Graphics Processing Unit",
            "D",
            "",
            "",
            1
        )
        questionsList.add(question4)

        val question5 = Question(
            1,
            "Moore's law originally stated that the number of transistors on a microprocessor chip would double every...",
            "Year",
            "Four Years",
            "Two Years",
            "Eight Years",
            "A",
            "",
            "",
            1
        )
        questionsList.add(question5)

        val question6 = Question(
            1,
            "What five letter word is the motto of the IBM Computer company?",
            "Click",
            "Logic",
            "Pixel",
            "Think",
            "D",
            "",
            "",
            1
        )
        questionsList.add(question6)

        val question7 = Question(
            1,
            "In programming, the ternary operator is mostly defined with what symbol(s)?",
            "??",
            "if then",
            "?:",
            "?",
            "C",
            "",
            "",
            1
        )
        questionsList.add(question7)

        return questionsList
    }
}