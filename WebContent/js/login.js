$('#showChk').change(function () {
	var text = $('#showLbl').text();
	if (text == 'Show '){
	    $('#showLbl').text('Hide ');
	    $('#showGlyph').removeClass('fas fa-eye').addClass('fas fa-eye-slash');
	    $('#passwordTxt').attr('type', 'text');
	} else {
		$('#showLbl').text('Show ')
		$('#showGlyph').removeClass('fas fa-eye-slash').addClass('fas fa-eye');
		$('#passwordTxt').attr('type', 'password');
	}
 });