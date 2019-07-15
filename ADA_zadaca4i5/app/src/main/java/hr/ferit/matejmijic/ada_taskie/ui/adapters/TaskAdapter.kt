package hr.ferit.matejmijic.ada_taskie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.matejmijic.ada_taskie.R
import hr.ferit.matejmijic.ada_taskie.model.Task



class TaskAdapter(private val onItemSelected: (Task) -> Unit): RecyclerView.Adapter<TaskHolder>() {



    private val data: MutableList<Task> = mutableListOf()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent,false)
        return TaskHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bindData(data[position],onItemSelected)
    }

    fun setData(data: MutableList<Task>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun setDataOrdered(data: MutableList<Task>){
        this.data.clear()
        this.data.addAll(data)
        this.data.sortBy { it.priority }
        notifyDataSetChanged()
    }

    fun getTaskAt(position: Int): Task{
        return data[position]
    }

    fun deleteItem(position: Int){
        data.removeAt(position)
    }




}