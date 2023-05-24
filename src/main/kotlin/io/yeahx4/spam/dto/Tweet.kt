package io.yeahx4.spam.dto

import io.yeahx4.spam.Configuration
import java.time.LocalDate

data class Tweet(
    val author: String,
    val content: String,
    val uploadDate: LocalDate
) {
    override fun toString(): String {
        return """
            
            Author: $author,
            Content: $content,
            Date: $uploadDate
            
        """.trimIndent()
    }

    fun isNews(): Boolean {
        return this.author == "CNN"
                || this.author == "BBC"
                || this.author == "KBS"
    }

    fun isFollower(): Boolean {
        return Configuration.followList.contains(this.author)
    }
}
