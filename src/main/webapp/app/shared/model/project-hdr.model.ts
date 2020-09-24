import { Moment } from 'moment';
import { IProjectAttachmentDtl } from 'app/shared/model/project-attachment-dtl.model';
import { IProjectFeatureDtl } from 'app/shared/model/project-feature-dtl.model';
import { IProjectRoleDtl } from 'app/shared/model/project-role-dtl.model';
import { IProjectSkillDtl } from 'app/shared/model/project-skill-dtl.model';
import { IProjectTypeMaster } from 'app/shared/model/project-type-master.model';
import { IProjectSpecificationMaster } from 'app/shared/model/project-specification-master.model';
import { ICategoryMetadata } from 'app/shared/model/category-metadata.model';
import { IProjectCategoryMaster } from 'app/shared/model/project-category-master.model';

export interface IProjectHdr {
  id?: number;
  overview?: string;
  projectDescription?: string;
  endDate?: Moment;
  createdBy?: string;
  createdDate?: Moment;
  modifiedBy?: string;
  modifiedDate?: Moment;
  projectattachmentdetails?: IProjectAttachmentDtl[];
  projectfeaturedetails?: IProjectFeatureDtl[];
  projectroledetails?: IProjectRoleDtl[];
  projectskilldetails?: IProjectSkillDtl[];
  projectType?: IProjectTypeMaster;
  projectSpecification?: IProjectSpecificationMaster;
  applicationType?: ICategoryMetadata;
  model?: ICategoryMetadata;
  projectCategory?: IProjectCategoryMaster;
  visibility?: ICategoryMetadata;
  preferContactedBy?: ICategoryMetadata;
  status?: ICategoryMetadata;
}

export class ProjectHdr implements IProjectHdr {
  constructor(
    public id?: number,
    public overview?: string,
    public projectDescription?: string,
    public endDate?: Moment,
    public createdBy?: string,
    public createdDate?: Moment,
    public modifiedBy?: string,
    public modifiedDate?: Moment,
    public projectattachmentdetails?: IProjectAttachmentDtl[],
    public projectfeaturedetails?: IProjectFeatureDtl[],
    public projectroledetails?: IProjectRoleDtl[],
    public projectskilldetails?: IProjectSkillDtl[],
    public projectType?: IProjectTypeMaster,
    public projectSpecification?: IProjectSpecificationMaster,
    public applicationType?: ICategoryMetadata,
    public model?: ICategoryMetadata,
    public projectCategory?: IProjectCategoryMaster,
    public visibility?: ICategoryMetadata,
    public preferContactedBy?: ICategoryMetadata,
    public status?: ICategoryMetadata
  ) {}
}
