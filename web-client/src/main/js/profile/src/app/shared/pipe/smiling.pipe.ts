import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'smiling'
})
export class SmilingPipe implements PipeTransform {

  transform(value: string): string {
    return value + ' :)';
  }

}
