package adapter

import Book
import activity.DescriptionActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pallav.bookhub.R
import com.squareup.picasso.Picasso

class DashboardRecyclerAdapter(val context: Context, val itemList: ArrayList<Book>) : RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {

    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtBookName: TextView = view.findViewById(R.id.txtBookName)
        val txtAuthor: TextView = view.findViewById(R.id.txtAuthor)
        val txtBookPrice: TextView = view.findViewById(R.id.txtBookPrice)
        val txtBookRating: TextView = view.findViewById(R.id.txtRating)
        val imgBookName: ImageView = view.findViewById(R.id.imgBookImage)
        val llContent: LinearLayout = view.findViewById(R.id.llContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_dashboard_single_row, parent, false)

        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val books = itemList[position]
        holder.txtBookName.text = books.bookName
        holder.txtAuthor.text = books.bookAuthor
        holder.txtBookRating.text = books.bookRating
        holder.txtBookPrice.text = books.bookPrice
        //holder.imgBookName.setImageResource(books.bookImage)
        Picasso.get().load(books.bookImage).error(R.drawable.default_book_cover).into(holder.imgBookName)

            holder.llContent.setOnClickListener {
                val intent = Intent(context, DescriptionActivity::class.java)
                intent.putExtra("book_id", books.bookId)
                context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
            return itemList.size
        }
    }
