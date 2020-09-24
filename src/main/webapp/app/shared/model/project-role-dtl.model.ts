import { IProjectRoleMaster } from 'app/shared/model/project-role-master.model';
import { IProjectHdr } from 'app/shared/model/project-hdr.model';

export interface IProjectRoleDtl {
  id?: number;
  role?: IProjectRoleMaster;
  project?: IProjectHdr;
}

export class ProjectRoleDtl implements IProjectRoleDtl {
  constructor(public id?: number, public role?: IProjectRoleMaster, public project?: IProjectHdr) {}
}
