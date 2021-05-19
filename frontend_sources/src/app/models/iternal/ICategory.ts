import { ISubcategory } from "..";

export interface ICategory {
  id: number;
  name: string;
  subcategories?: ISubcategory[];
}
