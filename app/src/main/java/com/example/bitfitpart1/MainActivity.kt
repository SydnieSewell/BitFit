package com.example.bitfitpart1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var rvCycle: RecyclerView
    private lateinit var addBtn: Button
    private var entries: MutableList<Cycle> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCycle = findViewById(R.id.rvCycle)
        addBtn = findViewById(R.id.addBtn)

        // Create adapter passing in the list
        val adapter = CyclesAdapter(this, entries)
        // Attach the adapter to the RecyclerView to populate items
        rvCycle.adapter = adapter
        // Set layout manager to position the items
        rvCycle.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            (application as CycleApplication).db.CycleDao()
                .getAll().collect { databaseList ->
                    databaseList.map { entity ->
                        CycleEntity(
                            entity.id,
                            entity.dayOfweek,
                            entity.moodToday,
                        )
                    }.also { mappedList ->
                        val cycleEnteries = mappedList.map { CycleEntity ->
                            Cycle(
                                CycleEntity.dayOfweek,
                                CycleEntity.moodToday,
                            )
                        }
                        entries.clear()
                        entries.addAll(cycleEnteries)
                        rvCycle.adapter?.notifyDataSetChanged()
                    }
                }
        }

        addBtn.setOnClickListener { addBtnPress() }
    }

    private fun addBtnPress() {
        val intent = Intent(this, AddActivity::class.java)
        this.startActivity(intent)
    }
}