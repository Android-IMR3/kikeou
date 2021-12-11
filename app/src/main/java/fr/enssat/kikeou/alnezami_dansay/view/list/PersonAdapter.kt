package fr.enssat.kikeou.alnezami_dansay.view.list

import android.icu.util.Calendar
import android.location.Location
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.enssat.kikeou.alnezami_dansay.R
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import fr.enssat.kikeou.alnezami_dansay.model.entity.LOC
import fr.enssat.kikeou.alnezami_dansay.model.entity.Status


class PersonAdapter: RecyclerView.Adapter<PersonAdapter.ViewHolder>(),Filterable {

    private var agendaList =  emptyList<Agenda>()
    private var agendaListFilter = ArrayList<Agenda>()

    override fun getItemCount() = agendaListFilter.size

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = agendaListFilter[position]
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
        private val avatar: ImageView =  itemView.findViewById(R.id.photo)
        private val status: ImageView =  itemView.findViewById(R.id.imageViewStatut)
        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(a: Agenda) {

            name.text = a.name
            if(a.photo.isEmpty()){
                a.photo = "https://source.unsplash.com/1600x900/?avatar,person"
            }
            Picasso.get().load(a.photo).into(avatar)
            status.setImageResource(getDayStatus(a.loc))
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.costume_row, parent, false)

                return ViewHolder(view)
            }
        }
        @RequiresApi(Build.VERSION_CODES.N)
        fun getDayStatus(location: List<LOC>):Int{
            val calendar: Calendar = Calendar.getInstance()
            val day: Int = calendar.get(Calendar.DAY_OF_WEEK)
            var path = 0
            path = when (day) {
                Calendar.MONDAY -> {
                    setImageByStatus(location.get(0).value)
                }
                Calendar.TUESDAY -> {
                    setImageByStatus(location.get(1).value)
                }
                Calendar.WEDNESDAY -> {
                    setImageByStatus(location.get(2).value)
                }
                Calendar.THURSDAY -> {
                    setImageByStatus(location.get(3).value)
                }
                Calendar.FRIDAY -> {
                    setImageByStatus(location.get(4).value)
                }
                else ->   setImageByStatus(location.get(0).value)
            }
            return path
        }
        fun setImageByStatus(location: String):Int{
            var path=0
            path = when (location) {
                Status.OFF.name -> {
                    R.drawable.vacation
                }
                Status.HOME.name -> {
                    R.drawable.homepage
                }
                Status.WORK.name -> {
                    R.drawable.work
                }
                else -> {
                    R.drawable.homepage
                }
            }


            return  path
        }

    }
    fun setData(agenda: List<Agenda>){
        this.agendaList = agenda as ArrayList<Agenda>
        this.agendaListFilter = agenda as ArrayList<Agenda>
        notifyDataSetChanged()

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    agendaListFilter = agendaList as ArrayList<Agenda>
                } else {
                    val resultList = ArrayList<Agenda>()
                    for (row in agendaListFilter) {
                        if (row.name.lowercase().contains(constraint.toString().lowercase())) {
                            resultList.add(row)
                            Log.i("search",row.name+"  : "+constraint)
                        }else{
                            Log.i("search don't match",row.name+"  : "+constraint)
                        }
                    }
                    agendaListFilter = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = agendaListFilter
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                agendaListFilter = results?.values as ArrayList<Agenda>
                notifyDataSetChanged()
            }
        }
    }



}