package gamecontrol

import gameobjects.enemies.Enemy
import gameobjects.enemies.Randomaster
import gameobjects.enemies.Typingmaster
import kotlin.random.Random

object EnemyControl {
    val enemyList = mutableListOf<Enemy>()

    fun generateEnemies(){
        for (i in 0..10) {
            if(Random.nextBoolean()) enemyList.add(Randomaster())
            else enemyList.add(Typingmaster())

        }
    }



}