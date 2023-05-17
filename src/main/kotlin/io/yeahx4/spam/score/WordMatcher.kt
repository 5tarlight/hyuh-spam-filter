package io.yeahx4.spam.score

import io.yeahx4.spam.io.WordSet

class WordMatcher(private val text: String, private val set: WordSet) {
    fun countWordSet(): Int {
        var count = 0;

        set
            .toList()
            .stream()
            .forEach { word ->
                count += text
                    .split(" ").joinToString(" ") {
                        it
                            .trim()
                            .lowercase()
                    }
                    .split(word)
                    .count() - 1
            }

        return count
    }
}
