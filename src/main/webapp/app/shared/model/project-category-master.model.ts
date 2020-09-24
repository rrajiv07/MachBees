export interface IProjectCategoryMaster {
  id?: number;
  categoryCode?: string;
  categoryName?: string;
  categoryDescription?: string;
}

export class ProjectCategoryMaster implements IProjectCategoryMaster {
  constructor(public id?: number, public categoryCode?: string, public categoryName?: string, public categoryDescription?: string) {}
}
