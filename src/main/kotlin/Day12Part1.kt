package org.example

import java.nio.file.Files
import java.nio.file.Paths

private fun process(lines: List<String>) {
    saw = HashSet() // clean up the cache

    val rows = lines.size
    val cols = lines.first().length
    val array = Array(rows) { Array(cols) { '.' } }

    for (i in array.indices) {
        for (j in array[i].indices) {
            array[i][j] = lines.get(i).toCharArray().get(j)
        }
    }

//    array.forEach { row ->
//        row.forEach { print(it) }
//        println() // Move to next line after each row
//    }

    var result = 0

    for (i in array.indices) {
        for (j in array[i].indices) {
            val f = find(array, i, j)
            result += f.first * f.second
        }
    }

    // up, right, down, left
    // val directions = arrayOf(intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1))

    println(result)
}

var saw = HashSet<Pair<Int, Int>>()

// area / edge
private fun find(array: Array<Array<Char>>, i: Int, j: Int): Pair<Int, Int> {
    if (saw.contains(Pair(i, j))) {
        return Pair(0, 0)
    }

    saw.add(Pair(i, j))

    val c = array[i][j]
    var area = 0
    var edge = 0

    // left
    if (j - 1 >= 0 && array[i][j - 1] == c) {
        val f = find(array, i, j - 1)
        area += f.first
        edge += f.second
    }

    // right
    if (j + 1 < array[i].size && array[i][j + 1] == c) {
        val f = find(array, i, j + 1)
        area += f.first
        edge += f.second
    }

    // up
    if (i - 1 >= 0 && array[i - 1][j] == c) {
        val f = find(array, i - 1, j)
        area += f.first
        edge += f.second
    }

    // down
    if (i + 1 < array.size && array[i + 1][j] == c) {
        val f = find(array, i + 1, j)
        area += f.first
        edge += f.second
    }

    // ----

    // left
    if (j - 1 < 0 || array[i][j - 1] != c) {
        edge += 1
    }

    // right
    if (j + 1 >= array[i].size || array[i][j + 1] != c) {
        edge += 1
    }

    // up
    if (i - 1 < 0 || array[i - 1][j] != c) {
        edge += 1
    }

    // down
    if (i + 1 >= array.size || array[i + 1][j] != c) {
        edge += 1
    }

    return Pair(area + 1, edge)
}

private val sample1 = """
        AAAA
        BBCD
        BBCC
        EEEC
    """.trimIndent()

private val sample2 = """
        OOOOO
        OXOXO
        OOOOO
        OXOXO
        OOOOO
    """.trimIndent()

private val sample3 = """
        RRRRIICCFF
        RRRRIICCCF
        VVRRRCCFFF
        VVRCCCJFFF
        VVVVCJJCFE
        VVIVCCJJEE
        VVIIICJJEE
        MIIIIIJJEE
        MIIISIJEEE
        MMMISSJEEE
    """.trimIndent()

fun main() {
    process(sample1.lines())
    process(sample2.lines())
    process(sample3.lines())
    process(fromFile())
}

private fun fromFile(): List<String> {
    val fileName = "src/main/resources/day12.txt"
    return Files.readAllLines(Paths.get(fileName))
}
