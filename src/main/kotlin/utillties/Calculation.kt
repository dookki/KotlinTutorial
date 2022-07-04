package utillties

val URL = "EXAMPLE.COM"

class Calculation {

    // trong 1 class chúng ta có thể tạo property, method static vs companion object
    // dc tạo ra mà không cần khởi tạo đối tượng
    // trong java tất cả property, method static để trong class nếu để biến (property) ra ngoài class sẽ không biên dịch dc,
    // còn kotlin có thể riêng file .kt để gọi, or để bên ngoài class (Example line 3)
    companion object {
        fun multiply(x: Int, y: Int): Int = x * y
        val PI = 3.1416
    }
}