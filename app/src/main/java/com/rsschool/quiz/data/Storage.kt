package com.rsschool.quiz.data

object Storage {

    val questions: List<QuestionEntity> =
        listOf(firstQuestion, secondQuestion, thirdQuestion, fourthQuestion, fifthQuestion)

    val userAnswerIds = MutableList(questions.size) { -1 }

    private val firstQuestion get() = QuestionEntity(
        id = 1,
        title = "В какой версии Android Studio начал нативно поддерживаться Kotlin?",
        answerVariants = listOf("2.3.3", "3.0", "3.6", "4.0", "4.1"),
        answerCorrect = 0
    )

    private val secondQuestion get() = QuestionEntity(
        id = 2,
        title = "В каком году права на обладание островом Kotlin получили одновременно " +
                "Новгородское Княжество и Швеция?",
        answerVariants = listOf("1427 г.", "1246 г.", "1323 г.", "1589 г.", "1116 г."),
        answerCorrect = 2
    )

    private val thirdQuestion get() = QuestionEntity(
        id = 3,
        title = "Какое утверждение относительно языка Kotlin является неверным?",
        answerVariants = listOf(
            "Язык статически типизирован, на нем код читается и пишется проще",
            "Если написать одну программу под Java и под Kotlin, то меньше времени займет второй",
            "В Java нужно написать больше кода для обработки NullPointerException, чем в Kotlin",
            "Язык Kotlin придумали в России",
            "В языке Kotlin можно объявить два вида переменных - " +
                    "иммутабельный var и мутабельный val"
        ),
        answerCorrect = 4
    )

    private val fourthQuestion get() = QuestionEntity(
        id = 4,
        title = "Сколько примерно необходимо преодолеть километров по прямой чтобы из острова " +
                "Котлин попасть в Маунтин-Вью (Сан-Франциско)?",
        answerVariants = listOf("4600 км", "10100 км", "7600 км", "8900 км", "5900 км"),
        answerCorrect = 3
    )

    private val fifthQuestion get() = QuestionEntity(
        id = 5,
        title = "В каком классе языка Java имеется функция canExecute() без входных параметров и " +
                "возвращающая результат типа Boolean?",
        answerVariants = listOf("DatePicker", "File", "ContentResolver", "String", "Activity"),
        answerCorrect = 1
    )
}