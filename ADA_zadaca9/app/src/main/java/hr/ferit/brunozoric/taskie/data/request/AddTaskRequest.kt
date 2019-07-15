package hr.ferit.brunozoric.taskie.data.request

data class AddTaskRequest(val title: String, val content: String, val taskPriority: Int)