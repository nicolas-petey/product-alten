import { Component, OnInit } from "@angular/core";
import { Product } from "app/product/utils/models/product.model";
import { ProductService } from "app/product/utils/product/product.service";
import { ConfirmationService, MessageService } from "primeng/api";

@Component({
  selector: "app-products-admin",
  templateUrl: "./products-admin.component.html",
  styleUrls: ["./products-admin.component.scss"],
  providers: [MessageService, ConfirmationService],
  styles: [
    `
      :host ::ng-deep .p-dialog .product-image {
        width: 150px;
        margin: 0 auto 2rem auto;
        display: block;
      }
    `,
  ],
})
export class ProductsAdminComponent implements OnInit {
  productDialog: boolean;

  products: Product[];

  product: Product;

  selectedProducts: Product[];

  submitted: boolean;

  searchText: string = "";

  constructor(
    private productService: ProductService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {}

  ngOnInit(): void {
    this.productService.getProducts().subscribe((products) => {
      this.products = products;
    });
  }

  openNew(): void {
    this.product = {
      name: "",
      code: this.createCode() + "",
      image: "",
      description: "",
      price: 0,
      quantity: 0,
      category: "",
      rating: 0,
      inventoryStatus: "INSTOCK",
    };
    this.submitted = false;
    this.productDialog = true;
  }

  deleteSelectedProducts(): void {
    this.confirmationService.confirm({
      message: "Etes vous sur de supprimer les produits selectionnés ?",
      header: "Confirm",
      icon: "pi pi-exclamation-triangle",
      accept: () => {
        this.products = this.products.filter(
          (val) => !this.selectedProducts.includes(val)
        );
        this.selectedProducts.forEach((product) => {
          this.productService.deleteProduct(product.id);
        });
        this.messageService.add({
          severity: "success",
          summary: "Successful",
          detail: "Products Deleted",
          life: 3000,
        });
        this.selectedProducts = null;
      },
    });
  }

  editProduct(product: Product): void {
    this.product = { ...product };
    this.productDialog = true;
  }

  deleteProduct(product: Product): void {
    this.confirmationService.confirm({
      message: "Etes vous sur de supprimer ce produit : " + product.name + " ?",
      header: "Confirm",
      icon: "pi pi-exclamation-triangle",
      accept: () => {
        this.products = this.products.filter((val) => val.id !== product.id);
        this.productService.deleteProduct(product.id);
        this.messageService.add({
          severity: "success",
          summary: "Successful",
          detail: "Product Deleted",
          life: 3000,
        });
      },
    });
  }

  hideDialog(): void {
    this.productDialog = false;
    this.submitted = false;
  }

  saveProduct(): void {
    this.submitted = true;

    if (this.product.name.trim()) {
      this.productService.createProduct(this.product);

      this.messageService.add({
        severity: "success",
        summary: "Successful",
        detail: "Product Updated",
        life: 3000,
      });
      this.products.push({ ...this.product });

      this.productDialog = false;
      this.product = {
        name: "",
        code: "",
        image: "",
        description: "",
        price: 0,
        quantity: 0,
        category: "",
        rating: 0,
        inventoryStatus: "INSTOCK",
      };
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

  createCode(): string {
    let code = "";
    var chars =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    for (var i = 0; i < 9; i++) {
      code += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return code;
  }
}
