package io.yeahx4.spam

import io.yeahx4.spam.io.WordSet

fun main() {
    val adWords = WordSet("./db/ads.txt")

    println(adWords.toString())
}
