import gameobjects.enemies.Enemy
import gameobjects.Floor
import gameobjects.GameObject
import gameobjects.items.Items

object Map {
    var gameMap = arrayOf<Array<GameObject>>()

    fun generateMap(){
        for (i in 0..9) {
            var array = arrayOf<GameObject>()
            for (j in 0..9) {
                array += GameObject()
            }
            gameMap += array
        }
    }

    fun floorMap(){
        for(i in 0..9){
            for(j in 0..9){
                gameMap[i][j] = Floor()
            }
        }
    }

    fun showMap(){
        for(i in 0..9){
            for (j in 0..9){
                print("${Map.gameMap[i][j].sprite} ")
            }
            println()
        }
    }

    fun updatePlayerPosition(){
        gameMap[Player.previousPosition.first][Player.previousPosition.second] = Floor()
        gameMap[Player.position.first][Player.position.second] = Player
    }

    fun addEnemy(enemy: Enemy){
        gameMap[enemy.position.first][enemy.position.second] = enemy
    }

    fun addItem(item: Items){
        gameMap[item.position.first][item.position.second] = item
    }

    fun addEnemies(enemies: MutableList<Enemy>){
        enemies.forEach { addEnemy(it) }
    }

    fun addItems(items: MutableList<Items>){
        items.forEach { addItem(it) }
    }

    fun getGameObject(i: Int,j: Int): GameObject {
        return gameMap[i][j]
    }

}