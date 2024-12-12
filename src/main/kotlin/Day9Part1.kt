package org.example

import java.nio.file.Files
import java.nio.file.Paths

private fun process(line: String) {
    val nums = line.toCharArray().map { it.toString() }
    val array = ArrayList<String>()
    // val sb = StringBuilder()
    var p = 0

    for (i in 0 until nums.size) {
        if (i % 2 == 0) {
            for (n in 0 until nums[i].toInt()) {
                // sb.append(p.toString())
                array.add(p.toString())
            }
            p++;
        } else {
            for (n in 0 until nums[i].toInt()) {
                // sb.append(".")
                array.add(".")
            }
        }
    }

    // val disk = sb.toString().toCharArray()

    var i = 0
    var j = array.size - 1

    // . 9 swap
    // . .
    // 9 .
    // 9 9

//    println(array)

    while (i < j) {
        if (array[i] == "." && array[j] != ".") {
            array[i] = array[j]
            array[j] = "."
            i++
            j--
        } else if (array[i] == "." && array[j] == ".") {
            j--
        } else if (array[i] != "." && array[j] == ".") {
            i++
            j--
        } else {
            i++
        }
    }

//    println(array)

    var result: Long = 0

    for (i in 0 until array.size) {
        if (array[i] != ".") {
            result += (i * array[i].toLong())
            // println(result)
        }
    }

    println(result)
}

private val sample = """
        2333133121414131402
    """.trimIndent()

fun main() {
    process("12345")
    process("1010101010101010101010")
    process(sample)
    process(fromFile())
}

private fun fromFile(): String {
    val fileName = "src/main/resources/day9.txt"
    return Files.readString(Paths.get(fileName))
}

