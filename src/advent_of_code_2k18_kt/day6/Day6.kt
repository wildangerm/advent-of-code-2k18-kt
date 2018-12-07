package advent_of_code_2k18_kt.day6

import advent_of_code_2k18_kt.BaseDay
import java.io.File
import java.util.regex.Pattern
import kotlin.math.abs

class Day6 : BaseDay {

	val SAFE_REGION_DISTANCE = 10000
	var inputList = ArrayList<Coordinate>()
	var grid: Array<IntArray>

	constructor() {
		readFile()
		val maxCoordX = inputList.maxBy { it.x }!!.x
		val maxCoordY = inputList.maxBy { it.y }!!.y

		grid = Array(maxCoordX + 1, { IntArray(maxCoordY + 1) })
	}

	override fun part1() {
		for (x in 0..grid.size - 1) {
			for (y in 0..grid[0].size - 1) {
				grid[x][y] = getClosestCoordinateID(x, y)
			}
		}

		val candidatesIDs = excludeInfinite()

		val flatFilteredGrid = grid.flatMap { it.asIterable() }.filter { candidatesIDs.contains(it) }

		val max = flatFilteredGrid.asSequence().groupBy { it }.map { it.value.size }.maxBy { it }

		println("max: " + max)

	}

	override fun part2() {

	}

	private fun excludeInfinite(): List<Int> {
		val candidates = ArrayList<Coordinate>()

		val toExclude = getIDsToExclude()

		inputList.forEach {
			if (!toExclude.contains(it.ID)) {
				candidates.add(it)
			}
		}
		return candidates.map { it.ID }
	}

	private fun getIDsToExclude(): Set<Int> {
		val toExclude = HashSet<Int>()

		// Walk the outer edges, and if we find an ID, it is to be excluded
		// Top
		for (x in 0..grid.size - 1) {
			val gridElement = grid[x][0];
			if (gridElement != -1) {
				toExclude.add(gridElement);
			}
		}

		// Bottom
		for (x in 0..grid.size - 1) {
			val gridElement = grid[x][grid[0].size - 1];
			if (gridElement != -1) {
				toExclude.add(gridElement);
			}
		}

		// Left
		for (y in 0..grid[0].size - 1) {
			val gridElement = grid[0][y];
			if (gridElement != -1) {
				toExclude.add(gridElement);
			}
		}

		// Right
		for (y in 0..grid[0].size - 1) {
			val gridElement = grid[grid.size - 1][y];
			if (gridElement != -1) {
				toExclude.add(gridElement);
			}
		}

		return toExclude;
	}

	private fun getClosestCoordinateID(x: Int, y: Int): Int {
		var min = Int.MAX_VALUE
		var coordinateID = -1

		for (coordinate in inputList) {
			if (coordinate.x == x && coordinate.y == y) {
				return coordinate.ID
			}

			val dist = getDistToCoordinate(coordinate, x, y)
			if (dist < min) {
				min = dist
				coordinateID = coordinate.ID
			} else if (dist == min) {
				coordinateID = -1
			}
		}

		return coordinateID
	}

	private fun getDistToCoordinate(c: Coordinate, x: Int, y: Int): Int {
		return abs(c.x - x) + abs(c.y - y)
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