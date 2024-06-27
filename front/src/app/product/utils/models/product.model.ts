export interface Product {
  id?: number;
  code: string;
  name: string;
  description: string;
  image?: string;
  price: number;
  quantity: number;
  category: string;
  rating?: number;
  inventoryStatus: string;
}
