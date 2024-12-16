package org.example

import java.nio.file.Files
import java.nio.file.Paths

private val map = HashMap<Pair<Long, Int>, Long>()

private fun blink(num: Long, times: Int): Long {

    if (times <= 0) {
        return 1 // the end
    }

    val pair = Pair(num, times)

    if (map.contains(pair)) {
        return map.getValue(pair)
    }

    if (num == 0L) {
        map[pair] = blink(1, times - 1)
        return map.getValue(pair)
    }

    if (num.toString().length % 2 == 0) {
        val left = num.toString().substring(0, num.toString().length / 2).toLong()
        val right = num.toString().substring(num.toString().length / 2, num.toString().length).toLong()
        return blink(left, times - 1) + blink(right, times - 1)
    }

    map[pair] = blink(num * 2024, times - 1)
    return map.getValue(pair)
}

private fun process(line: String, times: Int) {
    val nums = line.split(" ").map { it.toLong() }
    println(nums)

    var result: Long = 0L

    for (num in nums) {
        result += blink(num, times)
    }

    println(result)
}

private val sample = """
        125 17
    """.trimIndent()

fun main() {
    process(sample, 6)
    process(sample, 25)
    process(fromFile(), 75)
}

private fun fromFile(): String {
    val fileName = "src/main/resources/day11.txt"
    return Files.readString(Paths.get(fileName))
}

