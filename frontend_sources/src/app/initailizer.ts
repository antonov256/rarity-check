import { AppInitService } from "./services/app-init.service";

export function initializeApp(appInitService: AppInitService) {
  return () => {
    return appInitService.init();
  };
}
