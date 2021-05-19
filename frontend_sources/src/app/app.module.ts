import { APP_INITIALIZER, NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { AppRoutingModule } from "./app-routing.module";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { SessionService } from "src/app/services/session.service";

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
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { MatExpansionModule } from "@angular/material/expansion";
import { MatDialogModule } from "@angular/material/dialog";

import { AuthComponent } from "./views/auth/auth.component";
import { SignInComponent } from "./views/auth/sign-in/sign-in.component";
import { RegisterComponent } from "./views/auth/register/register.component";
import { ProfileComponent } from "./views/profile/profile.component";
import { PortfolioComponent } from "./views/portfolio/portfolio.component";
import { HomeComponent } from "./views/home/home.component";
import { CategoryComponent } from "./views/category/category.component";
import { CategoryCreateComponent } from "./views/category/create/create.component";
import { CategoryPageComponent } from "./views/category/page/page.component";
import { CategoryEditComponent } from "./views/category/edit/edit.component";
import { AdminComponent } from "./views/admin/admin.component";
import { ItemComponent } from "./views/item/item.component";
import { ItemCreateComponent } from "./views/item/create/create.component";
import { ItemEditComponent } from "./views/item/edit/edit.component";
import { ItemPageComponent } from "./views/item/page/page.component";
import { AboutComponent } from "./views/about/about.component";
import { SubcategoryComponent } from "./views/subcategory/subcategory.component";
import { SubcategoryPageComponent } from "./views/subcategory/page/page.component";
import { SubcategoryCreateComponent } from "./views/subcategory/create/create.component";
import { SubcategoryEditComponent } from "./views/subcategory/edit/edit.component";

import { AppComponent } from "./app.component";
import { HeaderComponent } from "./components/header/header.component";
import { FooterComponent } from "./components/footer/footer.component";
import { DefaultCardComponent } from "./components/cards/default/default.component";
import { WishComponent } from "./components/cards/wish/wish.component";
import { OwnComponent } from "./components/cards/own/own.component";
import { CategoryTreeComponent } from "./components/category-tree/category-tree.component";
import { CategoryFormComponent } from "./components/forms/category-form/category-form.component";
import { ItemFormComponent } from "./components/forms/item-form/item-form.component";
import { ImageBoxComponent } from "./components/image-box/image-box.component";
import { LoaderScreenComponent } from "./components/loader-screen/loader-screen.component";
import { SubcategoryFormComponent } from "./components/forms/subcategory-form/subcategory-form.component";
import { ConfirmComponent } from "./components/dialogs/confirm/confirm.component";
import { PagedCardsComponent } from "./components/paged-cards/paged-cards.component";

import { CustomInterceptorService } from "./services/custom-interceptor.service";
import { initializeApp } from "./initailizer";
import { AppInitService } from "./services/app-init.service";

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
    WishComponent,
    OwnComponent,
    AdminComponent,
    CategoryTreeComponent,
    CategoryCreateComponent,
    CategoryFormComponent,
    CategoryComponent,
    CategoryEditComponent,
    CategoryPageComponent,
    ItemComponent,
    ItemCreateComponent,
    ItemFormComponent,
    ImageBoxComponent,
    ItemEditComponent,
    LoaderScreenComponent,
    SubcategoryFormComponent,
    ConfirmComponent,
    PagedCardsComponent,
    AboutComponent,
    SubcategoryComponent,
    SubcategoryPageComponent,
    SubcategoryCreateComponent,
    SubcategoryEditComponent,
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
    MatDialogModule,
  ],
  providers: [
    AppInitService,
    { provide: APP_INITIALIZER, useFactory: initializeApp, deps: [AppInitService], multi: true },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CustomInterceptorService,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
