package io.yeahx4.spam

class Configuration {
    companion object {
        const val adScoreModifier = -3
        const val scamScoreModifier = -5
        const val newsStaticScore = 10
        const val reputationScore = 5
        val followList = listOf(
            "follower1", "follower2", "follower3"
        )
    }
}
