export interface ICategory {
  id: number;
  name: string;
  iconName?: string;
}

export class Category implements ICategory {
  constructor(public id: number, public name: string, public iconName?: string) {}
}
