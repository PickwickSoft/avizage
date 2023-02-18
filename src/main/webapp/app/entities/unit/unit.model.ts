export interface IUnit {
  id?: number;
  code?: string;
  name?: string;
}

export class Unit implements IUnit {
  constructor(public id?: number, public code?: string, public name?: string) {}
}
