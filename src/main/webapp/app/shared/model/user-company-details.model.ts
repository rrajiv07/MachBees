import { IUserMaster } from 'app/shared/model/user-master.model';

export interface IUserCompanyDetails {
  id?: number;
  name?: string;
  website?: string;
  description?: string;
  address?: string;
  vat?: string;
  mobile?: string;
  linkedin?: string;
  twitter?: string;
  skype?: string;
  user?: IUserMaster;
}

export class UserCompanyDetails implements IUserCompanyDetails {
  constructor(
    public id?: number,
    public name?: string,
    public website?: string,
    public description?: string,
    public address?: string,
    public vat?: string,
    public mobile?: string,
    public linkedin?: string,
    public twitter?: string,
    public skype?: string,
    public user?: IUserMaster
  ) {}
}
