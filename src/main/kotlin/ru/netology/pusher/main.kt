package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.Message
import java.io.FileInputStream

fun main(){
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
                "published": "вчера",
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
                "published": "вчера",
                "content": "Написана всякая ерунда", 
                "likedByMe":  true ,
                "likes": 0,
                "shares":4,
                "visibles": 8
             }""".trimIndent())
            .setToken(token)
            .build()

    try{
        FirebaseMessaging.getInstance().send(message)
        FirebaseMessaging.getInstance().send(message1)
        FirebaseMessaging.getInstance().send(message2)
    }
    catch(e: FirebaseMessagingException){
        println("Получатель не найден")
    }

}