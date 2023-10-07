fun main() {
        val def = ' '
        val field = MutableList(3) { MutableList(3) { def } }
        println("---------")
        println("| ${field[0].joinToString(" ")} |")
        println("| ${field[1].joinToString(" ")} |")
        println("| ${field[2].joinToString(" ")} |")
        println("---------")

        var countX = 0
        var countO = 0
        var countFree = 9

        loop@ while (true) {
            while (true) {
                val userInput = readln().toCharArray()
                if (userInput[0].isDigit()) {
                    val row = userInput[0].toString().toInt() - 1
                    val pos = userInput[2].toString().toInt() - 1
                    if (row in 0..2 && pos in 0..2) {
                        if (field[row][pos] == ' ') {
                            field[row][pos] = 'X'
                            countX++
                            countFree--
                            println("---------")
                            println("| ${field[0].joinToString(" ")} |")
                            println("| ${field[1].joinToString(" ")} |")
                            println("| ${field[2].joinToString(" ")} |")
                            println("---------")
                            val winLines =
                                "horizontal ${field[0][0]}${field[0][1]}${field[0][2]} ${field[1][0]}${field[1][1]}${field[1][2]}" +
                                        " ${field[2][0]}${field[2][1]}${field[2][2]} " +

                                        "vertical   ${field[0][0]}${field[1][0]}${field[2][0]} ${field[0][1]}${field[1][1]}${field[2][1]} " +
                                        "${field[0][2]}${field[1][2]}${field[2][2]} " +

                                        "diagonal   ${field[0][0]}${field[1][1]}${field[2][2]} ${field[2][0]}${field[1][1]}${field[0][2]}"
                            val winX = winLines.contains("XXX")
                            val winO = winLines.contains("OOO")
                            if (winX) {
                                println("X wins")
                                break@loop
                            } else if (winO) {
                                println("O wins")
                                break@loop
                            } else if (countFree == 0) {
                                println("Draw")
                                break@loop
                            } else {
                                break
                            }
                        } else if (field[row][pos] == 'X' || field[row][pos] == 'O') {
                            println("This cell is occupied! Choose another one!")
                            continue
                        }
                    } else {
                        println("Coordinates should be from 1 to 3!")
                        continue
                    }
                } else {
                    println("You should enter numbers!")
                    continue
                }

            }

            while (true) {
                val userInput = readln().toCharArray()
                if (userInput[0].isDigit()) {
                    val row = userInput[0].toString().toInt() - 1
                    val pos = userInput[2].toString().toInt() - 1
                    if (row in 0..2 && pos in 0..2) {
                        if (field[row][pos] == ' ') {
                            field[row][pos] = 'O'
                            countO++
                            countFree--
                            println("---------")
                            println("| ${field[0].joinToString(" ")} |")
                            println("| ${field[1].joinToString(" ")} |")
                            println("| ${field[2].joinToString(" ")} |")
                            println("---------")
                            val winLines =
                                "horizontal ${field[0][0]}${field[0][1]}${field[0][2]} ${field[1][0]}${field[1][1]}${field[1][2]}" +
                                        " ${field[2][0]}${field[2][1]}${field[2][2]} " +

                                        "vertical   ${field[0][0]}${field[1][0]}${field[2][0]} ${field[0][1]}${field[1][1]}${field[2][1]} " +
                                        "${field[0][2]}${field[1][2]}${field[2][2]} " +

                                        "diagonal   ${field[0][0]}${field[1][1]}${field[2][2]} ${field[2][0]}${field[1][1]}${field[0][2]}"
                            val winX = winLines.contains("XXX")
                            val winO = winLines.contains("OOO")
                            if (winX) {
                                println("X wins")
                                break@loop
                            } else if (winO) {
                                println("O wins")
                                break@loop
                            } else if (countFree == 0) {
                                println("Draw")
                                break@loop
                            } else {
                                continue@loop
                            }
                        } else if (field[row][pos] == 'X' || field[row][pos] == 'O') {
                            println("This cell is occupied! Choose another one!")
                            continue
                        }
                    } else {
                        println("Coordinates should be from 1 to 3!")
                        continue
                    }
                } else {
                    println("You should enter numbers!")
                    continue
                }
            }

        }
}