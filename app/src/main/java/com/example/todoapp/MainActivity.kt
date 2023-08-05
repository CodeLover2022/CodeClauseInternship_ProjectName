package com.example.todoapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.Room.Dao
import com.example.todoapp.Room.Entity
import com.example.todoapp.Room.MyDatabase
import com.example.todoapp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),OnIItemClickListen {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var taskList: List<Entity>
    private var database: MyDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = MyDatabase.CreateDatabase(this)
        val dao = database!!.CallDao()
        taskList = ArrayList()
        val list = dao.fetchData()
        list.observe(this, {
            taskList = it
            binding.countTasks.text=it.size.toString()
            binding.recylclerView.layoutManager = LinearLayoutManager(this)
            binding.recylclerView.adapter = TodoAdapter(taskList,this)

        })

        binding.addBtn.setOnClickListener {
            showEditTextDialog(dao)
        }


    }

    @SuppressLint("MissingInflatedId")
    private fun showEditTextDialog(dao: Dao) {
        val builder = AlertDialog.Builder(this)
        val dialogBox = layoutInflater.inflate(R.layout.dialog_layout, null)
        val editbox = dialogBox.findViewById<EditText>(R.id.editNote)
        with(builder) {
            setTitle("Enter text")
            setPositiveButton("OK") { dialog, which ->
                if (editbox.text.isEmpty()) {
                    Toast.makeText(context, "please enter some text", Toast.LENGTH_SHORT).show()
                } else {
                    val note = editbox.text.toString()
                    Toast.makeText(context, "New Task added", Toast.LENGTH_SHORT).show()
                    GlobalScope.launch {
                        dao.insert(Entity(0, note))
                    }

                }

            }
            setNegativeButton("Cancel") { dialog, which ->
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
            }
            setView(dialogBox)
            show()
        }
    }

    override fun onDeleteclick(entity: Entity) {
        GlobalScope.launch {
            database!!.CallDao().delete(entity)

        }
        Toast.makeText(this,"Task Completed",Toast.LENGTH_SHORT).show()

    }


}