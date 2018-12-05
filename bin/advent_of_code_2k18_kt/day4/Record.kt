package advent_of_code_2k18_kt.day4

import java.time.LocalDateTime

class Record(val date: LocalDateTime, val description: String) : Comparable<Record> {

	override operator fun compareTo(o: Record): Int {
		return date.compareTo(o.date)
	}
}