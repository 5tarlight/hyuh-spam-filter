package io.yeahx4.spam

import io.yeahx4.spam.dto.Tweet
import io.yeahx4.spam.io.TweetReader
import io.yeahx4.spam.io.WordSet
import io.yeahx4.spam.score.WordMatcher

fun main() {
    val adWords = WordSet("./db/ads.txt")
    val scamWords = WordSet("./db/scam.txt")
    val tweets = TweetReader("./db/tweets.json")

//    println(adWords.toString())
//    println(scamWords.toString())
//    tweets.getTweets().forEach {
//        println(it.toString())
//    }

//    val adCounter = WordMatcher(tweets.getTweets().first().content, adWords)
//    println(adCounter.countWordSet())

    val result: List<Pair<Tweet, Int>> = tweets
        .getTweets()
        .map { tweet ->
            val ad = -WordMatcher(tweet.content, adWords).countWordSet() * Configuration.adScoreModifier
            val scam = -WordMatcher(tweet.content, scamWords).countWordSet() * Configuration.scamScoreModifier

            val score = ad + scam

            Pair(tweet, score)
        }

    result.forEach {
        println("${it.first.content} ${it.second}")
    }
}
