import { IProjectHdr } from 'app/shared/model/project-hdr.model';

export interface IProjectAttachmentDtl {
  id?: number;
  fileId?: string;
  fileName?: string;
  fileType?: string;
  project?: IProjectHdr;
}

export class ProjectAttachmentDtl implements IProjectAttachmentDtl {
  constructor(
    public id?: number,
    public fileId?: string,
    public fileName?: string,
    public fileType?: string,
    public project?: IProjectHdr
  ) {}
}
