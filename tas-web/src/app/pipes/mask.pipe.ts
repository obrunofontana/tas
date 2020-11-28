import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'mask'
})
export class MaskPipe implements PipeTransform {
  transform(value: string, ...args: unknown[]): string {
    const [mask] = args;
    const valueToMasked = value.replace(/\D/g, '');

    if (mask === 'cpf') {
      return valueToMasked.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "\$1.\$2.\$3-\$4");
    }

    return valueToMasked.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/g, "\$1.\$2.\$3/\$4-\$5");
  }
}
