package sk.icicleworks.productssample.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import sk.icicleworks.productssample.database.entities.Product
import sk.icicleworks.productssample.repositories.interfaces.IProductsRepository

class DashboardViewModel (val productsRepository: IProductsRepository) : ViewModel() {

    val allProducts: LiveData<List<Product>> = productsRepository.getSaved()

}