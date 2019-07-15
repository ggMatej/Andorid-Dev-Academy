package hr.ferit.brunozoric.taskie.data.response

data class GetTaskResponse(val id: String, val title: String, val content: String, val taskPriority: Int) {
}