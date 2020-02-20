

$(function () {
	
	$(".rateyo").rateYo({
		fullStar: true,
		spacing: "10px"
	});
	
	if ($("#rate").val() != 0)
		$("#ratingstars").rateYo().rateYo("rating", $("#rate").val());
	
});

$('#submitBtn').click(function() {
	$('#rate').val(($("#ratingstars").rateYo().rateYo("rating")));
	$('#evalform').submit();
});
