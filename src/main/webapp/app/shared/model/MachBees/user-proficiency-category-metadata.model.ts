export interface IUserProficiencyCategoryMetadata {
  id?: number;
  categoryCode?: string;
  categoryName?: string;
  categoryDescription?: string;
}

export class UserProficiencyCategoryMetadata implements IUserProficiencyCategoryMetadata {
  constructor(public id?: number, public categoryCode?: string, public categoryName?: string, public categoryDescription?: string) {}
}
