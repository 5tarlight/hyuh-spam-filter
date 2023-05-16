package io.yeahx4.spam.io

import java.io.File

class WordSet(private val path: String) {
    private val words: List<String> = File(path).readLines()

    fun readWords():  List<String> = words

    override fun toString(): String {
        val sb = StringBuilder("============ Word Set ============\n")
        sb.append("Length : ${this.words.size}\n")
        sb.append(words.joinToString(", ", "{ ", " }\n"))
        sb.append("==================================")

        return sb.toString()
    }
}
