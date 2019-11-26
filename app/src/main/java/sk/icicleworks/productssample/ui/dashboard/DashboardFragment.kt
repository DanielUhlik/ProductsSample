package sk.icicleworks.productssample.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.android.viewmodel.ext.android.viewModel
import sk.icicleworks.productssample.R
import sk.icicleworks.productssample.adapters.ProductsAdapter
import sk.icicleworks.productssample.adapters.decorations.GridSpacingItemDecoration
import sk.icicleworks.productssample.database.entities.Product
import sk.icicleworks.productssample.ui.productEditor.ProductEditorFragment
import sk.icicleworks.productssample.ui.productEditor.ProductEditorFragmentArgs

class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productsAdapter = ProductsAdapter(null){product ->
            openEditor(product)
        }

        products_recycler_view.setHasFixedSize(true)
        products_recycler_view.layoutManager = GridLayoutManager(context, 2)
        products_recycler_view.adapter = productsAdapter
        products_recycler_view.addItemDecoration(GridSpacingItemDecoration(2, 30, true))

        dashboardViewModel.allProducts.observe(this, Observer {products ->
            productsAdapter.updateData(products)
            if (products.isNotEmpty())
                empty_recycler_text.visibility = View.GONE
            else
                empty_recycler_text.visibility = View.VISIBLE
        })

        add_product_fab.setOnClickListener {
            val extras = FragmentNavigatorExtras(
                add_product_fab to "shared_fab"
            )
            findNavController().navigate(R.id.action_navigation_dashboard_to_product_editor_fragment, null, null, extras)
        }
    }

    private fun openEditor(product: Product){
        val args = DashboardFragmentDirections.actionNavigationDashboardToProductEditorFragment(product.id ?: 0)
        findNavController().navigate(args)
    }
}