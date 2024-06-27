import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "environments/environment";
import { Observable } from "rxjs";
import { Product } from "../models/product.model";

@Injectable({
  providedIn: "root",
})
export class ProductApiService {
  constructor(private http: HttpClient) {}

  getProductsApi(): Observable<Product[]> {
    return this.http.get<Product[]>(environment.apiUrl + "products/");
  }

  createProductApi(product: any): Observable<Product> {
    return this.http.post<Product>(environment.apiUrl + "products/", product);
  }

  deleteProductApi(id: number): Observable<Product> {
    return this.http.delete<Product>(environment.apiUrl + "products/" + id);
  }
}
