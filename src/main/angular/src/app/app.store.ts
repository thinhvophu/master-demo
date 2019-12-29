import {AppState} from "./state/AppState";
import {Action} from "@ngrx/store";

export const INIT_APP_STATE: AppState = {
  appName: 'test',
  version: '0.0.1'
};

function changeAppState(currentState: AppState, action: Action): AppState {
  return INIT_APP_STATE;
}
