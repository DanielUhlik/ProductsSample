package sk.icicleworks.productssample.ui.productEditor

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sk.icicleworks.productssample.database.entities.Product
import sk.icicleworks.productssample.repositories.interfaces.IProductsRepository

class ProductEditorViewModel(private val productsRepository: IProductsRepository) : ViewModel() {

    val productName = MutableLiveData<String>().apply { postValue("") }
    val productShortDesc = MutableLiveData<String>().apply { postValue("") }
    val productLongDesc = MutableLiveData<String>().apply { postValue("") }
    val productPrice = MutableLiveData<String>().apply { postValue("0.0") }
    var productId : Int = 0

    val editMode :Boolean
        get() {
            return productId > 0
        }

    private fun getProduct(): Product {
        var priceDouble = 0.0
        try {
            priceDouble = productPrice.value!!.toDouble()
        }
        catch (e : Exception){
            Log.e("priceToDouble", e.message)
        }
        return Product(
            if (productId == 0) null else productId,
            productName.value!!,
            productShortDesc.value!!,
            productLongDesc.value!!,
            priceDouble
        )
    }

    fun saveChanges() {
        if (editMode)
            updateProduct()
        else
            createProduct()
    }

    fun createProduct(){
        productsRepository.insert(getProduct())
    }

    fun updateProduct(){
        productsRepository.update(getProduct())
    }

    fun removeProduct(){
        productsRepository.remove(getProduct())
    }

    suspend fun loadProduct(id: Int){
        productId = id
        val product = productsRepository.getById(id)
        withContext(Dispatchers.Main) {
            productShortDesc.value = product.shortDescription
            productLongDesc.value = product.longDescription
            productName.value = product.name
            productPrice.value = "%.2f".format(product.price)
        }
    }
}
