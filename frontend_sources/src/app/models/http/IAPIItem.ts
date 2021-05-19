import { IItem } from "src/app/models";

export interface IAPIItem extends IItem {
  _links: {
    self: {
      href: string
    }
  };
}
