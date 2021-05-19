import { ICategory } from "src/app/models";

export interface IAPICategory extends ICategory {
  _links: {
    self: {
      href: string
    }
  };
}
