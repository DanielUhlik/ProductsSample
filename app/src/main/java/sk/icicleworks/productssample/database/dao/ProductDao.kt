package sk.icicleworks.productssample.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import sk.icicleworks.productssample.database.entities.Product

@Dao
interface ProductDao {
    @Insert
    fun insert(product: Product)

    @Delete
    fun delete(product: Product)

    @Update
    fun update(product: Product)

    @Query("SELECT * FROM products WHERE id == :id")
    fun getById(id: Int) : Product

    @Query("SELECT * FROM products")
    fun getAll() : LiveData<List<Product>>
}