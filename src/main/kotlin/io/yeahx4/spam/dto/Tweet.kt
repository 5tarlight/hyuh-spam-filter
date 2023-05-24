package io.yeahx4.spam.dto

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
}
