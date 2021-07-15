export class ExperienceVO {
  constructor(public company: string,
              public position: string,
              public startDate: string,
              public endDate?: string,
              public id? : number) {
  }
}
