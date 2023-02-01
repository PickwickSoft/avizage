import { ICartItem } from '../cart-item/cart-item.model';

export interface IBill {
  id: number;
  products: ICartItem[];
  totalPrice: number;
  userName: string;
  created: Date;
}

export class Bill implements IBill {
  constructor(public id: number, public products: ICartItem[], public totalPrice: number, public userName: string, public created: Date) {}
}
