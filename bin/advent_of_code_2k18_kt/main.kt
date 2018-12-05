package advent_of_code_2k18_kt

import advent_of_code_2k18_kt.day4.Day4



fun main(args: Array<String>) {
	val days = arrayListOf<BaseDay>()
	
//	days.add(Day1())
//	days.add(Day2())
//	days.add(Day3())
	days.add(Day4())
	
	days.forEach{
		it.part1()
		it.part2()
	}
}
