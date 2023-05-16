package io.yeahx4.spam.io

import io.yeahx4.spam.dto.Tweet
import org.json.JSONObject
import java.io.File
import java.time.LocalDate

class TweetReader(private val path: String) {
    companion object {
        fun parseLocalDate(value: String): LocalDate {
            val tokens = value.split("-").map(String::toInt)
            return LocalDate.of(tokens[0], tokens[1], tokens[2])
        }
    }

    private val content = File(path).readText()

    fun getTweets(): List<Tweet> {
        val json = JSONObject(content)
        val tweets = json.getJSONArray("tweets")
        val len = tweets.length()

        val lists = mutableListOf<Tweet>()

        for (i in 0 until len) {
            val obj = tweets.getJSONObject(i)
            val tweet = Tweet(
                obj.getString("author"),
                obj.getString("content"),
                parseLocalDate(obj.getString("date"))
            )

            lists.add(tweet)
        }

        return lists
    }
}
