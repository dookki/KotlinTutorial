package enums.models

import kotlin.reflect.KProperty

data class User(val id: Int, var name: String, var email: String) {


    // you can also which properties determine "equal"
    // tự định nghĩa phương thức equal theo ý mik,
    // ở đây chỉ cần so sánh id, email bằng nhau là dc
    // ví dụ user 10 trường thì mik chỉ cần so sánh 2 trường mình cần.
    override fun equals(other: Any?): Boolean {
        return other is User
                && this.id == other.id
                && this.email == other.email

    }
    //property
    var age: Int by UserDelegate()

    // lazy delegates
    val description: String by lazy {
        "id: $id, name: $name, email: $email"
    }
}

class UserDelegate() {
    private var value: Int = 0

    //getter
    operator fun getValue(user: User, property: KProperty<*>): Int {
        println("call getValue of ${property.name}")
        return value
    }

    operator fun setValue(user: User, property: KProperty<*>, i: Int) {
        println("call setValue of ${property.name}")
        value = i
    }


}