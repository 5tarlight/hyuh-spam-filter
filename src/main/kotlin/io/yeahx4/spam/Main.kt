package io.yeahx4.spam

import io.yeahx4.spam.score.AuthorScore
import io.yeahx4.spam.system.Recommendation
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    println("1. Auto Recommendation")
    println("2. Full Tweets")
    println("3. Author Population")
    println("4. Quit")
    print("> ")
    System.out.flush()

    val input = scanner.nextInt()
    System.out.flush()

    when (input) {
        1 -> {
            val recommendation = Recommendation.done(5)

            recommendation.forEach {
                println("${it.first.content} ${it.second}")
            }
        }
        2 -> {
            val tweets = Recommendation.scoreTweet()

            tweets.forEach {
                println("${it.first.content} ${it.second}")
            }
        }
        3 -> {
            Recommendation.done()
            AuthorScore.reputation.forEach {
                println("${it.key} : ${it.value}")
            }
        }
    }

    println()
    println()
}
