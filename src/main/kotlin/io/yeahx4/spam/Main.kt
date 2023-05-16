package io.yeahx4.spam

import io.yeahx4.spam.io.TweetReader
import io.yeahx4.spam.io.WordSet

fun main() {
    val adWords = WordSet("./db/ads.txt")
    val scamWords = WordSet("./db/scam.txt")
    val tweets = TweetReader("./db/tweets.json")

    println(adWords.toString())
    println(scamWords.toString())
    tweets.getTweets().forEach {
        println(it.toString())
    }
}
