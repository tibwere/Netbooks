// attributi data-* 
var attributes = ['isbn', 'title', 'author', 'publisher', 'owner'];

$('[data-toggle="modal"]').on('click', function (e) {
  // e.target riferisce all'elemento che ha scatenato l'evento
	var $target = $(e.target);
  // prendo la modale targettata 
	var modalSelector = $target.data('target');
  
  // iterate over each possible data-* attribute
	attributes.forEach(function (attributeName) {
    // retrieve the dom element corresponding to current attribute
		var $modalAttribute = $(modalSelector + ' #modal-' + attributeName);
		var dataValue = $target.data(attributeName);
		// quando il valore di un attributo non è presente
		// dataValue corrisponde ad un valore indefinito.
		// Il metodo text() setta il testo dell'attributo 
		// con il valore contenuto in dataValue se non e' indefinito
		// ('' altrimenti)
		$modalAttribute.text(dataValue || '');
	});
	$('#isbn').val($target.data('isbn').toString());
	$('#title').val($target.data('title').toString());
	$('#owner').val($target.data('owner').toString());
});