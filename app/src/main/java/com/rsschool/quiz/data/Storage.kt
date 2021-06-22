package com.rsschool.quiz.data

object Storage {

    val questions: List<QuestionEntity> =
        listOf(firstQuestion, secondQuestion, thirdQuestion, fourthQuestion, fifthQuestion)

    val userAnswerIds = MutableList(questions.size) { -1 }

    private val firstQuestion get() = QuestionEntity(
        id = 1,
        title = "In which version of Android Studio was Kotlin natively supported?",
        answerVariants = listOf("2.3.3", "3.0", "3.6", "4.0", "4.1"),
        answerCorrect = 0
    )

    private val secondQuestion get() = QuestionEntity(
        id = 2,
        title = "In what year did the Principality of Novgorod and Sweden receive the rights to " +
                "own the Kotlin Island at the same time?",
        answerVariants = listOf("1427", "1246", "1323", "1589", "1116"),
        answerCorrect = 2
    )

    private val thirdQuestion get() = QuestionEntity(
        id = 3,
        title = "Which statement about the Kotlin language is wrong?",
        answerVariants = listOf(
            "The language is statically typed, it makes the code easier to read and write",
            "If you write one program for Java and for Kotlin, then the second will take less time.",
            "Java needs to write more code to handle NullPointerException than Kotlin",
            "The Kotlin language was invented in Russia",
            "In the Kotlin language, you can declare two kinds of variables" +
                    " - immutable var and mutable val"
        ),
        answerCorrect = 4
    )

    private val fourthQuestion get() = QuestionEntity(
        id = 4,
        title = "How many kilometers do you need to travel in a straight line to get from " +
                "Kotlin Island to Mountain View (San Francisco)?",
        answerVariants = listOf("4600 km", "10100 km", "7600 km", "8900 km", "5900 km"),
        answerCorrect = 3
    )

    private val fifthQuestion get() = QuestionEntity(
        id = 5,
        title = "Which Java language class has the canExecute() function without" +
                " input parameters and returning a Boolean result?",
        answerVariants = listOf("DatePicker", "File", "ContentResolver", "String", "Activity"),
        answerCorrect = 1
    )
}