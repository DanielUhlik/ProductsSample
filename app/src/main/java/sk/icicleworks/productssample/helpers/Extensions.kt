package sk.icicleworks.productssample.helpers

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.hideKeyboard(){
    val view = this.currentFocus
    view?.let { v ->
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }
}