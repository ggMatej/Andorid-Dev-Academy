import gameobjects.GameObject
import gameobjects.items.HealthPotion
import gameobjects.items.Items

object Player: GameObject(sprite = "X") {
    var position = Pair(0,0)
    var previousPosition = Pair(0,0)
    var attack = 1
    var health = 3
    var inventory: MutableList<Items> = mutableListOf(HealthPotion())

    fun goRight(){
        if (position.second + 1 != Map.gameMap.size){
            previousPosition = Pair(position.first, position.second)
            position = Pair(position.first, position.second + 1)
        }

    }

    fun goLeft(){
        if (position.second - 1 != -1){
            previousPosition = Pair(position.first, position.second)
            position = Pair(position.first, position.second - 1)
        }

    }

    fun goDown(){
        if (position.first + 1 != Map.gameMap.size)
            previousPosition = Pair(position.first, position.second)
            position = Pair(position.first + 1, position.second)
    }

    fun goUp(){
        if (position.first - 1 != -1){
            previousPosition = Pair(position.first, position.second)
            position = Pair(position.first -1, position.second)
        }

    }

    fun move(input: String){
        when(input){
            "w"-> goUp()
            "a"-> goLeft()
            "s"-> goDown()
            "d"-> goRight()
        }
    }

    fun showInventory(){
        var input: String? = null
        if(inventory.isEmpty()){
            println("Your inventory is empty!")
            println("[1] - CONTINUE")
            do {
                input = readLine()
            }while (input != "1")
        }else{
            println("This is your inventory:")
            inventory.forEach{println(it.name)}
            println("[1] Use potion [2] Use booster [3] Continue")

            input = readLine()
            when(input){
                "1"-> {
                     for (i in inventory){
                         if (i.name == "Health potion"){
                             i.use()
                             println("Health potion used!")
                             inventory.remove(i)
                             break
                         }else println("No health potions!")
                     }
                }
                "2"-> {
                    for (i in inventory){
                        if (i.name == "Attack boost"){
                            i.use()
                            println("Attack boost used!")
                            inventory.remove(i)
                            break
                        }else println("No attack boosts!")
                    }
                }
                "3" -> return
            }


        }

    }
}