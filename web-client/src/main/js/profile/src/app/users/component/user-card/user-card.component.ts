import {Component, Input, OnInit} from '@angular/core';
import {UserTo} from "../../model/user-to";
import {Router} from "@angular/router";
import {AppStateService} from "../../../commons/service/app-state.service";

@Component({
    selector: 'user-card',
    templateUrl: './user-card.component.html',
    styleUrls: ['./user-card.component.css']
})
export class UserCardComponent implements OnInit {

    @Input() userTo!: UserTo;

    constructor(private router: Router,
                private appStateService: AppStateService) {
    }

    ngOnInit(): void {
    }

    public openUsersPage(): void {
        this.appStateService.setSelectedUser(this.userTo);
        this.router.navigate(['/' + this.userTo.userName + '/cv']);
    }
}
