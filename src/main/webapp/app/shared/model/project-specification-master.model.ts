export interface IProjectSpecificationMaster {
  id?: number;
  specificationCode?: string;
  specificationName?: string;
  specificationDescription?: string;
}

export class ProjectSpecificationMaster implements IProjectSpecificationMaster {
  constructor(
    public id?: number,
    public specificationCode?: string,
    public specificationName?: string,
    public specificationDescription?: string
  ) {}
}
