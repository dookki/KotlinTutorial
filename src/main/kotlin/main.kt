import enums.Quality
import enums.RequestError
import enums.models.*
import storage.MyDBRepository
import storage.MySQLRepository
import utillties.Calculation

fun main() {
    sayHello("son")
    println("sum 3 & 4: ${sum(3, 4)}")
    println("sum 3 & 4: ${sum(x = 3, y = 4)}")
    showColor(255, 0, 0, 0.5f)
    like("kiwi", "apple", "banana", "orange")
    val z = 12 plus 5
    println(z)
    println("5 + 3 is: ${5 plus 3}")
    println("6 * 5 is: ${6 times 5}")
    println("Joinh" love "Marry")

    //
    val email: String? //nullable
    email = null

    // ?: -> "Elvis operator
    println("email: ${email ?: "son@gmail.com"}") // default value
    doSomeThing(1, 2, completion = { result: Int ->
        run {
            println("result is: $result")
        }
    })

    // more simpler way
    doSomeThing(4, 6) { result ->
        run { println("result is: $result") }
        //println("result is: $result")
    }

    // simplest way
    doSomeThing(4, 6) {
        println("result is: $it")
    }

    println("operation = ${operation(x = 10f)(20f)}")
    println(getFullName("Son", "Min"))

    println(url(0, 100))
    println(squaredNumber(34))
    println(squaredNumber1(2, 3))

    //
    url(1, 22).let {
        // khối block này dc thực hiện khi value khác NULL
        println("It means that url is NOT NULL")
        println(it)
    }

    /**
     * class is kotlin
     * data class
     * */

    // define an object from class
    val user1 = User(1, "son", "son@gmail.com")
    val user2 = User(1, "son", "son@gmail.com")
    println(user1) // @address memory object

    // 2 data objects with the same value => the same hashcode
    println(user1.hashCode()) // moi doi tuong se có 1 hashcode (vùng nhớ) khac nhau
    println(user2.hashCode())
    if (user1.equals(user2)) {
        // nội dung (content) bằng nhau nhưng 2 vùng chứa khác nhau
        // nên nếu sửa 1 trong 2 thì thằng còn lại không bị ảnh hưởng (class thường)
        // data-class sẽ có hashcode trùng nhau,
        println("user1 and user 2 the same content")
    }

    //
    user1.name = "John"
    println(user1) // you can change property`s value of a "val" object
    // nut you cannot reference to other object
    // user1 = User(2, "haha", "haha@gmail.com")

    //
    val user3 = User(3, "user3", "user3@gmail.com")
    // clone this object
    val user4 = user3.copy()

    // clone and change
    val user5 = user3.copy(email = "merry123@gmail.com")
    println(user3)
    println(user4)
    println(user5)


    /**
     * ENUM CLASS
     * */

    // example 1
    val quality: Quality = Quality.EXCELLENT
    val qualityMessage: String = when (quality) {
        // when = switch case
        Quality.BAD -> "this is bad"
        Quality.NORMAL -> "this is NORMAL"
        Quality.GOOD -> "this is GOOD"
        Quality.EXCELLENT -> "this is EXCELLENT"
        else -> "no message"
    }

    println(qualityMessage)

    // example 2
    val requestError: RequestError = RequestError.BAD_REQUEST
    println(requestError.massage)
    println(requestError.wordCount())

    /**
     * Define a key-value object
     * */
    val person1 = object {
        var name = "Son"
        var emailAddress = "Son@gmail.com"
        var age = 18
        override fun toString(): String = "name: $name, emailAddress: $emailAddress, age: $age"
    }
    println(person1)


    val person2 = mutableMapOf<String, Any>(
        "name" to "John",
        "email" to "John@gmail.com",
        "age" to 18
    )
    println(person2)

    // mapOf -> chỉ khởi tạo tạo và set giá trị 1 lần
    // mutableMapOf -> có thể thay đổi
    // mutable -> các thuộc tính bên trong có thể thay đổi, ko bị đóng băng (chỉ có thể getter)
    person2["email"] = "join123@gmail.com" // you can change if person2 is a "mutable map"
    println(person2)

    /**
     * companion object => like "static  method" in java
     * */

    // trong 1 class chúng ta có thể tạo property, method static vs companion object
    // dc tạo ra mà không cần khởi tạo đối tượng

    println(Calculation.multiply(3, 4))
    println("PI: ${Calculation.PI}")

    /**
     * sealed class
     * sealed is "abstract" = > cannot create object from it
     * */

    // không thể khởi tạo trực tiếp
    // Sealed types cannot be instantiated => it is "abstract"
    // val vehicle: Vehicle = Vehicle("haha", 2022)

    //ex2:
    val bicycle: Bicycle = Bicycle("vihaha", 2022, true)
    println(bicycle)

    //ex3:
    val car1: Car = Car("BWM-Z1000CC", 2022, 81.6f, 155)
    println(car1)
    /*println(car1.name)
    println(car1.horsePower)*/

    // you can update multiple properties here
    bicycle.apply {
        year = 2019
        hasBasket = false
    }
    println(bicycle)

    /**
     * check object type
     * */
    println(describeVehicle(vehicle = bicycle))
    println(describeVehicle(vehicle = car1))

    // extensions
    car1.running(123.4)
    println(car1)

    /**
     * list
     * */
    val someNumbers = mutableListOf<Int>(1, 4, 6, 7, -2, 9, -4)
    someNumbers.forEach { number ->
        print("[$number] ")
    }
    if (someNumbers.any { it < 0 }) {
        println("At least 1 item is negative")
    }

    if (someNumbers.all { it < 0 }) {
        println("All item are < 100")
    }

    if (someNumbers.none { it == 100 }) {
        println("No item has value = 100")
    }

    //
    val someFloats = mutableListOf<Float>(3.5f, 5.7f, 2.5f, 8.2f, 7.2f)
    someFloats[0] = 22.4f // no setter listOf -> using mutableListOf
    println(someFloats)


    //
    val cars = mutableListOf<Car>(
        Car("GLC60", 2010, 123.0f, 160),
        Car("GLC61", 2011, 123.1f, 161),
        Car("GLC62", 2012, 123.2f, 162),
        Car("GLC63", 2013, 123.3f, 163),
        Car("GLC64", 2014, 123.4f, 164),
        Car("GLC65", 2015, 123.5f, 165),
    )
    println(cars)

    // add first item
    println("------------Title---------------")
    println("Add 1 car to the first item")
    println("***")
    cars.add(0, Car("Toyota", 2013, 123.3f, 163))

    cars.forEach {
        println(it)
    }

    // add last item
    println("------------Title---------------")
    println("Add 1 car to the last item")
    println("***")

    cars.add(Car("Toyota", 2013, 123.3f, 163))

    cars.forEach {
        println(it)
    }

    // filter cars which year is between 2012 and 2014
    println("------------Title---------------")
    println("filter car")
    println("***")

    var filteredCars = cars.filter { it.year in 2012..2014 }
    for (item in filteredCars) {
        println(item)
    }

    // find cars name container "GLC63"
    // khong phan biet hoa thuong
    filteredCars = cars.filter { it.name.contains("GLC63", ignoreCase = true) }
    println("------------Title---------------")
    println("filter car name")
    println("***")

    for (item in filteredCars) {
        println(item)
    }

    //
    println("------------Title---------------")
    println("Sort the list, by horsePower")
    println("***")
    val sortedCars = cars.sortedBy { it.horsePower } // default ASC -> tang dan
    //var sortedCars = cars.sortedByDescending { it.horsePower } // DESC -> giam dan
    sortedCars.forEach {
        println(it)
    }

    // get car's name and add to a separated list
    // danh sách có nhiều phân tử nhưng mình chỉ cần lấy ra name
    // ánh xạ mảng (n)  -> mảng (n) với n đã dc biến đổi
    // mảng gốc và mảng đích, 2 mảng độc lập nhau, có số phần tử bằng nhau nhưng giá trị thay đổi
    val carName = cars.map {
        it.name
    }

    println("------------Title---------------")
    carName.forEach { println(it) }

    println("------------Title---------------")
    println("There are ${carName.count()} cars")
    // nếu sử dụng list[0], list[-1] nếu mảng rỗng có thể gây crash, nên sử dụng các collection fist(), last()
    println("First name: ${carName.first()}, Last name: ${carName.last()}")

    // split/partition a list
    val (newerCars, olderCars) = cars.partition { it.year > 2014 }
    println("debug now: $newerCars, $olderCars")

    // minimum, maximum
    println(cars.minOf { it.horsePower })
    println(cars.maxOf { it.horsePower })


    /**
     * Delegate
     * */
    // now we talk about delegate, interface
    // val repository = IStorageRepository() // can't init an object from interface
    val connectionString = "server=myserver,db=test,user=user,pass=pass"

    val repository: MySQLRepository = MySQLRepository(connectionString)
    println(repository.connectionString)

    val myDBRepository = MyDBRepository(connectionString)
    println(myDBRepository.makeConnection(connectionString))

    // delegated properties = make a separated class which override getter/setter
    val user6: User = User(11, "testDelegate", "testDelegate@gmail.com")
    user6.age = 30

    // some standard delegates: lazy, observable
    println(user6.description)

    // create object from key-value map
    val productA = Product(mapOf(
        "name" to "iphone 13 pro max",
        "price" to 33000000
    ))

    println(productA.toString())

    // observable => when property's  value changes => a function is run
    println("---------observable----------")
    productA.description = "haha"
    productA.description = "hihi"
    productA.description = "kaka"

    // property's type is Int
    println("---------property----------")
    productA.count = 2
    productA.count = 3
    productA.count = -1
    println(productA.count)
}