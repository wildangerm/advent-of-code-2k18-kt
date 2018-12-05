package advent_of_code_2k18_kt

abstract class BaseDay {
	abstract fun part1()
	abstract fun part2()

	protected fun getPathStringToInput(fileName: String): String {
		val url = javaClass.getResource(fileName);
		val pathString = url.getPath().substring(1).replace("/", "\\\\").replace("%20", " ");
		return pathString;
	}
}