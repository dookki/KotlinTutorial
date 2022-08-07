package scopefunction

import kotlin.random.Random

/**
 * web:
 * https://www.geeksforgeeks.org/kotlin-scope-function/
 * https://kotlinlang.org/docs/scope-functions.html
 * */

/**
 * Types of scope functions
 * There are five types of scope functions:
 * 1: let
 * 2:run
 * 3:with
 * 4:apply
 * 5:also
 */

/**
 * Differences in these functions:
 * 1: Way of referring to a context object (i.e. using either ‘this’ or ‘it’ keyword)
 * 2: return value (i.e. returns either ‘context object’ or ‘lambda result’)
 */

/**
 * Object References:
 * There are two ways of object referencing in scope functions:
 *
 * 1. this
 * We can refer to the context object by a lambda receiver keyword – this.
 * this keyword does object reference in ‘run’, ‘with’, and ‘apply’ functions.
 *
 * 2. it
 * ‘let’ and ‘also’ functions refer to the object’s context as a lambda argument.
 * */


/**
 * Return values:
 * 1. Lambda result
 * If we write any expression at the end of the code block, it becomes the return value for the scope function.
 * Return value for ‘let’, ‘run’, and ‘with’ functions is the lambda result.
 *
 * 2. Context object (object itself.)
 * ‘apply’ and ‘also’ functions return the context object itself. In this case,
 * we don’t need to specify the return value. The context object is automatically returned.
 * */
fun main() {
//    scopeAlso()
    takeIf()
}

// Context object: this or it
// lambda receiver (this)
// lambda argument (it)
private fun contextObject() {
    val str = "Hello"
    // this
    str.run {
        println("The string's length: $length")
        //println("The string's length: ${this.length}") // does the same
    }

    // it
    str.let {
        println("The string's length is ${it.length}")
    }
}

/**
 * Context object  :   it
 * Return value    :   lambda result
 *
 * Use Case:
 * let is often used for executing a code block only with non-null values.
 * To perform actions on a non-null object, use the safe call operator ?. on it and call let with the actions in its lambda.
 * */
private fun scopeLet() {
    // nullable variable a
    // with value as null
    var a: Int? = null
    a?.let {
        // statement(s) will not execute as a is null
        print(it)
    }

    // re-initializing value of a to 2
    a = 2
    a?.let {
        // statement(s) will execute
        // as a is not null
        print(a)
    }
}

/**
 * Context object   :    this
 * Return value     :    object itself.
 * Use Case:
 * As the name implies – “Apply these to the object”.
 * It can be used to operate on members of the receiver object mostly to initialize members.
 * */
private fun scopeApply() {
    val adam = Person("Adam").apply {
        age = 32
        city = "London"
    }
    println(adam)
}

data class Person(
    var name: String,
    var age: Int? = null,
    var city: String? = null,
)

/**
 * A non-extension function:
 * Context object   :    this
 * Return value     :    lambda result
 *
 * Use Case:
 * We recommend with for calling functions on the context object without providing the lambda result.
 * In the code, with can be read as “with this object, do the following.”
 * */
private fun scopeWith() {
    val numbers = mutableListOf("one", "two", "three")
    with(numbers) {
        println("'with' is called with argument $this")
        println("It contains $size elements")
    }
}

/**
 * Context object   :    this
 * Return value     :    lambda result
 *
 * Use Case:
 * Used when the object lambda contains both initialization and the computation of the return value.
 * Using run we can perform null safety calls as well as other computations.
 *
 * run does the same as with but invokes as let - as an extension function of the context object.
 * run is useful when your lambda contains both the object initialization and the computation of the return value.
 * */
private fun scopeRun() {
    val service = MultiportService("https://example.kotlinlang.org", 80)

    val result = service.run {
        port = 8080
        query(prepareRequest() + " to port $port")
    }

    // the same code written with let() function:
    val letResult = service.let {
        it.port = 8080
        it.query(it.prepareRequest() + " to port ${it.port}")
    }
}

/**
 * Context object   :    it
 * Return value     :    object itself.
 * Use Case:
 * It is used where we have to perform additional operations when we have initialized the object members.
 *
 * also is good for performing some actions that take the context object as an argument.
 * Use also for actions that need a reference to the object rather than its properties and functions,
 * or when you don't want to shadow the this reference from an outer scope.
 * When you see also in the code, you can read it as “and also do the following with the object.”
 * */
private fun scopeAlso() {
    val numbers = mutableListOf("one", "two", "three")
    numbers.also { println("The list elements before adding new one: $it") }
        .add("four")
    println(numbers)
}

/**
 * When called on an object with a predicate provided, takeIf returns this object if it matches the predicate.
 * Otherwise, it returns null. So, takeIf is a filtering function for a single object.
 * In turn, takeUnless returns the object if it doesn't match the predicate and null if it does.
 * The object is available as a lambda argument (it).
 * */
private fun takeIf() {

    val number = Random.nextInt(100)

    val evenOrNull = number.takeIf { it % 2 == 0 }
    val oddOrNull = number.takeUnless { it % 2 == 0 }
    println("even: $evenOrNull, odd: $oddOrNull")

    //
    println("---------------------------------")
    //
    val str = "Hello"
    val caps = str.takeIf { it.isNotEmpty() }?.uppercase()
    println(caps)

    //
    println("---------------------------------")
    //
    displaySubstringPosition("010000011", "11")
    displaySubstringPosition("010000011", "100")
}

fun displaySubstringPosition(input: String, sub: String) {
    input.indexOf(sub).takeIf { it >= 0 }?.let {
        println("The substring $sub is found in $input.")
        println("Its start position is $it.")
    }
}


data class MultiportService(
    var url: String?,
    var port: Int?,
) {
    fun query(it: String): String {
        return it
    }

    fun prepareRequest(): String {
        return "Result for query 'Default request "
    }
}