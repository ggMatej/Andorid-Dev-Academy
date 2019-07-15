package gameobjects.enemies

import Player
import kotlin.random.Random

class Randomaster: Enemy(
    position = Pair(Random.nextInt(0,10), Random.nextInt(0,10))) {


    override fun fight(){
        while (health > 0 && Player.health > 0) {
            println("Guess what number I imagined? (1,2 or 3)")
            var input = readLine()?.toInt()
            var rand = Random.nextInt(1, 4)
            if (rand == input) {
                println("YEAH!")
                health -= Player.attack

                if (health == 0) println("You killed the beast!")

            }else {
                println("OUCH!")
                Player.health -= attack
            }
        }
    }

}