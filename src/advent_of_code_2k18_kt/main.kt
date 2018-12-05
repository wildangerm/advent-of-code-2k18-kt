package advent_of_code_2k18_kt

import advent_of_code_2k18_kt.day5.Day5



fun main(args: Array<String>) {
	val days = arrayListOf<BaseDay>()
	
//	days.add(Day1())
//	days.add(Day2())
//	days.add(Day3())
//	days.add(Day4())
	days.add(Day5())
	
	days.forEach{
		it.part1()
		it.part2()
	}
}
