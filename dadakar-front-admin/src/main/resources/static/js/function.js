(function() {
	'use strict';
	
	function selectAll() {
		$("input[type='checkbox']").each(function() {
			if($(this).attr('id') !== 'bannishAll') {
				if($(this).is(':checked')) $(this).prop('checked', false);
				else $(this).prop('checked', true);
			}
		});
	}

	window.setTimeout(function() {
		$(".alert-dismissible").fadeTo(2000, 800).slideUp(800, function() { $(".alert-dismissible").alert('close'); });	
	}, 3000);
	$(document).ready(function () {
		$("#bannishAll").on("change", selectAll);
	});
}())