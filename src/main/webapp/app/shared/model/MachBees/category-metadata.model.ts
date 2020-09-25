export interface ICategoryMetadata {
  id?: number;
  categoryCode?: string;
  categoryName?: string;
  categoryDescription?: string;
}

export class CategoryMetadata implements ICategoryMetadata {
  constructor(public id?: number, public categoryCode?: string, public categoryName?: string, public categoryDescription?: string) {}
}
