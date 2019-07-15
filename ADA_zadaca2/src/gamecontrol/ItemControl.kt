package gamecontrol

import gameobjects.items.AttackBoost
import gameobjects.items.HealthPotion
import gameobjects.items.Items
import kotlin.random.Random

object ItemControl {
    val itemList = mutableListOf<Items>()

    fun generateItems(){
        for (i in 0..5) {
            if(Random.nextBoolean()) itemList.add(HealthPotion())
            else itemList.add(AttackBoost())

        }
    }
}