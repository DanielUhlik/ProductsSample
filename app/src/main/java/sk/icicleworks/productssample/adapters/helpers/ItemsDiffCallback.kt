package sk.icicleworks.productssample.adapters.helpers

import androidx.recyclerview.widget.DiffUtil
import sk.icicleworks.productssample.database.entities.Product


class ItemsDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}