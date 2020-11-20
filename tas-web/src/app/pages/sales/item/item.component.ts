import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { ItemSaleOrderEntity } from 'src/app/services/sale-order.service';
import {
  ProductEntity,
  ProductService,
} from 'src/app/services/product.service';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss'],
})
export class ItemComponent implements OnInit {
  public itemSale: ItemSaleOrderEntity;
  public products: ProductEntity[] = [];

  constructor(
    private productService: ProductService,
    public dialogRef: MatDialogRef<ItemComponent>
  ) {
    this.itemSale = new ItemSaleOrderEntity();

    this.productService.findAll().subscribe((result) => {
      this.products = result as [];
    });
  }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  public onDismiss(): void {
    this.dialogRef.close(false);
  }

  public onConfirm(): void {
    this.dialogRef.close(this.itemSale);
  }

  public changeItem(): void {
    this.itemSale.price = this.itemSale.product.price;
    this.itemSale.quantity = 1;
  }
}
