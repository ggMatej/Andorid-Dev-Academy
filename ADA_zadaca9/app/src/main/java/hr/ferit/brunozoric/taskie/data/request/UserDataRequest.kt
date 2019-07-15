package hr.ferit.brunozoric.taskie.data.request

data class UserDataRequest(val email: String, val password: String, val name: String? = null)