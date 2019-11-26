package sk.icicleworks.productssample.repositories

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sk.icicleworks.productssample.database.dao.ProductDao
import sk.icicleworks.productssample.database.entities.Product
import sk.icicleworks.productssample.repositories.interfaces.IProductsRepository
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ProductsRepository (
    private val productDao: ProductDao,
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
) : IProductsRepository {
    override fun update(product: Product) {
        executor.execute { productDao.update(product) }
    }

    override suspend fun getById(id: Int): Product {
        return withContext(Dispatchers.IO) { productDao.getById(id) }
    }

    override fun insert(product: Product) {
        executor.execute {
            productDao.insert(product)
        }
    }

    override fun getSaved(): LiveData<List<Product>> {
        return productDao.getAll()
    }

    override fun remove(product: Product) {
        executor.execute {
            productDao.delete(product)
        }
    }

}