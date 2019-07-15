package gameobjects.items

import gameobjects.GameObject

abstract  class Items(
    val name: String,
    var position: Pair<Int,Int>) : GameObject(sprite = "I") {

    abstract fun use()
}