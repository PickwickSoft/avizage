import { Component, OnInit } from '@angular/core';
import { StockService } from '../stock.service';
import { ProductService } from '../../entities/product/product.service';
import { IStorage } from '../../entities/storage/storage.model';
import { HttpResponse } from '@angular/common/http';
import { IProductEntry } from '../../entities/product/product-entry.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ICategory } from '../../entities/category/category.model';
import { UnitService } from '../../entities/unit/unit.service';
import { IUnit } from '../../entities/unit/unit.model';
import { MatDialogService } from '../../shared/dialog/mat-dialog.service';
import { CategoryUpdateComponent } from './category/update/category-update.component';

@Component({
  selector: 'stock-entry',
  templateUrl: 'entry.component.html',
  styleUrls: ['./entry.component.scss'],
})
export class EntryComponent implements OnInit {
  storages: IStorage[] = [];
  productId: string = '';
  productEntry?: IProductEntry;
  currentStorage?: IStorage;
  categories?: ICategory[];
  units?: IUnit[];
  loading: boolean = false;
  productForm = new FormGroup({
    productId: new FormControl({ value: '', disabled: true }, { nonNullable: true, validators: [Validators.required] }),
    productName: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    productDescription: new FormControl(),
    productCategoryId: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    purchasePrice: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.pattern('^(0|([1-9][0-9]*))(.[0-9]+)?$')],
    }),
    salePrice: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.pattern('^(0|([1-9][0-9]*))(.[0-9]+)?$')],
    }),
    quantity: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.pattern('^(0|([1-9][0-9]*))(.[0-9]+)?$')],
    }),
    productUnitId: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
  });

  constructor(
    private stockService: StockService,
    private productService: ProductService,
    private unitService: UnitService,
    private dialogService: MatDialogService
  ) {}

  ngOnInit(): void {
    this.loadData();
  }

  private loadData() {
    this.stockService.getAllStorages().subscribe((res: HttpResponse<IStorage[]>) => {
      this.storages = res.body || [];
    });
    this.loadCategories();
    this.unitService.getAllUnits().subscribe((res: HttpResponse<IUnit[]>) => {
      this.units = res.body || [];
    });
  }

  private loadCategories() {
    this.productService.getAllCategories().subscribe((res: HttpResponse<ICategory[]>) => {
      this.categories = res.body || [];
    });
  }

  handleKeyDown(event: KeyboardEvent) {
    let id = this.productId;
    if (event.key === 'Enter') {
      this.loading = true;
      this.stockService.getProductByIdAndStorageId(Number.parseInt(this.productId), this.currentStorage?.id || 1).subscribe(
        (res: HttpResponse<IProductEntry>) => {
          this.productEntry = res.body || undefined;
          if (this.productEntry !== undefined) {
            this.productForm.patchValue({
              productId: this.productEntry.productId.toString(),
              productName: this.productEntry.productName,
              productDescription: '',
              productCategoryId: this.productEntry.productCategoryId,
              purchasePrice: this.productEntry.purchasePrice.toString(),
              salePrice: this.productEntry.salePrice.toString(),
              quantity: this.productEntry.quantity.toString(),
              productUnitId: this.productEntry.productUnitId,
            });
          }
        },
        (err: any) => {
          this.resetForm(id);
        }
      );
      this.loading = false;
      this.productId = '';
    }
  }

  private resetForm(id?: string) {
    this.productForm.setValue({
      productId: '',
      productName: '',
      productDescription: '',
      productCategoryId: '',
      purchasePrice: '',
      salePrice: '',
      quantity: '',
      productUnitId: '',
    });
  }

  createProduct() {
    // TODO: Implement
  }

  addNewCategory() {
    const dialog = this.dialogService.openDialog(CategoryUpdateComponent, {});
    dialog.closed?.subscribe(reason => {
      if (reason === 'created') {
        this.loadCategories();
      }
    });
  }
}
