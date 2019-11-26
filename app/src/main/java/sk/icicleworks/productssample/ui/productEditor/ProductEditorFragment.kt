package sk.icicleworks.productssample.ui.productEditor


import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.transition.ChangeBounds
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_product_editor.*
import kotlinx.coroutines.*
import org.koin.android.viewmodel.ext.android.viewModel
import sk.icicleworks.productssample.R
import sk.icicleworks.productssample.databinding.FragmentProductEditorBinding
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import sk.icicleworks.productssample.helpers.hideKeyboard


class ProductEditorFragment : Fragment() {


    private val viewModel: ProductEditorViewModel by viewModel()
    val args: ProductEditorFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentProductEditorBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_editor, container, false)
        GlobalScope.launch {
            if (args.productIdToEdit != 0) {
                viewModel.loadProduct(args.productIdToEdit)
            }
            binding.item = viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        save_btn.setOnClickListener {
            if (viewModel.editMode)
                viewModel.updateProduct()
            else
                viewModel.createProduct()
            navigateUp()
        }

        remove_btn.setOnClickListener {
            viewModel.removeProduct()
            navigateUp(true)
        }
    }

    fun navigateUp(deleted: Boolean = false){
        activity?.hideKeyboard()

        val builder = AlertDialog.Builder(context!!)
        builder.setTitle(if (deleted) resources.getString(R.string.deleted_dialog_title) else resources.getString(R.string.saved_dialog_title))
        builder.setMessage(if (deleted) resources.getString(R.string.deleted_dialog_desc) else resources.getString(R.string.saved_dialog_desc))
        builder.setPositiveButton(resources.getString(R.string.ok)){ _, _ ->
            findNavController().navigateUp()
        }

        builder.show()
    }

}
