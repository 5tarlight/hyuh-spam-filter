package io.yeahx4.spam.system

import io.yeahx4.spam.dto.Tweet
import io.yeahx4.spam.score.AuthorScore
import java.util.stream.Collectors

class Recommendation(private val scores: List<Pair<Tweet, Int>>) {
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
