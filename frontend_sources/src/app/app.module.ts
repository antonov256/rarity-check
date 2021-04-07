import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppRoutingModule } from "./app-routing.module";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { MatToolbarModule } from "@angular/material/toolbar";
import { MatInputModule } from "@angular/material/input";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { MatDividerModule } from "@angular/material/divider";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatCardModule } from "@angular/material/card";
import { MatListModule } from "@angular/material/list";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatMenuModule } from "@angular/material/menu";
import { MatSidenavModule } from "@angular/material/sidenav";
import { MatSelectModule } from "@angular/material/select";
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatDialogModule } from '@angular/material/dialog';

import { AuthComponent } from "./views/auth/auth.component";
import { SignInComponent } from "./views/auth/sign-in/sign-in.component";
import { RegisterComponent } from "./views/auth/register/register.component";
import { ProfileComponent } from "./views/profile/profile.component";
import { PortfolioComponent } from "./views/portfolio/portfolio.component";
import { HomeComponent } from "./views/home/home.component";
import { ItemPageComponent } from './views/item-page/item-page.component';
import { CategoryComponent } from './views/category/category.component';
import { CategoryCreateComponent } from './views/category/create/create.component';
import { AdminComponent } from './views/admin/admin.component';
import { CategoryEditComponent } from './views/category/edit/edit.component';
import { ItemComponent } from './views/item/item.component';
import { ItemCreateComponent } from './views/item/create/create.component';

import { AppComponent } from "./app.component";
import { HeaderComponent } from "./components/header/header.component";
import { FooterComponent } from "./components/footer/footer.component";
import { DefaultCardComponent } from './components/cards/default/default.component';
import { PagedDefaultCardsComponent } from './components/paged-cards/default/paged-default-cards.component';
import { WishComponent } from './components/cards/wish/wish.component';
import { OwnComponent } from './components/cards/own/own.component';
import { PagedOwnCardsComponent } from './components/paged-cards/own/paged-own-cards.component';
import { PagedWishCardsComponent } from './components/paged-cards/wish/paged-wish-cards.component';
import { CategoryTreeComponent } from './components/category-tree/category-tree.component';
import { CategoryFormComponent } from './components/forms/category-form/category-form.component';
import { ItemFormComponent } from './components/forms/item-form/item-form.component';
import { ImageBoxComponent } from './components/image-box/image-box.component';
import { ItemEditComponent } from './views/item/edit/edit.component';

import { NotificationsService } from './services/notification.service';
import { HttpRequestsService } from './services/http-requests.service';
import { CategoriesService } from './services/categories.service';
import { AuthService } from './services/auth.service';
import { ApiService } from './services/api.service';
import { CustomInterceptorService } from "./services/custom-interceptor.service";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AuthComponent,
    SignInComponent,
    RegisterComponent,
    ProfileComponent,
    FooterComponent,
    PortfolioComponent,
    HomeComponent,
    ItemPageComponent,
    DefaultCardComponent,
    PagedDefaultCardsComponent,
    WishComponent,
    OwnComponent,
    PagedOwnCardsComponent,
    PagedWishCardsComponent,
    AdminComponent,
    CategoryTreeComponent,
    CategoryCreateComponent,
    CategoryFormComponent,
    CategoryComponent,
    CategoryEditComponent,
    ItemComponent,
    ItemCreateComponent,
    ItemFormComponent,
    ImageBoxComponent,
    ItemEditComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatCardModule,
    MatInputModule,
    MatDividerModule,
    MatListModule,
    MatPaginatorModule,
    MatMenuModule,
    MatSidenavModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatExpansionModule,
    MatDialogModule
  ],
  providers: [
    // ApiService,
    // AuthService,
    // CategoriesService,
    // HttpRequestsService,
    // NotificationsService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CustomInterceptorService,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
