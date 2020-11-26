import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { RawMaterialEntity } from 'src/app/services/composition.service';
import {
  ProductEntity,
  ProductService,
} from 'src/app/services/product.service';

@Component({
  selector: 'app-composition-item',
  templateUrl: './composition-item.component.html',
  styleUrls: ['./composition-item.component.scss'],
})
export class CompositionItemComponent implements OnInit {
  public rawMaterial: RawMaterialEntity;
  public products: ProductEntity[] = [];

  constructor(
    private productService: ProductService,
    public dialogRef: MatDialogRef<CompositionItemComponent>
  ) {
    this.rawMaterial = new RawMaterialEntity();

    this.productService.findAll().subscribe((result) => {
      this.products = result as [];
    });
  }

  ngOnInit(): void {}

  public onDismiss(): void {
    this.dialogRef.close(false);
  }

  public onConfirm(): void {
    this.dialogRef.close(this.rawMaterial);
  }

  public changeItem(): void {
    this.rawMaterial.quantity = 1;
  }
}
