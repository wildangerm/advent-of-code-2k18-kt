package advent_of_code_2k18_kt.day1

import advent_of_code_2k18_kt.BaseDay
import java.io.File

class Day1 : BaseDay {

	var inputList = arrayListOf<Int>()

	constructor() {
		readFile()
	}

	override fun part1() {
		print("sum: " + inputList.sum())
	}

	override fun part2() {
		var hashSet = hashSetOf<Int>()
		var sum = 0
		var finished = false

		while (!finished) {
			inputList.forEach {
				if (!hashSet.add(sum)) {
					finished = true
					return@forEach
				}
				sum += it
			}
		}

		println("Duplikatum: " + sum);
		println("Set elemei: " + hashSet.size);
	}

	fun readFile() {

		val pathString = getPathStringToInput("input.txt")
		val lines: ArrayList<String> = ArrayList(File(pathString).readLines())

		inputList = ArrayList(lines.map { it -> it.toInt() })
	}
}