package io.yeahx4.spam.system

import io.yeahx4.spam.Configuration
import io.yeahx4.spam.dto.Tweet
import io.yeahx4.spam.io.TweetReader
import io.yeahx4.spam.io.WordSet
import io.yeahx4.spam.score.AuthorScore
import io.yeahx4.spam.score.TweetScore
import java.util.stream.Collectors

class Recommendation(private val scores: List<Pair<Tweet, Int>>) {
    companion object {
        fun scoreTweet(): List<Pair<Tweet, Int>> {
            val adWords = WordSet("./db/ads.txt")
            val scamWords = WordSet("./db/scam.txt")
            val tweets = TweetReader("./db/tweets.json")

            val wordPair = listOf(
                Pair(adWords, Configuration.adScoreModifier),
                Pair(scamWords, Configuration.scamScoreModifier)
            )

            val result: List<Pair<Tweet, Int>> = tweets
                .getTweets()
                .map { tweet ->
                    Pair(tweet, TweetScore(tweet, wordPair).score())
                }

            println("${result.size} tweets loaded successfully.\n")

            return result
        }

        fun done(amount: Int = 10): List<Pair<Tweet, Int>> {
            val result = scoreTweet()
            return Recommendation(result).recommend(amount)
        }
    }

    fun recommend(amount: Int = 10): List<Pair<Tweet, Int>> {
        var sort = scores
            .stream()
            .map {
                val tweet = it.first
                var score = it.second

                score += AuthorScore.getReputation(tweet.author)

                Pair(tweet, score)
            }
            .sorted { t1, t2 -> t2.second - t1.second }
            .collect(Collectors.toList())

        val followed = scores
            .filter { it.first.isFollower() }
            .random()

        sort = if (sort.size < amount) {
            sort
        } else {
            sort.slice(0 until amount - 1)
        }

        sort.add(0, followed)

        return sort
    }
}
