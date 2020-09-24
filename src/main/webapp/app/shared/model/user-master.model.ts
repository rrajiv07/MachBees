import { Moment } from 'moment';
import { IUserLanguageDetails } from 'app/shared/model/user-language-details.model';
import { ICategoryMetadata } from 'app/shared/model/category-metadata.model';
import { IProfileMaster } from 'app/shared/model/profile-master.model';

export interface IUserMaster {
  id?: number;
  emailId?: string;
  password?: string;
  updatedBy?: string;
  updatedDate?: Moment;
  userlanguagedetails?: IUserLanguageDetails[];
  status?: ICategoryMetadata;
  profile?: IProfileMaster;
  profileCategory?: ICategoryMetadata;
  paymentSubscription?: ICategoryMetadata;
}

export class UserMaster implements IUserMaster {
  constructor(
    public id?: number,
    public emailId?: string,
    public password?: string,
    public updatedBy?: string,
    public updatedDate?: Moment,
    public userlanguagedetails?: IUserLanguageDetails[],
    public status?: ICategoryMetadata,
    public profile?: IProfileMaster,
    public profileCategory?: ICategoryMetadata,
    public paymentSubscription?: ICategoryMetadata
  ) {}
}
