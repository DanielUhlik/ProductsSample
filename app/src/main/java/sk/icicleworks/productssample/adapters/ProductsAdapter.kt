package sk.icicleworks.productssample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sk.icicleworks.productssample.R
import sk.icicleworks.productssample.adapters.itemViewModels.ProductItemViewModel
import sk.icicleworks.productssample.database.entities.Product
import sk.icicleworks.productssample.databinding.ProductItemBinding

class ProductsAdapter (private var products: List<Product>?, val onClickListener: (Product) -> Unit) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater)
        return ViewHolder(binding, onClickListener)
    }

    override fun getItemCount(): Int = products?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(products!![position])
    }

    fun updateData(products: List<Product>){
        this.products = products
        notifyDataSetChanged()
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