import { NgModule } from '@angular/core';
import { MenuBannedComponent } from './menu-banned/menu-banned';
import { MenuNotConnectedComponent } from './menu-not-connected/menu-not-connected';
import { MenuConnectedComponent } from './menu-connected/menu-connected';
import { RunDetailsComponent } from './run-details/run-details';
@NgModule({
	declarations: [MenuBannedComponent,
    MenuNotConnectedComponent,
    MenuConnectedComponent,
    RunDetailsComponent],
	imports: [],
	exports: [MenuBannedComponent,
    MenuNotConnectedComponent,
    MenuConnectedComponent,
    RunDetailsComponent]
})
export class ComponentsModule {}
