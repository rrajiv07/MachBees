import { ISkillCategoryMaster } from 'app/shared/model/skill-category-master.model';

export interface ISkillMaster {
  id?: number;
  skillCode?: string;
  skillName?: string;
  skillDescription?: string;
  skillCategory?: ISkillCategoryMaster;
}

export class SkillMaster implements ISkillMaster {
  constructor(
    public id?: number,
    public skillCode?: string,
    public skillName?: string,
    public skillDescription?: string,
    public skillCategory?: ISkillCategoryMaster
  ) {}
}
