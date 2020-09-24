import { IUserMaster } from 'app/shared/model/user-master.model';

export interface IUserPersonalDetails {
  id?: number;
  name?: string;
  surname?: string;
  address?: string;
  country?: string;
  mobile?: string;
  linkedin?: string;
  twitter?: string;
  skype?: string;
  selfPresentation?: string;
  virtualcv?: string;
  user?: IUserMaster;
}

export class UserPersonalDetails implements IUserPersonalDetails {
  constructor(
    public id?: number,
    public name?: string,
    public surname?: string,
    public address?: string,
    public country?: string,
    public mobile?: string,
    public linkedin?: string,
    public twitter?: string,
    public skype?: string,
    public selfPresentation?: string,
    public virtualcv?: string,
    public user?: IUserMaster
  ) {}
}
