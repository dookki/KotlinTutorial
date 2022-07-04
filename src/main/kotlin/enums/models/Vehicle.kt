package enums.models

// sealed is "abstract" = > cannot create object from it
sealed class Vehicle(var name: String, var year: Int) {
    override fun toString(): String = "name:$name, year:$year"
}