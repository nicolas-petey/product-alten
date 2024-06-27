import { TestBed } from "@angular/core/testing";
import { Product } from "app/product/utils/models/product.model";
import { of } from "rxjs";
import { ProductApiService } from "./product-api.service";
import { ProductService } from "./product.service";

describe("ProductService", () => {
  let service: ProductService;
  let apiServiceSpy: jasmine.SpyObj<ProductApiService>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj("ProductApiService", [
      "getProducts",
      "createProduct",
      "deleteProduct",
    ]);
    TestBed.configureTestingModule({
      // Provide both the service-to-test and its (spy) dependency
      providers: [
        ProductService,
        { provide: ProductApiService, useValue: spy },
      ],
    });
    service = TestBed.inject(ProductService);
    apiServiceSpy = TestBed.inject(
      ProductApiService
    ) as jasmine.SpyObj<ProductApiService>;
  });

  it("should be created", () => {
    expect(service).toBeTruthy();
  });

  it("should call getProducts and return a list of products", (done: DoneFn) => {
    const expectedProducts: Product[] = [
      {
        id: 1,
        name: "Test Product 1",
        price: 100,
        code: "testst1",
        description: "test description",
        rating: 5,
        image: "test.jpg",
        category: "test category",
        quantity: 10,
        inventoryStatus: "INSTOCK",
      },
      {
        id: 2,
        name: "Test Product 2",
        price: 200,
        code: "testst2",
        description: "test description",
        rating: 3,
        image: "test.jpg",
        category: "test category",
        quantity: 50,
        inventoryStatus: "INSTOCK",
      },
    ];

    apiServiceSpy.getProductsApi.and.returnValue(of(expectedProducts));

    service.getProducts().subscribe({
      next: (products) => {
        expect(products.length).toBe(2);
        expect(products).toEqual(expectedProducts);
        done();
      },
      error: done.fail,
    });

    expect(apiServiceSpy.getProductsApi.calls.count()).toBe(1, "one call");
  });

  it("should call createProduct and add a product", (done: DoneFn) => {
    const newProduct: Product = {
      id: 3,
      name: "Test Product 3",
      price: 300,
      code: "fsdfsdf5",
      description: "",
      quantity: 0,
      category: "",
      inventoryStatus: "",
    };

    apiServiceSpy.createProductApi.and.returnValue(of(newProduct));

    service.createProduct(newProduct).add(() => {
      expect(apiServiceSpy.createProductApi.calls.count()).toBe(1, "one call");
      done();
    });
  });

  it("should call deleteProduct and remove the specified product", (done: DoneFn) => {
    const productId = 1;

    apiServiceSpy.deleteProductApi.and.returnValue(of({}));

    service.deleteProduct(productId).add(() => {
      expect(apiServiceSpy.deleteProductApi.calls.count()).toBe(1, "one call");
      done();
    });
  });
});
