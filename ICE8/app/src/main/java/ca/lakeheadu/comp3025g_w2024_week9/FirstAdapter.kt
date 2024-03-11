package ca.lakeheadu.comp3025g_w2024_week9

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.lakeheadu.comp3025g_w2024_week9.databinding.TextRowItemBinding

class FirstAdapter(private val dataSet: Array<TVShow>) :
    RecyclerView.Adapter<FirstAdapter.ViewHolder>()
{

    class ViewHolder(val binding: TextRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder
    {
        // Inflate the layout with view binding
        val binding = TextRowItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int)
    {
        // Use view binding to set the text
        viewHolder.binding.title.text = dataSet[position].title
        viewHolder.binding.subTitle.text = dataSet[position].subTitle
    }

    override fun getItemCount() = dataSet.size
}
