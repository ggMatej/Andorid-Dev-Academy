package hr.ferit.brunozoric.taskie.model.request

class UpdateTaskRequest(val id: String, val title: String, val content: String, val taskPriority: Int) {
}