import { ICategoryMetadata } from 'app/shared/model/category-metadata.model';
import { IUserMaster } from 'app/shared/model/user-master.model';

export interface IUserLanguageDetails {
  id?: number;
  language?: ICategoryMetadata;
  proficiency?: ICategoryMetadata;
  user?: IUserMaster;
}

export class UserLanguageDetails implements IUserLanguageDetails {
  constructor(public id?: number, public language?: ICategoryMetadata, public proficiency?: ICategoryMetadata, public user?: IUserMaster) {}
}
