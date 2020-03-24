package io

import java.io.File

class Read {
    companion object {
        val charactersAllowed = mutableListOf<Char>().apply {
            addAll('a'..'z')
            addAll('A'..'Z')
            addAll('0'..'9')
            addAll(listOf('=', '{', '}', '[', ']', '(', ')'))
        }

        fun readFromFile(path: String): Map<String, Any> {
            val fileText = File(path).readText()
            val filteredText = fileText.filter { it in charactersAllowed }
            return emptyMap()
        }
    }
}