package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.Message
import java.io.FileInputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main(){
    val  nowDate =  LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"))
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .setDatabaseUrl(dbUrl)
        .build()
    FirebaseApp.initializeApp(options)

    val message = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """ {
             "userId": 1,
             "userName": "Vasiliy",
             "postId": 1,
             "postAuthor": "Netology"
             }""".trimIndent())
        .setToken(token)
        .build()


    val message1 = Message.builder()
            .putData("action", "POST")
            .putData("content", """ {
                "author": "Попов",
                "published": "${nowDate}",
                "content": "Написана всякая ерунда", 
                "likedByMe":  true ,
                "likes": 0,
                "shares":4,
                "visibles": 8
             }""".trimIndent())
            .setToken(token)
            .build()

    val message2 = Message.builder()
            .putData("action", "ROST")
            .putData("content", """ {
                "author": "Попов",
                "published": "${nowDate}",
                "content": "Написана всякая ерунда", 
                "likedByMe":  true ,
                "likes": 0,
                "shares":4,
                "visibles": 8
             }""".trimIndent())
            .setToken(token)
            .build()

    val message3 = Message.builder()
            .putData("action", "POST")
            .putData("content", """ {
                "author": "Сидоров",
                "published": "${nowDate}",
                "content":"Хотя частота назначения КТ детям по поводу, например, диагностики изолированной головной боли, снижается, 
                            авторы другого исследования пришли к выводу, что этот показатель по-прежнему непозволительно высок  
                            [3] - и многие дети подвергаются процедуре КТ более одного раза [4]
                            Хотя частота назначения КТ детям по поводу, например, диагностики изолированной головной боли, снижается, 
                            авторы другого исследования пришли к выводу, что этот показатель по-прежнему непозволительно высок  
                            [3] - и многие дети подвергаются процедуре КТ более одного раза [4]", 
                "likedByMe":  true ,
                "likes": 0,
                "shares":3,
                "visibles": 7
             }""".trimIndent())
            .setToken(token)
            .build()
    try{
        FirebaseMessaging.getInstance().send(message)
        FirebaseMessaging.getInstance().send(message1)
        FirebaseMessaging.getInstance().send(message2)
        FirebaseMessaging.getInstance().send(message3)
    }
    catch(e: FirebaseMessagingException){
        println("Получатель не найден")
    }

}