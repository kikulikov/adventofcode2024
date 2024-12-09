package org.example

import java.nio.file.Files
import java.nio.file.Paths

private fun process(lines: List<String>) {

    val before = HashMap<Int, HashSet<Int>>()
    val updates = ArrayList<List<Int>>()

    for (line in lines) {
        if (line.contains("|")) {
            var a = line.split("|").get(0).toInt()
            var b = line.split("|").get(1).toInt()
            val set = before.getOrPut(a) { HashSet() }
            set.add(b)
        }
        if (line.contains(",")) {
            updates.add(line.split(",").map { s -> s.toInt() })
        }
    }

    var result = 0

    for (update in updates) {
        var next = before.getOrPut(update[0]) { HashSet() }
        var include = true;

        for (i in 1 until update.size) {
            if (next.contains(update[i])) {
                next = before.getOrPut(update[i]) { HashSet() }
            } else {
                include = false;
                break;
            }
        }

        if (include) {
            result += update.get(update.size / 2)
        }
    }

    println(result)
}

private val sample = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13
        
        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
    """.trimIndent()

fun main() {
    process(sample.lines())
    process(fromFile())
}

private fun fromFile(): List<String> {
    val fileName = "src/main/resources/day5.txt"
    return Files.readAllLines(Paths.get(fileName))
}


