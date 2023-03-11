import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Category, ICategory } from '../../../../entities/category/category.model';
import { ProductService } from '../../../../entities/product/product.service';

@Component({
  selector: 'category-update',
  templateUrl: './category-update.component.html',
})
export class CategoryUpdateComponent implements OnInit {
  categories: ICategory[] = [];
  categoryForm = new FormGroup({
    categoryName: new FormControl('', { validators: [this.isDuplicateValidator(), Validators.required] }),
  });

  constructor(private productService: ProductService) {}
  ngOnInit(): void {
    this.productService.getAllCategories().subscribe(res => {
      this.categories = res.body ?? [];
    });
  }

  submit() {
    if (
      this.categoryForm.value.categoryName !== null &&
      this.categoryForm.value.categoryName !== undefined &&
      this.categoryForm.value.categoryName !== ''
    ) {
      this.productService.createCategory(new Category(this.categoryForm.value.categoryName)).subscribe();
    }
  }

  private isDuplicateValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const duplicate = this.categories.find(cat => cat.name.toLowerCase() === control.value.toLowerCase()) !== undefined;
      return duplicate ? { duplicate: { value: control.value } } : null;
    };
  }
}
