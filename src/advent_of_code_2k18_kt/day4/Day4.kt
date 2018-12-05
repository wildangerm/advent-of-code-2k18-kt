package advent_of_code_2k18_kt.day4

import advent_of_code_2k18_kt.BaseDay
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Day4 : BaseDay {
	var inputList = arrayListOf<Record>()
	val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
	var guards = LinkedHashMap<Int, Guard>()

	constructor() {
		readFile()
		inputList.sort()
		guards = createGuards()

	}

	override fun part1() {
		val guard = guards.values.maxWith(compareBy { it.getMinutesAsleep() })

		val id = guard!!.id
		val mostAsleepMinute = guard.getMostAsleepMinute()

		println("result: " + id + "*" + mostAsleepMinute + " = " + id * mostAsleepMinute)
	}

	override fun part2() {
		val guard = guards.values.maxWith(compareBy { it.getMostAsleepMinuteCount() })

		val id = guard!!.id
		val mostAsleepMinute = guard.getMostAsleepMinute()

		println("result: " + id + "*" + mostAsleepMinute + " = " + id * mostAsleepMinute)

	}

	fun readFile() {
		val pathString = getPathStringToInput("input.txt")
		val lines: ArrayList<String> = ArrayList(File(pathString).readLines())

		inputList = ArrayList(lines.map { line -> createRecordFromRegex(line) })

	}

	private fun createRecordFromRegex(line: String): Record {
		val dateString = line.substring(1, 17)
		val thing = line.substring(19)

		return Record(LocalDateTime.from(dtf.parse(dateString)), thing)
	}

	private fun createGuards(): LinkedHashMap<Int, Guard> {
		var guards = LinkedHashMap<Int, Guard>()
		var activeGuardId: Int = -1

		for (record in inputList) {
			if (record.description.contains("Guard")) {
				val parts = record.description.split(" ")
				activeGuardId = parts[1].substring(1).toInt()
				val g = guards.get(activeGuardId)
				if (g == null) {
					guards.put(activeGuardId, Guard(activeGuardId, record.date, Activity.BEGIN))
				} else {
					g.activities.put(record.date, Activity.BEGIN)
				}
			} else if (record.description.contains("asleep")) {
				guards.get(activeGuardId)?.activities?.put(record.date, Activity.ASLEEP)
			} else if (record.description.contains("wakes")) {
				guards.get(activeGuardId)?.activities?.put(record.date, Activity.WAKE_UP)
			}
		}
		return guards
	}
}