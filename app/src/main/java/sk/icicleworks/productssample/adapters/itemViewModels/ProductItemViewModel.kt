package sk.icicleworks.productssample.adapters.itemViewModels

import android.content.Context
import sk.icicleworks.productssample.database.entities.Product
import java.util.*

data class ProductItemViewModel (
    val id : Int,
    val name : String,
    val shortDescription : String,
    val longDescription : String,
    val price : String
) {
    companion object {
        fun create(product: Product, context : Context) : ProductItemViewModel {
            val userCurrency = Currency.getInstance(context.resources.configuration.locale)
            val price = "%.2f${userCurrency.symbol}".format(product.price)

            return ProductItemViewModel(
                product.id ?: 0,
                product.name,
                product.shortDescription,
                product.longDescription,
                price
            )
        }
    }
}