package sk.icicleworks.productssample.injection.modules

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import sk.icicleworks.productssample.ui.dashboard.DashboardViewModel
import sk.icicleworks.productssample.ui.productEditor.ProductEditorViewModel

val viewModelsModule = module {

    viewModel { DashboardViewModel(get()) }
    viewModel { ProductEditorViewModel(get()) }
}