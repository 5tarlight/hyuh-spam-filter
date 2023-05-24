package io.yeahx4.spam.score

import io.yeahx4.spam.Configuration
import io.yeahx4.spam.dto.Tweet
import io.yeahx4.spam.io.WordSet

class TweetScore(private val tweet: Tweet, private val words: List<Pair<WordSet, Int>>) {
    fun score(): Int {
        if (tweet.isNews())
            return Configuration.newsStaticScore

        var score = 0

        words.forEach {
            val result = WordMatcher(tweet.content, it.first).countWordSet() * it.second
            score += result
        }

        if (score < 0)
            AuthorScore.alterReputation(tweet.author, score)
        else
            AuthorScore.alterReputation(tweet.author, 1)

        return score
    }
}
