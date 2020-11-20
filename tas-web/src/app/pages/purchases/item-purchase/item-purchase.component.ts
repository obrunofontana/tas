import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { ItemPurchaseEntity } from 'src/app/services/purchase.service';
import {
  ProductEntity,
  ProductService,
} from 'src/app/services/product.service';

@Component({
  selector: 'app-item-purchase',
  templateUrl: './item-purchase.component.html',
  styleUrls: ['./item-purchase.component.scss'],
})
export class ItemPurchaseComponent implements OnInit {
  public itemPurchase: ItemPurchaseEntity;
  public products: ProductEntity[] = [];

  constructor(
    private productService: ProductService,
    public dialogRef: MatDialogRef<ItemPurchaseComponent>
  ) {
    this.itemPurchase = new ItemPurchaseEntity();

    this.productService.findAll().subscribe((result) => {
      this.products = result as [];
    });
  }
  ngOnInit(): void {}

  public onDismiss(): void {
    this.dialogRef.close(false);
  }

  public onConfirm(): void {
    this.dialogRef.close(this.itemPurchase);
  }

  public changeItem(): void {
    this.itemPurchase.price = this.itemPurchase.product.price;
    this.itemPurchase.quantity = 1;
  }
}
