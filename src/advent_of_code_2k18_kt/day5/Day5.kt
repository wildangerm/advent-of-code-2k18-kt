package advent_of_code_2k18_kt.day5

import advent_of_code_2k18_kt.BaseDay
import java.nio.file.Files
import java.nio.file.Paths

class Day5 : BaseDay {

	var inputList = arrayListOf<Char>()

	constructor() {
		readFile()

	}

	override fun part1() {
		val shortestPolymer = react(inputList)
		println("shortest: " + shortestPolymer)
	}

	override fun part2() {
		TODO()
	}

	fun react(inputList: List<Char>): Int {
		val list = ArrayList<Char>(inputList)
		var finished = false
		while (!finished) {


			var size = list.size
			var previous = Char.MIN_VALUE
			val toDelete = ArrayList<Int>()

			for (i in 0..list.size - 1) {
				val c = list.get(i)
				if (!c.equals(previous) && (c.equals(previous.toUpperCase()) || c.equals(previous.toLowerCase()))) {
					if (i != 0) {
						toDelete.add(i)
						toDelete.add(i - 1)
						break
					}
				}
				previous = c
			}
			toDelete.forEach { list.removeAt(it) }
			if (list.size == size) {
				finished = true
			}

		}

		return list.size
	}


	fun readFile() {
		val pathString = getPathStringToInput("input.txt")
		val input: String = String(Files.readAllBytes(Paths.get(pathString)))

		inputList = ArrayList(input.toCharArray().toList())

	}
}