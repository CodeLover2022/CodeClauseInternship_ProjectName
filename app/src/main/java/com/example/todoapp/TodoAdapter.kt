package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Room.Entity

class TodoAdapter(private val arr:List<Entity>,private val listener:OnIItemClickListen):RecyclerView.Adapter<TodoAdapter.MyViewholder>() {

    class MyViewholder(itemView: View):RecyclerView.ViewHolder(itemView) {
  val task=itemView.findViewById<TextView>(R.id.tasks)
        val del_btn=itemView.findViewById<ImageView>(R.id.imageView_del)

 }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.MyViewholder {
        return TodoAdapter.MyViewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.recy, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return arr.size
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.task.text=arr[position].task
        holder.del_btn.setOnClickListener {
            listener.onDeleteclick(arr[holder.adapterPosition])
            notifyDataSetChanged()
            notifyItemRemoved(position)
        }

    }

}