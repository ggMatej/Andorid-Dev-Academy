package gameobjects.enemies

import Player
import gameobjects.enemies.Enemy
import kotlin.random.Random
import kotlinx.coroutines.*

class Typingmaster(
    val attackList: MutableList<String> = mutableListOf("sarma","peskir","vrc",
        "cup","grmlje","cuprija","zec","kec"))
    : Enemy(
    position = Pair(Random.nextInt(0,10), Random.nextInt(0,10))) {


    override fun fight() {

        var input: String? = null
        while (health > 0 && Player.health > 0) {

            val rand = attackList[Random.nextInt(0, attackList.size - 1)]
            GlobalScope.launch {
                input = readLine()
            }
            println(rand)
            Thread.sleep(5000L)
            if (input == rand) {
                println("YEAH!")
                health -= Player.attack
                input = null
                if (health == 0) println("You killed the beast!")
            } else {
                println("OUCH!")
                input = null
                Player.health -= attack
            }
        }
    }
}
