import java.util.function.Consumer

/*
Challenge: Apply Functional Programming for Simple Data Analysis

We decided to gather data on the age of our users.
In this challenge, you'll be presented with this partly faulty data
 of user ages which is based on four input files:
 */

val data = mapOf(
    "users1.csv" to listOf(32, 45, 17, -1, 34),
    "users2.csv" to listOf(19, -1, 67, 22),
    "users3.csv" to listOf(),
    "users4.csv" to listOf(56, 32, 18, 44)
)

fun main() {

    // Prvo rješenje

   /* var sum = 0
    var count = 0
    var faultyEntries = 0
    val faultyDataSet : HashSet<String> = HashSet()

    for(i in 1..data.size){
        var myList: List<Int>? = data["users$i.csv"]
        myList!!.forEach {
            if(it>0) {
                sum += it
                count += 1
            } else {
                faultyDataSet.add("users$i.csv")
                faultyEntries+=1
            }
        }
    }

    println("Average age of users is: ${sum/count}")
    println("Files that contain faulty data: $faultyDataSet")
    println("Total number of faulty entries across all input files: $faultyEntries")*/

    //Drugo rješenje

    // 1. Find the average age of users (use only valid ages for the calculation!)
    println("Average age if users is: ${data.flatMap { it.value }.filter { it > 0 }.average().toInt()}")

    // 2. Extract the names of input files that contain faulty data

    print("Files that contain faulty data: ")
    for (i in 1..data.size) {
        data["users$i.csv"]?.forEach { if (it < 0) print("users$i.csv") }
        print(" ")
    }

    println()

    // Or

    print("Files that contain faulty data: ")
    val ageList = data.map { it.value }
    val nameList = data.map { it.key }
    for (i in 0 until data.size-1){
        ageList[i].forEach { if (it<0)  print(nameList[i] + " ")}
    }

    println()

    // 3. Count the total number of faulty entries across all input files
    var faultyEntries = 0
    data.flatMap { it.value }.forEach { if (it < 0) faultyEntries += 1 }
    println("Total number of faulty entries across all input files: $faultyEntries")



}

/*
Apply the functions you learned about as well as other functions from Kotlin's
standard library to solve the following data analysis tasks:

1. Find the average age of users (use only valid ages for the calculation!)
2. Extract the names of input files that contain faulty data
3. Count the total number of faulty entries across all input files

Hints: map() and flatMap() are often very useful functions for such tasks
Use IntelliJ's autocompletion to explore which other functions, that were not
presented in the lectures, are available (they could simplify the tasks)
 */