export interface IProfileMaster {
  id?: number;
  profileCode?: string;
  profileName?: string;
  profileDescription?: string;
}

export class ProfileMaster implements IProfileMaster {
  constructor(public id?: number, public profileCode?: string, public profileName?: string, public profileDescription?: string) {}
}
