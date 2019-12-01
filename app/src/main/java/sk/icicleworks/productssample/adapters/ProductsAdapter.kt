package sk.icicleworks.productssample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sk.icicleworks.productssample.adapters.helpers.ItemsDiffCallback
import sk.icicleworks.productssample.adapters.itemViewModels.ProductItemViewModel
import sk.icicleworks.productssample.database.entities.Product
import sk.icicleworks.productssample.databinding.ProductItemBinding

class ProductsAdapter(val onClickListener: (Product) -> Unit) :
    ListAdapter<Product, ProductsAdapter.ViewHolder>(ItemsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater)
        return ViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ProductItemBinding, private val onClickListener: (Product) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product?) = with(itemView) {
            binding.item = ProductItemViewModel.create(item!!, context)
            binding.executePendingBindings()
            itemView.setOnClickListener {
                onClickListener(item)
            }
        }
    }
}