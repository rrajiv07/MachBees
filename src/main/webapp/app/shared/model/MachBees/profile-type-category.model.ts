export interface IProfileTypeCategory {
  id?: number;
  profileCode?: string;
  profileName?: string;
  profileDescription?: string;
}

export class ProfileTypeCategory implements IProfileTypeCategory {
  constructor(public id?: number, public profileCode?: string, public profileName?: string, public profileDescription?: string) {}
}
