import {Component, ChangeDetectionStrategy, Input} from '@angular/core';

@Component({
	selector: 'rating',
	templateUrl: 'rating.html',
	changeDetection: ChangeDetectionStrategy.OnPush
})
export class RatingComponent {

	@Input() public score: number = 1;
	@Input() public max: number = 5;

	@Input() public iconEmpty: string = 'star-outline';
	@Input() public iconHalf: string = 'star-half';
	@Input() public iconFull: string = 'star';

	public icons(): string[] {
		let step = 0.5;
		let score = Math.ceil(this.score / step) * step;

		let icons = [];
		for (let i = 1; i <= this.max; i++) {
			if (i <= score) {
				icons.push(this.iconFull);
			} else if (i - step <= score) {
				icons.push(this.iconHalf);
			} else {
				icons.push(this.iconEmpty);
			}
		}
		return icons;
	}

}
