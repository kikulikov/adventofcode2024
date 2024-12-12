package org.example

import java.nio.file.Files
import java.nio.file.Paths

private fun process(line: String) {
    val nums = line.toCharArray().map { it.toString() }
    val disk = ArrayList<String>()
    var p = 0

    for (i in 0 until nums.size) {
        if (i % 2 == 0) {
            for (n in 0 until nums[i].toInt()) {
                disk.add(p.toString())
            }
            p++;
        } else {
            for (n in 0 until nums[i].toInt()) {
                disk.add(".")
            }
        }
    }

    val groups = mutableListOf<MutableList<String>>()
    var currentGroup = mutableListOf<String>()

    for (element in disk) {
        if (currentGroup.isEmpty() || currentGroup.last() == element) {
            currentGroup.add(element)
        } else {
            groups.add(currentGroup)
            currentGroup = mutableListOf(element)
        }
    }

    // Add the last group
    if (currentGroup.isNotEmpty()) {
        groups.add(currentGroup)
    }

    // println(grouped)
    var index = groups.size - 1

    while (0 < index) {
        val toMove = groups[index]

        if (toMove.contains(".")) {
            index -= 1
            continue
        }

        for (i in 0 until groups.size) {
            if (i > index) {
                break
            }

            val leftGroup = groups[i]

            if (!leftGroup.contains(".")) {
                continue;
            }

            if (toMove.size == leftGroup.size) {
                groups[i] = toMove
                groups[index] = leftGroup
                break
            }

            if (toMove.size <= leftGroup.size) {
                val newListFront = MutableList(leftGroup.size - toMove.size) { "." }
                val newListBack = MutableList(toMove.size) { "." }

                groups[i] = toMove
                groups.add(i + 1, newListFront)
                index += 1
                groups[index] = newListBack
                break
            }
        }

        index -= 1
    }

    val result = groups.flatten()
    var sum: Long = 0

    for (i in 0 until result.size) {
        if (result[i] != ".") {
            sum += (i * result[i].toLong())
        }
    }

    println(sum)
}

private val sample = """
        2333133121414131402
    """.trimIndent()

fun main() {
    process("14113") // 16
    process("12345") // 132
    process(sample)
    process(fromFile())
}

private fun fromFile(): String {
    val fileName = "src/main/resources/day9.txt"
    return Files.readString(Paths.get(fileName))
}

