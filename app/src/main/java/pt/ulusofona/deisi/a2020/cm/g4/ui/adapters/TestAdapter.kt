package pt.ulusofona.deisi.a2020.cm.g4.ui.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_test_register.view.*
import kotlinx.android.synthetic.main.item_test.view.*
import pt.ulusofona.deisi.a2020.cm.g4.data.local.room.entities.Test

class TestAdapter(private val context: Context, private val layout: Int, private val items: List<Test>, var clickListner: onTestItemClickListener) :  RecyclerView.Adapter<TestAdapter.TestViewHolder>(){

    class TestViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val date: TextView = view.text_date
        val result: TextView = view.text_result
        val local: TextView = view.text_local
        val dateReg: TextView = view.text_dateReg
        val image: ImageView = view.text_image_view
        val text = view.text_test

        fun initialize(item: Test, action: onTestItemClickListener){
            date.text = item.date
            result.text = item.result
            local.text = item.local
            dateReg.text = item.dateReg
            image.setImageBitmap(BitmapFactory.decodeFile(item.image))

            if(item.result=="Positive"){
                text.setTextColor(Color.RED)
            }else{
                text.setTextColor(Color.GREEN)
            }


            itemView.setOnClickListener{
                action.onItemClick(item,adapterPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder(
            LayoutInflater.from(context).inflate(layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {

        holder.initialize(items.get(position),clickListner)


    }

    override fun getItemCount() = items.size

    interface onTestItemClickListener{
        fun onItemClick(item: Test, position: Int)
    }

}