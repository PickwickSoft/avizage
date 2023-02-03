export interface IReportItem {
  id: number;
  name: string;
  qty: number;
  totalPrice: number;
}

export interface IReport {
  items: IReportItem[];
  total: number;
}

export class Report implements IReport {
  constructor(public items: IReportItem[], public total: number) {}
}

export class ReportItem implements IReportItem {
  constructor(public id: number, public name: string, public qty: number, public totalPrice: number) {}
}
