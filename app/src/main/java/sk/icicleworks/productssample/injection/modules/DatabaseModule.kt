package sk.icicleworks.productssample.injection.modules

import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.dsl.module
import sk.icicleworks.productssample.database.ProductsDatabase

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), ProductsDatabase::class.java, "products_database").build()}

    single { get<ProductsDatabase>().productDao() }
}