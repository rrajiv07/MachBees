export interface ICategoryMetadata {
  id?: number;
  categoryCode?: string;
  categoryName?: string;
  categoryDescription?: string;
  sequenceNumber?: number;
}

export class CategoryMetadata implements ICategoryMetadata {
  constructor(
    public id?: number,
    public categoryCode?: string,
    public categoryName?: string,
    public categoryDescription?: string,
    public sequenceNumber?: number
  ) {}
}
