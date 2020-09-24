export interface IUserSkillCategoryMetadata {
  id?: number;
  categoryCode?: string;
  categoryName?: string;
  categoryDescription?: string;
}

export class UserSkillCategoryMetadata implements IUserSkillCategoryMetadata {
  constructor(public id?: number, public categoryCode?: string, public categoryName?: string, public categoryDescription?: string) {}
}
