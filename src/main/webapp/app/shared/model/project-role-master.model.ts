export interface IProjectRoleMaster {
  id?: number;
  roleCode?: string;
  roleName?: string;
  roleDescription?: string;
}

export class ProjectRoleMaster implements IProjectRoleMaster {
  constructor(public id?: number, public roleCode?: string, public roleName?: string, public roleDescription?: string) {}
}
