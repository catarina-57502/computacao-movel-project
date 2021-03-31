package pt.ulusofona.deisi.a2020.cm.g4

import android.content.Context
import android.text.AlteredCharSequence.make
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_test_detail.view.*
import kotlinx.android.synthetic.main.item_test.view.*

class TestAdapter(private val context: Context, private val layout: Int, private val items: ArrayList<Test>, var clickListner: onTestItemClickListener) :  RecyclerView.Adapter<TestAdapter.TestViewHolder>(){

    class TestViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val date: TextView = view.text_date
        val result: TextView = view.text_result
        val local: TextView = view.text_local
        val dateReg: TextView = view.text_dateReg

        fun initialize(item: Test, action:onTestItemClickListener){
            date.text = item.date
            result.text = item.result
            local.text = item.local
            dateReg.text = item.dateReg

            itemView.setOnClickListener{
                action.onItemClick(item,adapterPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder(LayoutInflater.from(context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {

        holder.initialize(items.get(position),clickListner)


    }

    override fun getItemCount() = items.size

    interface onTestItemClickListener{
        fun onItemClick(item: Test, position: Int)
    }

}