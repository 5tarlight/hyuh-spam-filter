package io.yeahx4.spam.score

class AuthorScore {
    companion object {
        @JvmStatic
        private var reputation = mutableMapOf<String, Int>()

        fun alterReputation(author: String, score: Int) {
            if (reputation.containsKey(author)) {
                reputation[author] = reputation[author]!! + score
            } else {
                reputation[author] = score
            }
        }

        fun getReputation(author: String): Int {
            return if (reputation.containsKey(author)) {
                reputation[author]!!
            } else {
                reputation[author] = 0
                0
            }
        }
    }
}
