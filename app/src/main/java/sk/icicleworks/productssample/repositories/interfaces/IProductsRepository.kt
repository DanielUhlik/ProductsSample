package sk.icicleworks.productssample.repositories.interfaces

import androidx.lifecycle.LiveData
import sk.icicleworks.productssample.database.entities.Product

interface IProductsRepository {
    fun insert(product: Product)
    fun getSaved() : LiveData<List<Product>>
    suspend fun getById(id: Int) : Product
    fun update(product: Product)
    fun remove(product: Product)
}