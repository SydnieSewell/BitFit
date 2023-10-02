package com.example.bitfitpart1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class AddActivity: AppCompatActivity() {
    private lateinit var etCycle: EditText
    private lateinit var etCyc: EditText
    private lateinit var submitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_page)
        etCycle= findViewById(R.id.etCycle)
        etCyc = findViewById(R.id.etCyc)
        submitBtn = findViewById(R.id.recordBtn)

        submitBtn.setOnClickListener { addItem() }
    }

    private fun addItem() {
        val day = etCycle.text.toString()
        val mood = etCyc.text.toString()

        let {
            lifecycleScope.launch(IO) {
                val list = ArrayList<CycleEntity>()
                list.add(CycleEntity(dayOfweek = day, moodToday = mood))
                (application as CycleApplication).db.CycleDao().insertAll(list)
            }
        }

        finish()
    }
}