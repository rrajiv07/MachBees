import { ISkillMaster } from 'app/shared/model/skill-master.model';
import { IProjectHdr } from 'app/shared/model/project-hdr.model';

export interface IProjectSkillDtl {
  id?: number;
  skill?: ISkillMaster;
  project?: IProjectHdr;
}

export class ProjectSkillDtl implements IProjectSkillDtl {
  constructor(public id?: number, public skill?: ISkillMaster, public project?: IProjectHdr) {}
}
