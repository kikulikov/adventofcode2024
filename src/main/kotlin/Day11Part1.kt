package org.example

import java.nio.file.Files
import java.nio.file.Paths

data class Node<T>(var value: T, var next: Node<T>? = null)

private fun blink(list: Node<Long>?) {
    var node = list

    while (node != null) {
        if (node.value == 0L) {
            node.value = 1
            node = node.next

        } else if (node.value.toString().length % 2 == 0) {
            var str = node.value.toString()
            var left = str.substring(0, str.length / 2).toLong()
            var right = str.substring(str.length / 2, str.length).toLong()

            var next = node.next
            var created = Node(right, next)

            node.value = left
            node.next = created
            node = created.next

        } else {
            node.value *= 2024
            node = node.next
        }

    }
}

private fun process(line: String) {
    val nums = line.split(" ").map { it.toLong() }
    println(nums)

    var node: Node<Long>? = null
    var curr: Node<Long>? = null

    for (num in nums) {
        if (curr == null) {
            curr = Node(num)
            node = curr
        } else {
            var next = Node(num)
            curr.next = next
            curr = next
        }
    }

    println(node)

    for (i in 0 until 25) {
        blink(node)
    }

    var result: Long = 0L

    while (node != null) {
        result += 1
        node = node.next
    }

    println(result)
}

private val sample = """
        125 17
    """.trimIndent()

fun main() {
    process(sample)
    process(fromFile())
}

private fun fromFile(): String {
    val fileName = "src/main/resources/day11.txt"
    return Files.readString(Paths.get(fileName))
}

