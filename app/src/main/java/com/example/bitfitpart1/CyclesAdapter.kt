package com.example.bitfitpart1

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "CyclesAdapter"
class CyclesAdapter(private val context: Context, private val cycles: List<Cycle>):
    RecyclerView.Adapter<CyclesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.cycle_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder position $position")
        val movie = cycles[position]
        holder.bind(movie)
    }

    override fun getItemCount() = cycles.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDay = itemView.findViewById<TextView>(R.id.DayOfWeek)
        private val tvMoodOfDay = itemView.findViewById<TextView>(R.id.mood)

        fun bind(cycle: Cycle) {
            tvDay.text = cycle.dayOfweek
            tvMoodOfDay.text = cycle.moodToday
        }
    }
}