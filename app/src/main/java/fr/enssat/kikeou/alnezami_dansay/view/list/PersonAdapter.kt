package fr.enssat.kikeou.alnezami_dansay.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.enssat.kikeou.alnezami_dansay.R
import fr.enssat.kikeou.alnezami_dansay.database.entity.Person


class PersonAdapter: RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private var personList =  emptyList<Person>()


    override fun getItemCount() = personList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = personList[position]
        holder.bind(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(LayoutInflater.from(parent.context).inflate(R.layout.costume_row,parent,false) as ViewGroup)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val first_name: TextView = itemView.findViewById(R.id.last_name_txt)
        val last_name: TextView = itemView.findViewById(R.id.first_name_txt)
        val avatar: ImageView =  itemView.findViewById(R.id.photo)

        fun bind(person: Person) {
            val res = itemView.context.resources
            first_name.text = person.first_name
            last_name.text = person.last_name
            Picasso.get().load(person.photo).into(avatar)

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.costume_row, parent, false)

                return ViewHolder(view)
            }
        }

    }
    fun setData(person: List<Person>){
        this.personList = person
        notifyDataSetChanged()

    }
}