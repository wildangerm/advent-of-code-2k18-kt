package advent_of_code_2k18_kt.day2

import advent_of_code_2k18_kt.BaseDay
import java.io.File

class Day2 : BaseDay {

	var inputList = arrayListOf<String>()

	constructor() {
		readFile()
	}

	override fun part1() {
		var twice = 0;
		var thrice = 0;

		inputList.forEach {
			var hashMap = HashMap<Char, Int>()
			it.forEach {
				var num = hashMap.get(it) ?: 0
				num++
				hashMap.put(it, num)
			}

			if (hashMap.values.contains(2)) {
				twice++;
			}
			if (hashMap.values.contains(3)) {
				thrice++;
			}

		}

		println("checksum: " + twice * thrice)
	}

	override fun part2() {
		var common = ""
		for (i in inputList.indices) {
			for (j in inputList.indices) {
				if (i != j) {
					var mistakesAllowed = 1
					val word1 = inputList.get(i)
					val word2 = inputList.get(j)
					var saved = 0

					for (k in word1.indices) {
						if (word1.get(k) != word2.get(k)) {
							mistakesAllowed--
							saved = k

							if (mistakesAllowed < 0) {
								break;
							}
						}

						if (k == (word1.length - 1) && mistakesAllowed == 0) {
							common = word1.substring(0, saved) + word1.substring(saved + 1)
							break;
						}
					}
				}
			}
		}

		println("common: " + common)

	}

	fun readFile() {
		val pathString = getPathStringToInput("input.txt")
		inputList = ArrayList(File(pathString).readLines())

	}
}