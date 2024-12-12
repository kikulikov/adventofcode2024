package org.example

import java.nio.file.Files
import java.nio.file.Paths

private fun process(lines: List<String>) {

    val rows = lines.size
    val cols = lines.first().length
    val map = HashMap<Char, ArrayList<Pair<Int, Int>>>()

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            val ch = lines[i].toCharArray()[j]
            if (ch != '.') {
                val pairs = map.getOrPut(ch) { ArrayList() }
                pairs.add(Pair(i, j))
                map.put(ch, pairs)
            }
        }
    }

//    map.forEach { row ->
//        row.value.forEach { print(it) }
//        println() // Move to next line after each row
//    }

    val nodes = HashSet<Pair<Int, Int>>()

    map.forEach { type ->
        for (i in 0 until type.value.size - 1) {
            for (j in i + 1 until type.value.size) {
                val prev = type.value[i]
                val curr = type.value[j]
                val ver = curr.first - prev.first
                val hor = curr.second - prev.second

                nodes.add(Pair(prev.first, prev.second))
                nodes.add(Pair(curr.first, curr.second))

                var t1 = prev.first - ver
                var t2 = prev.second - hor

                while (t1 >= 0 && t1 < rows && t2 >= 0 && t2 < cols) {
                    nodes.add(Pair(t1, t2))
                    t1 -= ver
                    t2 -= hor
                }

                t1 = curr.first + ver
                t2 = curr.second + hor

                while (t1 >= 0 && t1 < rows && t2 >= 0 && t2 < cols) {
                    nodes.add(Pair(t1, t2))
                    t1 += ver
                    t2 += hor
                }

//                nodes.add(Pair(prev.first - ver, prev.second - hor))
//                nodes.add(Pair(curr.first + ver, curr.second + hor))
            }
        }
    }

    println(nodes.sortedBy { it.first }.size)
}

private val sample = """
        ............
        ........0...
        .....0......
        .......0....
        ....0.......
        ......A.....
        ............
        ............
        ........A...
        .........A..
        ............
        ............
    """.trimIndent()

fun main() {
    process(sample.lines())
    process(fromFile())
}

private fun fromFile(): List<String> {
    val fileName = "src/main/resources/day8.txt"
    return Files.readAllLines(Paths.get(fileName))
}