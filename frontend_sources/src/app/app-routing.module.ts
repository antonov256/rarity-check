import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { AuthGuard } from "./guards/auth.guard";
import { AdminGuard } from "./guards/admin.guard";

import { AdminComponent } from "./views/admin/admin.component";
import { AuthComponent } from "./views/auth/auth.component";
import { RegisterComponent } from "./views/auth/register/register.component";
import { SignInComponent } from "./views/auth/sign-in/sign-in.component";
import { CategoryComponent } from "./views/category/category.component";
import { CategoryCreateComponent } from "./views/category/create/create.component";
import { CategoryEditComponent } from "./views/category/edit/edit.component";
import { CategoryPageComponent } from "./views/category/page/page.component";
import { SubcategoryComponent } from "./views/subcategory/subcategory.component";
import { SubcategoryCreateComponent } from "./views/subcategory/create/create.component";
import { SubcategoryEditComponent } from "./views/subcategory/edit/edit.component";
import { SubcategoryPageComponent } from "./views/subcategory/page/page.component";
import { HomeComponent } from "./views/home/home.component";
import { PortfolioComponent } from "./views/portfolio/portfolio.component";
import { ProfileComponent } from "./views/profile/profile.component";
import { ItemComponent } from "./views/item/item.component";
import { ItemCreateComponent } from "./views/item/create/create.component";
import { ItemEditComponent } from "./views/item/edit/edit.component";
import { ItemPageComponent } from "./views/item/page/page.component";
import { AboutComponent } from "./views/about/about.component";

const authRoutes = [
  { path: "", component: SignInComponent },
  { path: "sign-in", component: SignInComponent },
  { path: "register", component: RegisterComponent },
];

const categoryRoutes = [
  { path: "create", component: CategoryCreateComponent, canActivate: [AuthGuard, AdminGuard] },
  { path: ":id", component: CategoryPageComponent },
  { path: "edit/:id", component: CategoryEditComponent, canActivate: [AuthGuard, AdminGuard] },
];

const subcategoryRoutes = [
  { path: "create", component: SubcategoryCreateComponent, canActivate: [AuthGuard, AdminGuard] },
  { path: ":id", component: SubcategoryPageComponent },
  { path: "edit/:id", component: SubcategoryEditComponent, canActivate: [AuthGuard, AdminGuard] },
];

const itemRoutes = [
  { path: "create", component: ItemCreateComponent, canActivate: [AuthGuard, AdminGuard] },
  { path: ":id", component: ItemPageComponent },
  { path: "edit/:id", component: ItemEditComponent, canActivate: [AuthGuard, AdminGuard] },
];

const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "auth", component: AuthComponent, children: authRoutes },
  { path: "profile", component: ProfileComponent, canActivate: [AuthGuard] },
  {
    path: "portfolio",
    component: PortfolioComponent,
    canActivate: [AuthGuard],
  },
  { path: "admin", component: AdminComponent, canActivate: [AuthGuard, AdminGuard] },
  { path: "category", component: CategoryComponent, children: categoryRoutes },
  { path: "subcategory", component: SubcategoryComponent, children: subcategoryRoutes },
  { path: "item", component: ItemComponent, children: itemRoutes },
  { path: "about", component: AboutComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
