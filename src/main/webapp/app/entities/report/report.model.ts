export interface IReportItem {
  itemName: string;
  quantity: number;
  unitPrice: number;
}

export interface IReport {
  items: IReportItem[];
  total: number;
}

export class Report implements IReport {
  constructor(public items: IReportItem[], public total: number) {}
}

export class ReportItem implements IReportItem {
  constructor(public itemName: string, public quantity: number, public unitPrice: number) {}
}
