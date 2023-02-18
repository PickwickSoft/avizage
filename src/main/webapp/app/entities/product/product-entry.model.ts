export interface IProductEntry {
  productName: string;
  productUnitId: string;
  productCategoryName: string;
  productCategoryId: string;
  productBarCode: number;
  productId: number;
  storageName: string;
  storageId: number;
  quantity: number;
  purchasePrice: number;
  salePrice: number;
  id: number;
}

export class ProductEntry implements IProductEntry {
  constructor(
    public productName: string,
    public productUnitId: string,
    public productCategoryName: string,
    public productCategoryId: string,
    public productBarCode: number,
    public productId: number,
    public storageName: string,
    public storageId: number,
    public quantity: number,
    public purchasePrice: number,
    public salePrice: number,
    public id: number
  ) {}
}
