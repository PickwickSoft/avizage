export interface ICartItem {
  id: number;
  qty: number;
  price: number;
}

export class CartItem implements ICartItem {
  constructor(public id: number, public name: string, public qty: number, public price: number) {}
}

export class APICartItem implements ICartItem {
  constructor(public id: number, public qty: number, public price: number) {}
}
