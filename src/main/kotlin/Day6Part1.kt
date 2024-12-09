package org.example

import java.nio.file.Files
import java.nio.file.Paths

private fun process(lines: List<String>) {

    val rows = lines.size
    val cols = lines.first().length
    val array = Array(rows) { Array(cols) { '.' } }
    var m = 0
    var n = 0

    for (i in array.indices) {            // Loop over rows
        for (j in array[i].indices) {     // Loop over columns
            array[i][j] = lines.get(i).toCharArray().get(j)
            if (array[i][j] == '^' || array[i][j] == 'v' || array[i][j] == '<' || array[i][j] == '>') {
                m = i
                n = j
            }
        }
    }

//    array.forEach { row ->
//        row.forEach { print(it) }
//        println() // Move to next line after each row
//    }

    var visited = HashSet<String>();

    // up, right, down, left
    val directions = arrayOf(intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1))
    var currentDirection = 0;

    while (true) {
        // Add current position to visited set
        visited.add("$m,$n")

        // Calculate next position
        val nextRow: Int = m + directions.get(currentDirection).get(0)
        val nextCol: Int = n + directions.get(currentDirection).get(1)

        // Check if out of bounds
        if (nextRow < 0 || nextRow >= array.size || nextCol < 0 || nextCol >= array[0].size) {
            break
        }

        // Check if there's an obstacle
        if (array[nextRow][nextCol] == '#') {
            // Turn right
            currentDirection = (currentDirection + 1) % 4
        } else {
            // Move forward
            m = nextRow
            n = nextCol
        }
    }

    println(visited.size)
}

private val sample = """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
    """.trimIndent()

fun main() {
    process(sample.lines())
    process(fromFile())
}

private fun fromFile(): List<String> {
    val fileName = "src/main/resources/day6.txt"
    return Files.readAllLines(Paths.get(fileName))
}


