import enums.models.Bicycle
import enums.models.Car
import enums.models.Vehicle

fun sayHello(name: String): Unit {
    println("hello $name")
}

fun sum(x: Int, y: Int): Int {
    return x + y
}

fun showColor(red: Int, green: Int, blue: Int, alpha: Float) {
    println("color: $red - $green - $blue - $alpha")
}

//fun with variadic arguments - vararg
// không giới hạn tham số đầu vào
fun like(vararg fruits: String) {
    for (i in fruits) {
        println("i like $i")
    }
}

// infix function
// can be called without using the period and brackets
infix fun Int.plus(x: Int): Int {
    return this + x
}

// one - line function
infix fun Int.times(x: Int): Int = this * x
infix fun String.love(name: String) = "$this is love $name"


// Higher order function is:
// - function that takes another function as parameter
// OR - function returns a function
fun doSomeThing(x: Int, y: Int, completion: (Int) -> Unit) {
    println("do something")
    val result = x + y
    completion(result)
}

// function returns a function
fun operation(x: Float): (y: Float) -> Float {
    return fun(y: Float): Float {
        return y * y
    }
}

// lambda function
val getFullName: (String, String) -> String = { firstName: String, lastName: String ->
    run {
        println("This is a lambda function")
        "$firstName $lastName"
    }
}

//another example
val url: (Int, Int) -> String = {
    page: Int, limit: Int -> "https:yourServerName:port/product?page=$page&limit=$limit"
}
//another simpler example
val squaredNumber: (Int) -> Int = {x -> x * x}
val squaredNumber1: (Int, Int) -> Int = {x, y -> x * y}

fun describeVehicle(vehicle: Vehicle): String{
    return when(vehicle){
        is Bicycle -> "this is a Bicycle"
        is Car -> "this is a Car"
        else -> "i don't know"
    }
}
