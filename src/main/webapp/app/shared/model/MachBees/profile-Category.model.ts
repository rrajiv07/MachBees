export interface IProfileCategory {
  id?: number;
  categoryCode?: string;
  categoryName?: string;
  categoryDescription?: string;
}

export class ProfileCategory implements IProfileCategory {
  constructor(public id?: number, public categoryCode?: string, public categoryName?: string, public categoryDescription?: string) {}
}
