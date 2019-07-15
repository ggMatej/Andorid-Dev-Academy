package gameobjects.enemies

import gameobjects.GameObject

abstract class Enemy(
    var position: Pair<Int,Int>,
    var attack: Int = 1,
    var health: Int = 3): GameObject(sprite = "E"), EnemyBehaviour {

}
