import { NgModule } from '@angular/core';
import { MenuNotConnectedComponent } from './menu-not-connected/menu-not-connected';
import { MenuConnectedComponent } from './menu-connected/menu-connected';
@NgModule({
	declarations: [MenuNotConnectedComponent,
    MenuConnectedComponent],
	imports: [],
	exports: [MenuNotConnectedComponent,
    MenuConnectedComponent]
})
export class ComponentsModule {}
