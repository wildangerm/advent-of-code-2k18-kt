package advent_of_code_2k18_kt.day6

import advent_of_code_2k18_kt.BaseDay
import java.io.File
import java.util.regex.Pattern

class Day6 : BaseDay {

	val SAFE_REGION_DISTANCE = 10000
	var inputList = ArrayList<Coordinate>()
	var grid: Array<IntArray>

	constructor() {
		readFile()
		val maxCoordX = inputList.maxBy { it.x }!!.x
		val maxCoordY = inputList.maxBy { it.y }!!.y

		grid = Array(maxCoordX + 1, { IntArray(maxCoordY + 1) })
		println(1)
	}

	override fun part1() {
		TODO()
	}

	override fun part2() {
		TODO()
	}

	fun readFile() {
		val pathString = getPathStringToInput("input.txt")
		val lines: ArrayList<String> = ArrayList(File(pathString).readLines())

		inputList = ArrayList(lines.map { line -> createCoordinateFromRegex(line) })

	}

	private fun createCoordinateFromRegex(line: String): Coordinate {
		val re1 = "(\\d+)" // Integer Number 1
		val re2 = "(,)" // Any Single Character 1
		val re3 = "( )" // White Space 1
		val re4 = "(\\d+)" // Integer Number 2

		val p = Pattern.compile(re1 + re2 + re3 + re4, Pattern.CASE_INSENSITIVE);
		val m = p.matcher(line);

		if (m.find()) {
			val x = Integer.parseInt(m.group(1));
			val y = Integer.parseInt(m.group(4));

			return Coordinate(x, y);
		}
		return Coordinate(-1, -1);
	}
}