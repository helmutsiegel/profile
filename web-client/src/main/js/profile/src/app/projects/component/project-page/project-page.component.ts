import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProjectVO} from "../../model/project-v-o";
import {SectionVO} from "../../model/section-v-o";
import {AuthService} from "../../../service/auth.service";
import {Subscription} from "rxjs";
import {UserTO} from "../../../shared/model/to/user-t-o";
import {SimpleTextCardComponent} from "../../../shared/component/simple-text-card/simple-text-card.component";

@Component({
  selector: 'project-page',
  templateUrl: './project-page.component.html',
  styleUrls: ['./project-page.component.css']
})
export class ProjectPageComponent implements OnInit, OnDestroy {

  projectVO!: ProjectVO;
  currentSection!: SectionVO;
  private currentUser!: UserTO;
  private subscription!: Subscription;
  seeDescription: string = 'See description +';
  closeDescription: string = 'Close description -';
  descriptionButtonLabel: string = this.seeDescription;

  @ViewChild('textCard') simpleTextCard!: SimpleTextCardComponent;

  constructor(private activatedRoute: ActivatedRoute,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    this.subscription = this.authService.getCurrentUser().subscribe(userTO => {
      this.currentUser = userTO;
    });
    this.activatedRoute.data.forEach(data => {
      this.projectVO = data['projectVO'];
      this.currentSection = this.projectVO.chapters[0].sections[0];
    });
  }

  public loggedInUserIsOnTheyPage(): boolean {
    return this.currentUser && (this.currentUser.email === this.activatedRoute.snapshot.params['email']);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  public clickDescription(): void {
    this.descriptionButtonLabel = this.descriptionButtonLabel === this.seeDescription ? this.closeDescription : this.seeDescription;
  }

  public navigateToSection(sectionVO: SectionVO): void {
    if(this.simpleTextCard.isEdited()){
      if(window.confirm('save?')){

      }
    }
    this.simpleTextCard.editMode = false;
    this.currentSection = sectionVO;
  }
}
