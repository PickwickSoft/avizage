export interface IStorage {
  name: string;
  description: string;
  id: number;
}

export class Storage implements IStorage {
  constructor(public name: string, public description: string, public id: number) {}
}
