import { Injectable } from "@angular/core";
import { Product } from "app/product/utils/models/product.model";
import { Observable, Subscription } from "rxjs";
import { ProductApiService } from "./product-api.service";
import { map } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class ProductService {
  constructor(private apiService: ProductApiService) {}

  getProducts(): Observable<Product[]> {
    return this.apiService
      .getProductsApi()
      .pipe(map((products) => products.map((product) => product)));
  }

  createProduct(product: Product): Subscription {
    return this.apiService.createProductApi(product).subscribe();
  }

  deleteProduct(id: number): Subscription {
    return this.apiService.deleteProductApi(id).subscribe();
  }
}
