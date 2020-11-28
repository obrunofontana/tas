import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CustomHttpInterceptor } from './interceptors/custom.interceptor';

import { MatNativeDateModule } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatListModule } from '@angular/material/list';
import { MatDividerModule } from '@angular/material/divider';
import { MatTabsModule } from '@angular/material/tabs';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatBadgeModule } from '@angular/material/badge';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

// Default locale BR
import localePt from '@angular/common/locales/pt';
import { registerLocaleData } from '@angular/common';

import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog.component';
import { GroupsComponent } from './pages/groups/groups.component';
import { SalesComponent } from './pages/sales/sales.component';
import { ProductsComponent } from './pages/products/products.component';
import { CustomersComponent } from './pages/customers/customers.component';
import { ItemSaleComponent } from './pages/sales/item-sale/item-sale.component';
import { TotalSalePipe } from './pipes/total-sale.pipe';
import { MenuComponent } from './components/menu/menu.component';
import { SuppliersComponent } from './pages/suppliers/suppliers.component';
import { PurchasesComponent } from './pages/purchases/purchases.component';
import { ItemPurchaseComponent } from './pages/purchases/item-purchase/item-purchase.component';
import { CompositionComponent } from './pages/composition/composition.component';
import { CompositionItemComponent } from './pages/composition/composition-item/composition-item.component';
import { IndexComponent } from './pages/index/index.component';
import { MaskPipe } from './pipes/mask.pipe';

registerLocaleData(localePt, 'pt');

@NgModule({
  declarations: [
    AppComponent,
    ConfirmDialogComponent,
    GroupsComponent,
    SalesComponent,
    ProductsComponent,
    CustomersComponent,
    ItemSaleComponent,
    TotalSalePipe,
    MenuComponent,
    SuppliersComponent,
    PurchasesComponent,
    ItemPurchaseComponent,
    ItemSaleComponent,
    CompositionComponent,
    CompositionItemComponent,
    IndexComponent,
    MaskPipe,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatTableModule,
    MatMenuModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatProgressBarModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatSnackBarModule,
    MatDialogModule,
    MatListModule,
    MatDividerModule,
    MatTabsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatBadgeModule,
    MatProgressSpinnerModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CustomHttpInterceptor,
      multi: true,
    },
    {
      provide: LOCALE_ID,
      useValue: 'pt',
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
