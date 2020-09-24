export interface IProjectTypeMaster {
  id?: number;
  projectTypeCode?: string;
  projectTypeName?: string;
  projectTypeDescription?: string;
}

export class ProjectTypeMaster implements IProjectTypeMaster {
  constructor(
    public id?: number,
    public projectTypeCode?: string,
    public projectTypeName?: string,
    public projectTypeDescription?: string
  ) {}
}
