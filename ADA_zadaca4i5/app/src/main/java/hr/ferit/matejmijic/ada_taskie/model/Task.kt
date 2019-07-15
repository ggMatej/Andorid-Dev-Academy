package hr.ferit.matejmijic.ada_taskie.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import hr.ferit.matejmijic.ada_taskie.model.Priority

@Entity(tableName = "tasks")
data class Task (
    @PrimaryKey (autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description")val description: String,
    @ColumnInfo (name = "priority") val priority: Priority
)


