package sk.icicleworks.productssample.injection.modules

import androidx.room.Room
import org.koin.dsl.module
import sk.icicleworks.productssample.database.ProductsDatabase
import sk.icicleworks.productssample.repositories.ProductsRepository
import sk.icicleworks.productssample.repositories.interfaces.IProductsRepository

val repositoriesModule = module {
    single<IProductsRepository> {ProductsRepository(get())}
}