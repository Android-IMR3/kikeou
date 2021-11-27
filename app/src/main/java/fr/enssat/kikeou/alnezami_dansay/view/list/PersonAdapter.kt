package fr.enssat.kikeou.alnezami_dansay.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.enssat.kikeou.alnezami_dansay.R
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda


class PersonAdapter: RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private var agendaList =  emptyList<Agenda>()


    override fun getItemCount() = agendaList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = agendaList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            val action = ListPersonFragmentDirections.actionListFragmentToContactFragment(item)
            holder.itemView.findNavController().navigate(action)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(LayoutInflater.from(parent.context).inflate(R.layout.costume_row,parent,false) as ViewGroup)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.name_txt)

        val avatar: ImageView =  itemView.findViewById(R.id.photo)

        fun bind(person: Agenda) {
            val res = itemView.context.resources
            name.text = person.name

            Picasso.get().load("https://source.unsplash.com/1600x900/?nature,water").into(avatar)

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
    fun setData(agenda: List<Agenda>){
        this.agendaList = agenda
        notifyDataSetChanged()

    }
}