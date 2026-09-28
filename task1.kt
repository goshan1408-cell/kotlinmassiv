fun main() {
    println("=== Задача 1. Анализ двумерного массива ===")

    print("Введите количество строк: ")
    val rows = readlnOrNull()?.toIntOrNull()
    print("Введите количество столбцов: ")
    val cols = readlnOrNull()?.toIntOrNull()

    if (rows == null || rows <= 0 || cols == null || cols <= 0) {
        println("Ошибка: Размеры массива должны быть целыми числами больше 0.")
        return
    }

    // создание матрицы
    val matrix = Array(rows) { IntArray(cols) }
    // массив для отслеживания использованных цифр (от '0' до '9')
    val usedDigits = BooleanArray(10)

    println("Введите элементы массива (трехзначные числа):")
    for (i in 0 ..< rows) {
        for (j in 0 ..< cols) {
            print("Элемент [$i][$j]: ")
            val input = readlnOrNull()
            val number = input?.toIntOrNull()

            // проверка на трехзначное число (включая отрицательные)
            if (number == null || number < 100 || number > 999) {
                println("Ошибка: Необходимо ввести трехзначное число от 100 до 999.")
                return
            }

            matrix[i][j] = number

            // фиксируем цифры, из которых состоит число
            for (k in 0 ..< input.length) {
                val char = input[k]
                if (char in '0'..'9') {
                    val digitIndex = char - '0'
                    usedDigits[digitIndex] = true
                }
            }
        }
    }

    // подсчет уникальных цифр
    var uniqueDigitsCount = 0
    for (i in 0 ..< 10) {
        if (usedDigits[i]) {
            uniqueDigitsCount++
        }
    }

    // вывод матрицы на экран
    println("\nВведенный массив:")
    for (i in 0 ..< rows) {
        for (j in 0 ..< cols) {
            print("${matrix[i][j]}\t")
        }
        println()
    }

    println("\nРезультат: В массиве использовано $uniqueDigitsCount различных цифр")
}
