package gameobjects.items

import Player
import gameobjects.items.Items
import kotlin.random.Random

class HealthPotion: Items(
    name = "Health potion",
    position = Pair(Random.nextInt(0,10), Random.nextInt(0,10))) {

    override fun use() {
        Player.health += 1
    }

}