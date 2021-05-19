const API_ROUTE = "/api";
const ADDITION_PUBLIC = "/public";
const ADDITION_PRIVATE = "/profile";

export const CATEGORIES_ROUTE = API_ROUTE + "/categories";
export const SUBCATEGORIES_ROUTE = API_ROUTE + "/subcategories";
export const ITEMS_ROUTE_OLD = API_ROUTE + "/itemsJson";
export const ITEMS_ROUTE = API_ROUTE + "/items";
export const USERS_ROUTE = API_ROUTE + "/users";
export const IMAGES_ROUTE = API_ROUTE + "/images";
export const FILES_ROUTE = API_ROUTE + "/files";

export const REGISTER_ROUTE = API_ROUTE + ADDITION_PUBLIC + "/registration";
export const LOGIN_ROUTE = API_ROUTE + ADDITION_PUBLIC + "/login";
export const REFRESH_ROUTE = API_ROUTE + ADDITION_PUBLIC + "/refresh";
export const LOGOUT_ROUTE = API_ROUTE + ADDITION_PUBLIC + "/logout";

export const PORTFOLIO_ROUTE = API_ROUTE + ADDITION_PRIVATE + "/portfolio";

export const WISH_ROUTE = PORTFOLIO_ROUTE + "/wishItems";
export const OWN_ROUTE = PORTFOLIO_ROUTE + "/ownItems";

export const CHANGE_PROFILE_DATA = API_ROUTE + ADDITION_PRIVATE;
export const CHANGE_PASSWORD = API_ROUTE + ADDITION_PRIVATE + "/password";
