fun main() {
    println("=== Задача 3. Шифрование текста ключевым словом ===")

    // базовый русский алфавит
    val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ"

    // карта числовых значений для каждой буквы из таблицы
    val customOrder = intArrayOf(
        21, 13, 4, 20, 22, 1, 25, 12, 24, 14, 2, 28, 9, 23, 3, 29,
        6, 16, 15, 11, 26, 5, 30, 27, 8, 18, 10, 33, 31, 32, 19, 7, 17
    )

    print("Введите ключевое слово (только русские буквы): ")
    val keywordInput = readlnOrNull()?.uppercase()
    if (keywordInput == null || keywordInput == "") {
        println("Ошибка: Ключевое слово не должно быть пустым.")
        return
    }

    print("Введите исходный текст для шифрования: ")
    val textInput = readlnOrNull()?.uppercase()
    if (textInput == null || textInput == "") {
        println("Ошибка: Текст не должен быть пустым.")
        return
    }

    // валидация символов ключа
    for (i in 0 ..< keywordInput.length) {
        if (keywordInput[i] !in alphabet) {
            println("Ошибка: Ключевое слово должно содержать только русские буквы.")
            return
        }
    }

    var encryptedText = ""
    var keywordIndex = 0

    for (i in 0 ..< textInput.length) {
        val originalChar = textInput[i]

        if (originalChar in alphabet) {
            val keyChar = keywordInput[keywordIndex % keywordInput.length]

            // получаем величину сдвига из номера буквы ключевого слова
            val keyAlphaIndex = alphabet.indexOf(keyChar)
            val shift = customOrder[keyAlphaIndex]

            // находим числовое значение текущей буквы текста по таблице
            val currentCharIndex = alphabet.indexOf(originalChar)
            val currentCustomValue = customOrder[currentCharIndex]

            // высчитываем новое зашифрованное числовое значение (закольцованность от 1 до 33)
            var newCustomValue = (currentCustomValue + shift) % 33
            if (newCustomValue == 0) {
                newCustomValue = 33
            }

            // находим, какой букве соответствует полученное числовое значение
            var foundChar = ' '
            for (j in 0 ..< customOrder.size) {
                if (customOrder[j] == newCustomValue) {
                    foundChar = alphabet[j]
                    break
                }
            }

            encryptedText += foundChar
            keywordIndex++
        } else {
            encryptedText += originalChar // сохраняем пробелы
        }
    }

    println("\nЗашифрованный текст: $encryptedText")
}
