(function() {
	'use strict';
	
	function loadImg(target) {
		console.log(target.id);
		fetch("http://localhost:8180/dadakar/img/name:" + target.id, {
				headers: {
					"Authorization": $("#token").val(),
					"Content-Type": "application/json"
				}
		})
			.then(response => response.blob())
			.then(blob => {
				let img = document.getElementById(target.id);
				let a = document.getElementById(target.id + "-" + img.getAttribute("alt"));
				let url = URL.createObjectURL(blob);
				img.src = `${url}`;
				a.href = `${url}`;
			});	
	}
	
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
	$(".disabled").on("click", function(ev) {ev.preventDefault(); });
	$(document).ready(function () {
		$(".image").each(function() {loadImg(this); })
		$("#bannishAll").on("change", selectAll);
	});
}())