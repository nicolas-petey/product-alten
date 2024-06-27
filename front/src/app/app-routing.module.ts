import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ProductsAdminComponent } from "./product/ui/products-admin/products-admin.component";
import { ProductsComponent } from "./product/ui/products/products.component";

const routes: Routes = [
  {
    path: "products",
    component: ProductsComponent,
  },
  {
    path: "admin",
    children: [
      {
        path: "products",
        component: ProductsAdminComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: "legacy" })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
