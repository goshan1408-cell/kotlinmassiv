fun main() {
    println("=== Задача 4. Пересечение числовых массивов ===")

    print("Введите элементы первого массива через пробел: ")
    val input1 = readlnOrNull()
    print("Введите элементы второго массива через пробел: ")
    val input2 = readLine()

    if (input1.isNullOrEmpty() || input2.isNullOrEmpty()) {
        println("Ошибка: Массивы не должны быть пустыми.")
        return
    }

    // ручной парсинг строк в списки чисел
    val list1 = parseStringToList(input1)
    val list2 = parseStringToList(input2)

    if (list1 == null || list2 == null) {
        println("Ошибка при вводе чисел. Убедитесь, что вводите только цифры через пробел.")
        return
    }

    val result = mutableListOf<Int>()
    // маркер для отслеживания использованных элементов из второго массива
    val visited2 = BooleanArray(list2.size)

    // поиск общих элементов с учетом кратности
    for (i in 0 ..< list1.size) {
        for (j in 0 ..< list2.size) {
            if (list1[i] == list2[j] && !visited2[j]) {
                result.add(list1[i])
                visited2[j] = true
                break
            }
        }
    }

    // простая сортировка результирующего списка пузырьком
    for (i in 0 ..< result.size) {
        for (j in 0 ..< result.size - 1) {
            if (result[j] > result[j + 1]) {
                val temp = result[j]
                result[j] = result[j + 1]
                result[j + 1] = temp
            }
        }
    }

    println("\nРезультат пересечения: $result")
}

// вспомогательная функция ручного разбора числовой строки
fun parseStringToList(str: String): List<Int>? {
    val list = mutableListOf<Int>()
    var currentNumStr = ""

    for (i in 0 ..< str.length) {
        val ch = str[i]
        if (ch == ' ') {
            if (currentNumStr != "") {
                val num = currentNumStr.toIntOrNull() ?: return null
                list.add(num)
                currentNumStr = ""
            }
        } else {
            currentNumStr += ch
        }
    }
    if (currentNumStr != "") {
        val num = currentNumStr.toIntOrNull() ?: return null
        list.add(num)
    }
    return list
}
