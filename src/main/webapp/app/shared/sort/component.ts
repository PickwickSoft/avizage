import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { combineLatest, Observable } from 'rxjs';
import { SORT } from '../../config/navigation.constants';
import { ActivatedRoute, Router } from '@angular/router';
import { Directive, ViewChild } from '@angular/core';
import { MatSort, SortDirection } from '@angular/material/sort';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { MatTableDataSource } from '@angular/material/table';
import { IService } from '../service/iservice';

@Directive({})
export abstract class SortableComponent<T> {
  data: MatTableDataSource<T> = new MatTableDataSource<T>([]);
  isLoading = false;
  totalItems = 0;
  abstract itemsPerPage: number;
  page!: number;
  predicate!: string;
  direction!: SortDirection;
  abstract defaultSortColumn: string;
  abstract defaultSortDirection: SortDirection;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  protected constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected service: IService<T>) {}

  getData(event: PageEvent) {
    this.page = event.pageIndex;
    this.itemsPerPage = event.pageSize;
    this.loadAll();
  }

  transition(): void {
    this.router.navigate(['./'], {
      relativeTo: this.activatedRoute.parent,
      queryParams: {
        page: this.page,
        sort: `${this.sort ? this.sort.active : this.predicate},${this.sort ? this.sort.direction : this.direction}`,
      },
      queryParamsHandling: 'merge',
    });
  }

  handleNavigation(): void {
    combineLatest([this.activatedRoute.data, this.activatedRoute.queryParamMap]).subscribe(([_, params]) => {
      const page = params.get('page');
      this.page = Number(page ?? 0);
      const sort = (params.get(SORT) ?? `${this.defaultSortColumn},${this.defaultSortDirection}`).split(',');
      this.predicate = sort[0];
      this.direction = sort[1] === 'asc' ? 'asc' : sort[1] === 'desc' ? sort[1] : '';
      if (params.keys.length === 0) {
        this.transition();
        return;
      }
      this.loadAll();
    });
  }

  sortData(): string[] {
    const result = [`${this.sort ? this.sort.active : this.predicate},${this.sort ? this.sort.direction : this.direction}`];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  applySort() {
    this.transition();
  }

  loadAll(): void {
    this.isLoading = true;
    this.query().subscribe({
      next: (res: HttpResponse<T[]>) => {
        this.isLoading = false;
        this.onSuccess(res.body ? res.body : [], res.headers);
      },
      error: () => (this.isLoading = false),
    });
    this.transition();
  }

  private onSuccess(data: T[], headers: HttpHeaders): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.paginator.length = this.totalItems;
    this.paginator.pageIndex = this.page;
    this.data = new MatTableDataSource<T>(data);
    this.data.sort = this.sort;
  }

  protected query(): Observable<HttpResponse<T[]>> {
    return this.service.query({
      page: this.page,
      size: this.itemsPerPage,
      sort: this.sortData(),
    });
  }
}
