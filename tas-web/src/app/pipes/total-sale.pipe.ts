import { Pipe, PipeTransform } from '@angular/core';
import { ItemSaleOrderEntity } from '../services/sale-order.service';

@Pipe({
  name: 'totalSale',
})
export class TotalSalePipe implements PipeTransform {
  transform(items: ItemSaleOrderEntity[]): number {
    return items.reduce((total, item) => {
      return total + item.quantity * item.price;
    }, 0);
  }
}
