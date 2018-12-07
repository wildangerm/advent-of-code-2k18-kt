package advent_of_code_2k18_kt.day6

class Coordinate {
	var x: Int = 0
	var y: Int = 0

	companion object {
		var counter: Int = 1
	}

	val ID: Int

	constructor(x: Int, y: Int) {
		this.x = x
		this.y = y
		this.ID = counter++
	}

}