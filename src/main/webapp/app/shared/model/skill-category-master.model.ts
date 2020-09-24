export interface ISkillCategoryMaster {
  id?: number;
  categoryCode?: string;
  categoryName?: string;
  categoryDescription?: string;
}

export class SkillCategoryMaster implements ISkillCategoryMaster {
  constructor(public id?: number, public categoryCode?: string, public categoryName?: string, public categoryDescription?: string) {}
}
