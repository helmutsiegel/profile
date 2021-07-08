export class ExperienceVO {
  constructor(public company: string,
              public startDate: string,
              public endDate: string,
              public position: string,
              public id? : number) {
  }
}
