export interface IProjectFeatureMaster {
  id?: number;
  featureCode?: string;
  featureName?: string;
  featureDescription?: string;
}

export class ProjectFeatureMaster implements IProjectFeatureMaster {
  constructor(public id?: number, public featureCode?: string, public featureName?: string, public featureDescription?: string) {}
}
