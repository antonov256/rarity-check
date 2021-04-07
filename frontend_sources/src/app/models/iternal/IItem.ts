export interface IItem {
  id: number,
  title: string,
  description: string,
  category: '',
  subcategory: '',
  qualityValue: number,
  photos: string[],
  videos?: string[]
}
