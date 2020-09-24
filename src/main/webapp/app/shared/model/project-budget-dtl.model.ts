import { IProjectHdr } from 'app/shared/model/project-hdr.model';

export interface IProjectBudgetDtl {
  id?: number;
  budget?: number;
  ipOwnership?: number;
  project?: IProjectHdr;
}

export class ProjectBudgetDtl implements IProjectBudgetDtl {
  constructor(public id?: number, public budget?: number, public ipOwnership?: number, public project?: IProjectHdr) {}
}
