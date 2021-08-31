import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  courses: Course[] = [{
    title: 'AWS Developer: Getting Started',
    author: 'Ryan Lewis',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/aws-developer-getting-started/table-of-contents',
    status: 'Finished'
  }, {
    title: 'Angular Fundamentals',
    author: 'Jim Cooper, Joe Eames',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/angular-fundamentals/table-of-contents',
    status: 'Finished'
  }, {
    title: 'Java Persistence API 2.2',
    author: 'Antonio Goncalves',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/java-persistence-api-21/table-of-contents',
    status: 'Finished'
  }, {
    title: 'Context and Dependency Injection (CDI 1.1)',
    author: 'Antonio Goncalves',
    platform: 'Pluralsight',
    link: 'https://app.pluralsight.com/library/courses/context-dependency-injection-1-1/table-of-contents',
    status: 'Finished'
  },
    {
      title: 'Getting Started Unit Testing with JUnit 5',
      author: 'Jim Weaver',
      platform: 'Pluralsight',
      link: 'https://app.pluralsight.com/library/courses/junit-5-unit-testing-getting-started/table-of-contents',
      status: 'Finished'
    },
    {
      title: 'TDD with JUnit 5',
      author: 'Catalin Tudose',
      platform: 'Pluralsight',
      link: 'https://app.pluralsight.com/library/courses/tdd-junit5/table-of-contents',
      status: 'In progress'
    }, {
      title: 'RESTFul Services in Java using Jersey',
      author: 'Bryan Hansen',
      platform: 'Pluralsight',
      link: 'https://app.pluralsight.com/library/courses/restful-services-java-using-jersey/table-of-contents',
      status: 'Planned'
    }, {
      title: 'Maven Fundamentals',
      author: 'Bryan Hansen',
      platform: 'Pluralsight',
      link: 'https://app.pluralsight.com/library/courses/maven-fundamentals/table-of-contents',
      status: 'Planned'
    }, {
      title: 'SOLID Software Design Principles in Java',
      author: 'Dan Geabunea',
      platform: 'Pluralsight',
      link: 'https://app.pluralsight.com/library/courses/solid-software-design-principles-java/table-of-contents',
      status: 'Planned'
    }, {
      title: 'Jenkins',
      author: '',
      platform: '',
      link: '',
      status: 'Planned'
    }];

  constructor() {
  }

  ngOnInit(): void {
  }

  getClassForStatus(course: Course) {
    return course.status === 'Finished' ? 'bg-success' : course.status === 'In progress' ? 'bg-warning' : 'bg-secondary';
  }
}

interface Course {
  title: string;
  author: string;
  platform: string;
  link: string;
  status: 'Finished' | 'In progress' | 'Planned';
}
