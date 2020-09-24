import { IProjectFeatureMaster } from 'app/shared/model/project-feature-master.model';
import { IProjectHdr } from 'app/shared/model/project-hdr.model';

export interface IProjectFeatureDtl {
  id?: number;
  feature?: IProjectFeatureMaster;
  project?: IProjectHdr;
}

export class ProjectFeatureDtl implements IProjectFeatureDtl {
  constructor(public id?: number, public feature?: IProjectFeatureMaster, public project?: IProjectHdr) {}
}
