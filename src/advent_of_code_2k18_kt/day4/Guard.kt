package advent_of_code_2k18_kt.day4

import java.time.LocalDateTime
import kotlin.collections.Map.Entry
import kotlin.collections.MutableMap.MutableEntry
import java.util.AbstractMap

class Guard {

	var id: Int = 0
	var activities = LinkedHashMap<LocalDateTime, Activity>()

	constructor(id: Int, date: LocalDateTime, activity: Activity) {
		this.id = id
		activities.put(date, activity)
	}

	fun addActivity(date: LocalDateTime, activity: Activity) {
		activities.put(date, activity)
	}

	public fun getMinutesAsleep(): Int {
		var sum: Int = 0
		var previousAsleep: LocalDateTime = LocalDateTime.MIN

		for (activity in activities.entries) {
			if (activity.value.equals(Activity.ASLEEP)) {
				previousAsleep = activity.key
			} else if (activity.value.equals(Activity.WAKE_UP)) {
				sum += getAsleepTime(previousAsleep, activity.key)
			}

		}

		return sum
	}

	private fun getAsleepTime(fromAsleep: LocalDateTime, toAwake: LocalDateTime): Int {
		return toAwake.minute - fromAsleep.minute
	}

	private fun calcMostAsleepMinuteEntry(): Entry<Int, Int>? {
		var minuteStatsMap = HashMap<Int, Int>()
		var stillSleeping = false
		var asleepStartMinute: Int = 0

		for (activity in activities.entries) {
			if (activity.value.equals(Activity.ASLEEP)) {
				asleepStartMinute = activity.key.minute
				stillSleeping = true
			} else if (activity.value.equals(Activity.WAKE_UP) && stillSleeping) {
				for (i in asleepStartMinute..activity.key.minute) {
					if (minuteStatsMap.get(i) != null) {
						minuteStatsMap.put(i, minuteStatsMap.get(i)!! + 1)
					} else {
						minuteStatsMap.put(i, 1)
					}
					stillSleeping = false
				}
			}
		}
		var result: MutableEntry<Int, Int>?
		if (minuteStatsMap.isEmpty()) {
			result = AbstractMap.SimpleEntry<Int, Int>(-1, -1)
		} else {
			result = minuteStatsMap.entries.maxBy { it.value }
		}

		return result
	}

	public fun getMostAsleepMinute(): Int {
		return calcMostAsleepMinuteEntry()?.key ?: -1
	}

	public fun getMostAsleepMinuteCount(): Int {
		return calcMostAsleepMinuteEntry()?.value ?: -1
	}
}