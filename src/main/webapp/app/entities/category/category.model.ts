export interface ICategory {
  name: string;
  id?: number;
  iconName?: string;
}

export class Category implements ICategory {
  constructor(public name: string, public id?: number, public iconName?: string) {}
}
