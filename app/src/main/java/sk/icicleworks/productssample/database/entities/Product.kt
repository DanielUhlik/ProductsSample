package sk.icicleworks.productssample.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product (@PrimaryKey(autoGenerate = true) var id : Int?,
                    @ColumnInfo(name = "name") var name : String,
                    @ColumnInfo(name = "short_desc") var shortDescription : String,
                    @ColumnInfo(name = "long_desc") var longDescription : String,
                    @ColumnInfo(name = "price") var price : Double) {

    companion object {
        fun create() : Product {
            return Product(null, "", "", "", 0.0)
        }
    }
}