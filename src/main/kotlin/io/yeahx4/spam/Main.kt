package io.yeahx4.spam

import io.yeahx4.spam.dto.Tweet
import io.yeahx4.spam.io.TweetReader
import io.yeahx4.spam.io.WordSet
import io.yeahx4.spam.score.AuthorScore
import io.yeahx4.spam.score.TweetScore
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

    val wordPair = listOf<Pair<WordSet, Int>>(
        Pair(adWords, Configuration.adScoreModifier),
        Pair(scamWords, Configuration.scamScoreModifier)
    )

    val result: List<Pair<Tweet, Int>> = tweets
        .getTweets()
        .map { tweet ->
            Pair(tweet, TweetScore(tweet, wordPair).score())
        }

    result.forEach {
        println("${it.first.content} ${it.second}")
    }

    println(AuthorScore.getReputation("돈을버는가장빠르고확실한방법"))
}
