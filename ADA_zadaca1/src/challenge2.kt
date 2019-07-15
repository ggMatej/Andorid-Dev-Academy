import kotlin.random.Random
/*
Create a collection of integers

Use Random to fill the collection with 100 random numbers between 1 and 100.

Go through the collection from start to end and print its elements up to the point where an element is less than or equal to 10
 */

fun main(args: Array<String>) {


    val myList : MutableList<Int> = mutableListOf()
    for(i in 1..100) {
        myList.add(Random.nextInt(1,100))
    }

    for(i in myList){
        if(i>10) print("$i ") else break
    }

}
