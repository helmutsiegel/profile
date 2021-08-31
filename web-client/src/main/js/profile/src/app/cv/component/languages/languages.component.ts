import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {LanguageVO} from "../../model/language-v-o";

@Component({
    selector: 'languages',
    templateUrl: './languages.component.html',
    styleUrls: ['./languages.component.css']
})
export class LanguagesComponent implements OnInit {

    @Input() languages!: LanguageVO[];
    @Input() editable!: boolean;
    @Output() onSave: EventEmitter<LanguageVO[]> = new EventEmitter<LanguageVO[]>();
    editMode: boolean = false;
    languagesToEdit: LanguageVO[] = [];
    levels: string[] = ['BEGINNER', 'ELEMENTARY', 'INTERMEDIATE', 'ADVANCED', 'PROFICIENT', 'NATIVE'];

    constructor() {
    }

    ngOnInit(): void {

    }

    public editLanguages(): void {
        this.languagesToEdit = [];
        this.languages.forEach(val => this.languagesToEdit.push(Object.assign({}, val)));
        this.editMode = !this.editMode;
    }

    public addLanguage() {
        this.languagesToEdit.push(new LanguageVO('', ''));
    }

    public save() {
        this.editMode = false;
        this.onSave.emit(this.languagesToEdit);
    }

    public reset() {
        this.editMode = false;
        this.languagesToEdit = [...this.languages];
    }
}
