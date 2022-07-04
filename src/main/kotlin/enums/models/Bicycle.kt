package enums.models

class Bicycle(name: String, year: Int, var hasBasket: Boolean): Vehicle(name, year) {

    override fun toString(): String {
        return "${super.toString()}, hasBasket: $hasBasket"
    }
}