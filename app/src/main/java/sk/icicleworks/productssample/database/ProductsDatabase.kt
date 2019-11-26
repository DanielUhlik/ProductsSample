package sk.icicleworks.productssample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import sk.icicleworks.productssample.database.dao.ProductDao
import sk.icicleworks.productssample.database.entities.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao

}