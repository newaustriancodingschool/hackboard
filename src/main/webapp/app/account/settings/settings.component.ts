import { Component, OnInit } from '@angular/core';
import { JhiLanguageService } from 'ng-jhipster';
import { Principal, AccountService, JhiLanguageHelper } from '../../shared';

@Component({
  templateUrl: './settings.component.html'
})
export class SettingsComponent implements OnInit {
  error: string;
  success: string;
  settingsAccount: any;
  languages: any[];
  value: any;
  tags: Array<string> = [];
  selectedTags: Array<string> = ['Java'];

  constructor(
    private account: AccountService,
    private principal: Principal,
    private languageService: JhiLanguageService,
    private languageHelper: JhiLanguageHelper
  ) {}

  ngOnInit() {
    this.principal.identity().then(account => {
      this.settingsAccount = this.copyAccount(account);
      //  this.tagservice.showAllTags().subscribe(tags => (this.tags = tags));
      this.languageHelper.getAll().then(languages => (this.languages = languages));
    });
  }

  save() {
    this.account.save(this.settingsAccount).subscribe(
      () => {
        this.error = null;
        this.success = 'OK';
        this.principal.identity(true).then(account => {
          this.settingsAccount = this.copyAccount(account);
        });
        this.languageService.getCurrent().then(current => {
          if (this.settingsAccount.langKey !== current) {
            this.languageService.changeLanguage(this.settingsAccount.langKey);
          }
        });
      },
      () => {
        this.success = null;
        this.error = 'ERROR';
      }
    );
  }
  copyAccount(account) {
    return {
      activated: account.activated,
      email: account.email,
      github: account.github,
      firstName: account.firstName,
      langKey: account.langKey,
      lastName: account.lastName,
      description: account.description,
      login: account.login,
      tags: account.tags,
      imageUrl: account.imageUrl
    };
  }
  selected(value: any): void {
    console.log('Selected value is: ', value);
  }

  removed(value: any): void {
    console.log('Removed value is: ', value);
  }

  refreshValue(value: any): void {
    this.value = value;
  }

  rolesToString(value: Array<any> = []): string {
    return value
      .map((item: any) => {
        return item.text;
      })
      .join(',');
  }
}
