import { ICategory } from "./ICategory";
import { ISubcategory } from "./ISubcategory";
import { IMedia } from "./IMedia";

export interface IItem {
  id: number;
  title: string;
  description: string;
  classification: {
    category: ICategory;
    subcategory: ISubcategory;
  };
  quality: {
    value: string;
  };
  photos: IMedia[];
  videos?: IMedia[];
}
