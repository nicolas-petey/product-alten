import { Component, OnInit } from "@angular/core";
import { Product } from "app/product/utils/models/product.model";
import { ProductService } from "app/product/utils/product/product.service";
import { PrimeNGConfig, SelectItem } from "primeng/api";
@Component({
  selector: "app-products",
  templateUrl: "./products.component.html",
  styleUrls: ["./products.component.scss"],
})
export class ProductsComponent implements OnInit {
  products: Product[];

  sortOptions: SelectItem[];

  sortOrder: number;

  sortField: string;

  sortKey: string;

  searchText: string = "";

  constructor(
    private productService: ProductService,
    private primengConfig: PrimeNGConfig
  ) {}

  ngOnInit(): void {
    this.productService.getProducts().subscribe((products) => {
      this.products = products;
    });

    this.sortOptions = [
      { label: "Prix du plus grand au plus petit", value: "!price" },
      { label: "Prix du plus petit au plus plus grand", value: "price" },
      { label: "Aucun", value: "id" },
    ];

    this.primengConfig.ripple = true;
  }

  onSortChange(event) {
    let value = event.value;

    if (value.indexOf("!") === 0) {
      this.sortOrder = -1;
      this.sortField = value.substring(1, value.length);
    } else {
      this.sortOrder = 1;
      this.sortField = value;
    }
  }

  filterProducts() {
    if (this.searchText) {
      return this.products.filter((product) =>
        product.name.toLowerCase().includes(this.searchText.toLowerCase())
      );
    } else {
      return this.products;
    }
  }
}
