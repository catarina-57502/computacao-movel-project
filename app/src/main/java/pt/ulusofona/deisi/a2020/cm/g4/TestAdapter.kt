package pt.ulusofona.deisi.a2020.cm.g4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_test.view.*

class TestAdapter(private val context: Context, private val layout: Int, private val items: ArrayList<Test>) :  RecyclerView.Adapter<TestAdapter.HistoryViewHolder>(){

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view){
        /*
        val date: TextView = view.text_date
        val result: TextView = view.text_result
        val local: TextView = view.text_local
         */
        val dateReg: TextView = view.text_dateReg
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(LayoutInflater.from(context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        /*
        holder.date.text = items[position].date
        holder.result.text = items[position].result
        holder.local.text = items[position].local
         */
        holder.dateReg.text = items[position].dateReg
    }

    override fun getItemCount() = items.size
}