package enums.models

import kotlin.properties.Delegates

class Product(val map: Map<String, Any>) {
    val name: String by map
    val price: Int by map

    override fun toString(): String {
        return "name: $name, price: $price"
    }

    // observable property -> quan sát sự thay đổi
    var description: String by Delegates.observable("Initial value") { property, oldValue, newValue ->
        println("${property.name}: $oldValue -> $newValue")
    }

    // vetoable ->  quan sát thay đổi và phê duyệt
    var count: Int by Delegates.vetoable(0) { property, oldValue, newValue ->
        println("$oldValue -> $newValue")
        newValue > 0// validation, lay gia tri duong, am thi lay gia tri cu
    }
}