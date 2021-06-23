import {Component, OnInit, ViewChild} from '@angular/core';
import {SimpleModalComponent} from "../../../shared/component/simple-modal/simple-modal.component";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Size} from "../../../shared/model/enum/size";
import {PostService} from "../../service/post.service";
import {ActivatedRoute} from "@angular/router";
import {PostTO} from "../../../shared/model/to/post-t-o";
import {UserTO} from "../../../shared/model/to/user-t-o";
import {AuthService} from "../../../service/auth.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

  @ViewChild('newPostModal') newPostModal!: SimpleModalComponent;

  newPostForm!: FormGroup;
  private postTitleFC!: FormControl;
  private postContentFC!: FormControl;
  private currentUser!: UserTO;

  private newPostTitle!: string;
  private newPostContent!: string;
  private subscription!: Subscription;
  posts!: PostTO[];

  constructor(private postService: PostService,
              private activatedRoute: ActivatedRoute,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    this.loadPosts();
    this.subscription = this.authService.getCurrentUser().subscribe(userTO => {
      this.currentUser = userTO;
    });
    this.postTitleFC = new FormControl(this.newPostTitle, Validators.required);
    this.postContentFC = new FormControl(this.newPostContent, Validators.required);
    this.newPostForm = new FormGroup({
      postTitle: this.postTitleFC,
      postContent: this.postContentFC
    })
  }

  private loadPosts(): void {
    const emailFromUrl = this.activatedRoute.snapshot.params['email'];
    this.postService.getByEmail(emailFromUrl).subscribe(posts => {
      this.posts = posts.sort((a, b) => b.dateCreated.localeCompare(a.dateCreated));
    });
  }

  public newPost(formValues: any): void {
    this.postService.createPost({
      title: formValues.postTitle,
      content: formValues.postContent
    } as PostTO).subscribe(_ => {
      this.loadPosts()
    });
    this.newPostModal.close();
  }

  public get size(): typeof Size {
    return Size;
  }

  public loggedInUserIsOnTheyPage(): boolean {
    return this.currentUser && (this.currentUser.email === this.activatedRoute.snapshot.params['email']);
  }

}
