import { BaseEntity, User } from './../../shared';

export class Project implements BaseEntity {
  constructor(
    public id?: number,
    public title?: string,
    public description?: string,
    public user?: number,
    public user_fk?: User
  ) {}
}
