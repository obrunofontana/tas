import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GroupsComponent } from './pages/groups/groups.component';
import { ProductsComponent } from './pages/products/products.component';
import { CustomersComponent } from './pages/customers/customers.component';
import { SalesComponent } from './pages/sales/sales.component';
import { SuppliersComponent } from './pages/suppliers/suppliers.component';
import { PurchasesComponent } from './pages/purchases/purchases.component';
import { CompositionComponent } from './pages/composition/composition.component';
import { IndexComponent } from './pages/index/index.component';

const routes: Routes = [
  { path: 'index', component: IndexComponent },
  { path: 'groups', component: GroupsComponent },
  { path: 'products', component: ProductsComponent },
  { path: 'customers', component: CustomersComponent },
  { path: 'sales', component: SalesComponent },
  { path: 'suppliers', component: SuppliersComponent },
  { path: 'purchases', component: PurchasesComponent },
  { path: 'compositions', component: CompositionComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
