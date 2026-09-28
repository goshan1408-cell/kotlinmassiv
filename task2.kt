fun main() {
    println("=== Задача 2. Проверка симметрии матрицы 5х5 ===")
    val size = 5
    val matrix = Array(size) { IntArray(size) }

    println("Заполните матрицу 5 на 5 целыми числами через пробел для каждой строки:")
    for (i in 0 ..< size) {
        print("Строка ${i + 1}: ")
        val input = readlnOrNull()
        if (input == null || input == "") {
            println("Ошибка: Строка не должна быть пустой.")
            return
        }

        // ручной разбор строки по пробелам
        var spaceCount = 0
        val parts = Array(size) { "" }
        for (k in 0 ..< input.length) {
            val ch = input[k]
            if (ch == ' ') {
                if (k > 0 && input[k - 1] != ' ') {
                    spaceCount++
                }
            } else {
                if (spaceCount < size) {
                    parts[spaceCount] += ch
                }
            }
        }

        // валидация количества чисел в строке
        if (parts[size - 1] == "") {
            println("Ошибка: В строке должно быть ровно 5 чисел через пробел.")
            return
        }

        // заполнение матрицы
        for (j in 0 ..< size) {
            val num = parts[j].toIntOrNull()
            if (num == null) {
                println("Ошибка: Элемент '${parts[j]}' не является целым числом.")
                return
            }
            matrix[i][j] = num
        }
    }

    // проверка симметрии относительно главной диагонали
    var isSymmetric = true
    for (i in 0 ..< size) {
        for (j in i + 1 ..< size) {
            if (matrix[i][j] != matrix[j][i]) {
                isSymmetric = false
            }
        }
    }

    if (isSymmetric) {
        println("\nРезультат: Массив является симметричным относительно главной диагонали")
    } else {
        println("\nРезультат: Массив НЕ является симметричным относительно главной диагонали")
    }
}
