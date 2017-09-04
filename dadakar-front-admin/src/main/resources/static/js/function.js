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
	
	$("#bannishAll").on("change", selectAll);
}())