import gamecontrol.EnemyControl
import gamecontrol.ItemControl

fun main() {
    var input: String?
    Map.generateMap()
    Map.floorMap()
    Map.updatePlayerPosition()
    EnemyControl.generateEnemies()
    ItemControl.generateItems()
    Map.addEnemies(EnemyControl.enemyList)
    Map.addItems(ItemControl.itemList)
    println("""WELCOME TO MY GAME!
        | USE W A S D TO WALK AROUND THE MAP.
        | THERE ARE TWO TYPES OF MONSTERS:
        | ->TYPINGMASTER ( YOU MUST WRITE WHATEVER HE WRITES TO BEAT HIM )
        | ->RANDOMASTER ( GUESS THE NUMBER RANDOMASTER IMAGINED )
        | USE I TO SEE INVENTORY AND USE HEALTH POTIONS AND ATTACK BOOSTERS
    """.trimMargin())
    println("START GAME [1]")
    do {
       var input = readLine()
    }while (input != "1")
    Map.showMap()


    do {
        // if(gamecontrol.EnemyControl.enemyList.isEmpty()) startBossFight()
        println("[W] - Up | [A] - Left | [S] - down | [D] - Right | [I] - Inventory | [H] - Show stats")
        input = readLine()
        when(input){
            "w","a","s","d" -> Player.move(input)
            "i" -> Player.showInventory()
            "h"-> println("Health: ${Player.health} Attack: ${Player.attack}")
        }
        val list = EnemyControl.enemyList.filter{ it.position == Player.position }
        val listIn = ItemControl.itemList.filter { it.position == Player.position }
        if (listIn.isNotEmpty()){
            println("Item collected!")
            Player.inventory.add(listIn.first())
            ItemControl.itemList.removeIf { it.position == Player.position }
        }
        if (list.isNotEmpty()){
            println("Are you ready to fight?")
            println("[1] - Yes")
            do {
                val temp:String? = readLine()
            }while (temp != "1")

                list.first().fight()



            EnemyControl.enemyList.removeIf { it.position == Player.position }
        }
        if(Player.health <= 0){
            println("YOU DED!")
            break
        }
        Map.updatePlayerPosition()
        Map.showMap()
        if (EnemyControl.enemyList.isEmpty()){
            println("YOU WIN!")
            break
        }
  }while(true)
}