import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { AuthGuard } from "./guards/auth.guard";

import { AdminComponent } from "./views/admin/admin.component";
import { AuthComponent } from "./views/auth/auth.component";
import { RegisterComponent } from "./views/auth/register/register.component";
import { SignInComponent } from "./views/auth/sign-in/sign-in.component";
import { CategoryComponent } from "./views/category/category.component";
import { CategoryCreateComponent } from "./views/category/create/create.component";
import { CategoryEditComponent } from "./views/category/edit/edit.component";
import { HomeComponent } from "./views/home/home.component";
import { ItemPageComponent } from "./views/item-page/item-page.component";
import { PortfolioComponent } from "./views/portfolio/portfolio.component";
import { ProfileComponent } from "./views/profile/profile.component";
import { ItemComponent } from './views/item/item.component';
import { ItemCreateComponent } from './views/item/create/create.component';

const authRoutes = [
  { path: "", component: SignInComponent },
  { path: "sign-in", component: SignInComponent },
  { path: "register", component: RegisterComponent },
];

const categoryRoutes = [
  { path: "create", component: CategoryCreateComponent },
  { path: "edit/:id", component: CategoryEditComponent },
];

const itemRoutes = [
  { path: "create", component: ItemCreateComponent },
];

const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "auth", component: AuthComponent, children: authRoutes },
  { path: "profile", component: ProfileComponent, canActivate: [AuthGuard] },
  { path: "portfolio", component: PortfolioComponent, canActivate: [AuthGuard] },
  { path: "item-page", component: ItemPageComponent },
  { path: "admin", component: AdminComponent },
  { path: "category", component: CategoryComponent, children: categoryRoutes },
  { path: "item", component: ItemComponent, children: itemRoutes },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
