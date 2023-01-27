export interface ICartItem {
  id: number;
  name: string;
  qty: number;
  price: number;
}

export class CartItem implements ICartItem {
  constructor(public id: number, public name: string, public qty: number, public price: number) {}
}
