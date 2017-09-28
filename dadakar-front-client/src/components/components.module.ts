import { NgModule } from '@angular/core';
import { MenuNotConnectedComponent } from './menu-not-connected/menu-not-connected';
import { MenuConnectedComponent } from './menu-connected/menu-connected';
import { RunDetailsComponent } from './run-details/run-details';
@NgModule({
	declarations: [MenuNotConnectedComponent,
    MenuConnectedComponent,
    RunDetailsComponent],
	imports: [],
	exports: [MenuNotConnectedComponent,
    MenuConnectedComponent,
    RunDetailsComponent]
})
export class ComponentsModule {}
