import { IAPIParams } from "src/app/models";

export function getApiParamsString(params: IAPIParams): string {
  const outputParams = [];
  const searchParams = [];

  if (params.title) {
    searchParams.push("title~" + params.title);
  }

  if (params.categoryId?.toString()) {
    searchParams.push("classification.category.id:" + params.categoryId);
  }

  if (params.subcategoryId?.toString()) {
    searchParams.push("classification.subcategory.id:" + params.subcategoryId);
  }

  if (params.pageNumber?.toString()) {
    outputParams.push("page=" + params.pageNumber);
  }

  if (params.pageSize?.toString()) {
    outputParams.push("size=" + params.pageSize);
  }

  if (searchParams.length) {
    outputParams.push("search=" + searchParams.join(","));
  }

  return "?" + outputParams.join("&");
}
