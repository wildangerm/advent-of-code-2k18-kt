package advent_of_code_2k18_kt.day3

import advent_of_code_2k18_kt.BaseDay
import java.io.File
import java.util.regex.Matcher
import java.util.regex.Pattern

class Day3 : BaseDay {
	var inputList = arrayListOf<Claim>()

	constructor() {
		readFile()
	}

	override fun part1() {
		var fabric = Array(1001, { IntArray(1001) })
		var sqinch = 0

		inputList.forEach { claim ->
			for (x in claim.left..claim.left + claim.width - 1) {
				for (y in claim.top..claim.top + claim.height - 1) {
					if (fabric[x][y] == 0) {
						fabric[x][y] = claim.id
					} else if (fabric[x][y] != -1) {
						fabric[x][y] = -1
						sqinch++
					}
				}
			}
		}

		println("sqinch: " + sqinch)
	}

	override fun part2() {
		var fabric = Array(1001, { IntArray(1001) })

		inputList.forEach { claim ->
			for (x in claim.left..claim.left + claim.width - 1) {
				for (y in claim.top..claim.top + claim.height - 1) {
					if (fabric[x][y] == 0) {
						fabric[x][y] = claim.id
					} else {
						fabric[x][y] = -1
					}
				}
			}
		}

		var intactClaimId = -1

		inputList.forEach { claim ->
			var stillFree = true
			for (x in claim.left..claim.left + claim.width - 1) {
				if (stillFree) {
					for (y in claim.top..claim.top + claim.height - 1) {
						if (stillFree) {
							if (fabric[x][y] != claim.id) {
								stillFree = false
								break
							} else if (x == (claim.left + claim.width - 1) && y == (claim.top + claim.height - 1) && stillFree) {
								intactClaimId = claim.id
							}
						}
					}
				}
			}
		}

		println("Intact claimid: " + intactClaimId)


	}

	fun readFile() {
		val pathString = getPathStringToInput("input.txt")
		val lines: ArrayList<String> = ArrayList(File(pathString).readLines())

		inputList = ArrayList(lines.map { line -> createClaimFromRegex(line) })

	}

	private fun createClaimFromRegex(line: String): Claim {
		val re1 = "(#)"; // Any Single Character 1
		val re2 = "(\\d+)"; // Integer Number 1
		val re3 = "(\\s+)"; // White Space 1
		val re4 = "(@)"; // Any Single Character 2
		val re5 = "(\\s+)"; // White Space 2
		val re6 = "(\\d+)"; // Integer Number 2
		val re7 = "(,)"; // Any Single Character 3
		val re8 = "(\\d+)"; // Integer Number 3
		val re9 = "(:)"; // Any Single Character 4
		val re10 = "(\\s+)"; // White Space 3
		val re11 = "(\\d+)"; // Integer Number 4
		val re12 = "(x)"; // Any Single Word Character (Not Whitespace) 1
		val re13 = "(\\d+)"; // Integer Number 5

		val p = Pattern.compile(
			re1 + re2 + re3 + re4 + re5 + re6 + re7 + re8 + re9 + re10 + re11 + re12 + re13,
			Pattern.CASE_INSENSITIVE
		);
		val m = p.matcher(line);
		if (m.find()) {
			val id = Integer.parseInt(m.group(2));
			val left = Integer.parseInt(m.group(6));
			val top = Integer.parseInt(m.group(8));
			val width = Integer.parseInt(m.group(11));
			val height = Integer.parseInt(m.group(13));

			return Claim(id, left, top, width, height);
		}

		return Claim(0, 0, 0, 0, 0);
	}
}