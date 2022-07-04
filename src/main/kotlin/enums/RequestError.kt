package enums

enum class RequestError(val massage: String) {
    BAD_REQUEST("Invalid request"),
    INTERNAL_ERROR("Internal server error"),
    SUCCESS("Success request");

    // you can define more method here
    fun wordCount() = massage.trim().split("\\s+".toRegex()).size
}