import { UserStorageService } from "./user-storage.service";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import jwtDecode from "jwt-decode";

import { AuthService } from "./api/auth.service";
import { NotificationsService } from "./notification.service";

import { IToken, IUserLight } from "../models";
import { TOKEN_REFRESH_INTERVAL } from "src/environments/api.pops";

@Injectable({
  providedIn: "root",
})
export class SessionService {
  userData?: IUserLight = {} as IUserLight;

  _isAuthorized = false;
  _isAdmin = false;
  adminAuthorities = "ADMIN";
  refreshInterval: any;

  get isAuthroized(): boolean {
    return this._isAuthorized;
  }

  get isAdmin(): boolean {
    return this._isAdmin;
  }

  constructor(
    private authService: AuthService,
    private router: Router,
    private userStorageService: UserStorageService,
    private notificationsService: NotificationsService
  ) {}

  async init(): Promise<void> {
    const userFromStorage = JSON.parse(localStorage.getItem("userData"));

    if (!userFromStorage) {
      return;
    }

    const { isAdmin, userData } = userFromStorage;

    try {
      await this.authService.refresh();

      this.refreshInterval = setInterval(() => {
        this.authService.refresh();
      }, TOKEN_REFRESH_INTERVAL);

      this.userData = userData;
      this._isAuthorized = true;
      this._isAdmin = isAdmin;
      this.userStorageService.init();
    } catch (e) {
      this.logOut();
      return;
    }
  }

  async logIn(login: string, password: string): Promise<void> {
    const response = await this.authService.login(login, password);
    const userData = response.user;
    const isAdmin = response.user.authorities.indexOf(this.adminAuthorities) >= 0;

    localStorage.setItem("userData", JSON.stringify({ isAdmin, userData }));

    this.refreshInterval = setInterval(() => {
      this.authService.refresh();
    }, TOKEN_REFRESH_INTERVAL);

    this.userData = userData;
    this._isAuthorized = true;
    this._isAdmin = isAdmin;
    this.userStorageService.init();
  }

  logOut(): void {
    this.authService.logout();
    clearInterval(this.refreshInterval);
    localStorage.removeItem("userData");

    if (this.isAuthroized) {
      this.notificationsService.openSnackBar("Logged out successfuly", 3000, "", {
        horizontal: "center",
        vertical: "bottom",
      });
    }

    this._isAuthorized = false;
    this._isAdmin = false;

    this.userStorageService.destroy();

    this.router.navigate(["/"]);
  }

  updateUserData(userData: IUserLight) {
    this.userData = userData;
    const isAdmin = userData.authorities.indexOf(this.adminAuthorities) >= 0;
    localStorage.setItem("userData", JSON.stringify({ isAdmin, userData }));
  }
}
