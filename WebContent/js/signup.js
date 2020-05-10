 $('#submitBtn').click(function() {
	 if ($('#passwdTxt').val() == $('#confPasswdTxt').val()) {
		 $('#signForm').submit();
	 } else {
		 $('#mismatch').show();
	 }
});
 
$('input[type=text], input[type=password], input[type=password]').click(function () {
	$('#mismatch').hide();
});