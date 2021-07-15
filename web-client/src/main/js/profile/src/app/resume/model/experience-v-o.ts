export class ExperienceVO {

  constructor(public company: string,
              public startDate: string,
              public endDate: string | undefined,
              public position: string) {
  }
}
