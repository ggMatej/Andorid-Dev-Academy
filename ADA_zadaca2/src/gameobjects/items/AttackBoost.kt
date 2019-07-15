package gameobjects.items

import Player
import gameobjects.items.Items
import kotlin.random.Random

class AttackBoost: Items(
    name = "Attack boost",
    position = Pair(Random.nextInt(0,10), Random.nextInt(0,10))) {
    override fun use() {
        Player.attack += 1
    }

}