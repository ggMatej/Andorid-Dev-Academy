import kotlin.coroutines.coroutineContext

/*
Implement the member functions of the Robot class. A Robot starts in the (0, 0) cell and can move to the right, to the left, down and up.
Its location is stored as a pair of coordinates (x, y), where (0, 0) is the top-left corner:

(x,y)
(0,0) (1,0)
(0,1) (1,1)
Going right increases x coordinate, going down increases y coordinate, going left and up decreases x and y coordinates accordingly.

Implement goRight(), goLeft(), goUp(), goDown() and getLocation() member functions. getLocation() method should return the coordinates as a string (x, y).
 */

class Robot(var coordinates : Pair<Int,Int> = Pair(0,0)) {


    fun goRight(steps: Int) {
        coordinates = Pair(coordinates.first + steps, coordinates.second)
    }


    fun goLeft(steps: Int) {
        coordinates = Pair(coordinates.first - steps, coordinates.second)
    }

    fun goDown(steps: Int) {
        coordinates = Pair(coordinates.first, coordinates.second  + steps)
    }

    fun goUp(steps: Int) {
        coordinates = Pair(coordinates.first, coordinates.second  - steps)
    }

    fun getLocation(): String = "Location is: $coordinates"
}

fun main(args: Array<String>) {
    val robot = Robot()
    println(robot.getLocation())
    robot.goRight(1)
    println(robot.getLocation())
    robot.goDown(2)
    println(robot.getLocation())
}
/* Output:
(0,0)
(1,0)
(1,2)
*/