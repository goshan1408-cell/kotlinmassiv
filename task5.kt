fun main() {
    println("=== Задача 5. Группировка слов по составу букв ===")
    print("Введите слова через пробел: ")
    val input = readlnOrNull()

    if (input.isNullOrEmpty()) {
        println("Ошибка: Ввод пуст.")
        return
    }

    // ручное разделение строки на отдельные слова
    val words = mutableListOf<String>()
    var currentWord = ""
    for (i in 0 ..< input.length) {
        val ch = input[i]
        if (ch == ' ') {
            if (currentWord != "") {
                words.add(currentWord)
                currentWord = ""
            }
        } else {
            currentWord += ch
        }
    }
    if (currentWord != "") {
        words.add(currentWord)
    }

    // массив маркеров для отслеживания сгруппированных слов
    val visited = BooleanArray(words.size)

    println("\nРезультат группировки:")
    for (i in 0 ..< words.size) {
        if (!visited[i]) {
            val group = mutableListOf<String>()
            group.add(words[i])
            visited[i] = true

            // ищем слова с точно таким же набором букв
            for (j in i + 1 ..< words.size) {
                if (!visited[j] && hasSameLetters(words[i], words[j])) {
                    group.add(words[j])
                    visited[j] = true
                }
            }

            // красивый вывод группы слов
            for (k in 0 ..< group.size) {
                print("\"${group[k]}\"${if (k < group.size - 1) ", " else ""}")
            }
            println()
        }
    }
}

// функция проверки, состоят ли два слова из одинаковых букв
fun hasSameLetters(word1: String, word2: String): Boolean {
    if (word1.length != word2.length) return false

    val chars1 = word1.toCharArray()
    val chars2 = word2.toCharArray()

    // ручная сортировка пузырьком букв первого слова
    for (i in 0 ..< chars1.size) {
        for (j in 0 ..< chars1.size - 1) {
            if (chars1[j] > chars1[j + 1]) {
                val temp = chars1[j]
                chars1[j] = chars1[j + 1]
                chars1[j + 1] = temp
            }
        }
    }

    // ручная сортировка пузырьком букв второго слова
    for (i in 0 ..< chars2.size) {
        for (j in 0 ..< chars2.size - 1) {
            if (chars2[j] > chars2[j + 1]) {
                val temp = chars2[j]
                chars2[j] = chars2[j + 1]
                chars2[j + 1] = temp
            }
        }
    }

    // посимвольное сравнение двух отсортированных массивов букв
    for (i in 0 ..< chars1.size) {
        if (chars1[i] != chars2[i]) return false
    }
    return true
}