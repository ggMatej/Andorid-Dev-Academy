package hr.ferit.brunozoric.taskie.model.response

import android.util.EventLogTags
import hr.ferit.brunozoric.taskie.model.BackendTask

data class GetTaskResponse(val id: String, val title: String, val content: String, val taskPriority: Int) {
}