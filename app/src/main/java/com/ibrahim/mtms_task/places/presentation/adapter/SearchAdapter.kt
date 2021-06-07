package com.ibrahim.mtms_task.places.presentation.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.mtms_task.R
import com.ibrahim.mtms_task.model.PlaceUiModel
import kotlinx.android.synthetic.main.item_location_search.view.*


class SearchAdapter(
    val data: ArrayList<PlaceUiModel>,
    val onItemClicked: (location: PlaceUiModel) -> Unit
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var searchQuery = ""

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_location_search, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])

        holder.itemView.setOnClickListener {
            onItemClicked(data[position])
        }

    }

    fun setList(list: List<PlaceUiModel>) {
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    class ViewHolder constructor(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: PlaceUiModel) {
            view.tvPlace.text = model.name

        }




    }
}