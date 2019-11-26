package sk.icicleworks.productssample.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sk.icicleworks.productssample.database.entities.Product
import sk.icicleworks.productssample.repositories.ProductsRepository
import sk.icicleworks.productssample.repositories.interfaces.IProductsRepository

class DashboardViewModel (val productsRepository: IProductsRepository) : ViewModel() {

    val allProducts: LiveData<List<Product>> = productsRepository.getSaved()

    fun addProduct(){
        productsRepository.insert(Product(null, "Name", "Short decs", "Long description", 2.5))
    }

    fun remove(product: Product){
        productsRepository.remove(product)
    }
}