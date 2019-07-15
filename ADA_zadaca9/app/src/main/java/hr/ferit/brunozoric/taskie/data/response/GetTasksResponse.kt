package hr.ferit.brunozoric.taskie.data.response

import hr.ferit.brunozoric.taskie.data.BackendTask

data class GetTasksResponse(val notes: MutableList<BackendTask>? = mutableListOf())