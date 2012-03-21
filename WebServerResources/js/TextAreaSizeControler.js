function textCounter(idText, maxlimit, idSpanInfo, cssClass, cssClassError) {
	text = document.getElementById(idText);
	text.className = cssClass;
	span = document.getElementById(idSpanInfo);
	span.innerHTML = "";
	if (text.value.length > 0 && text.value.length > maxlimit) {// if too long...trim it! 
		//field.value = field.value.substring(0, maxlimit);
		//alert('Attention, vous depassez la taille maximum autorisee de '+maxlimit+' caracteres !\n');
		//alert(field.style);
		text.className = cssClassError;
		span.innerHTML = 'Attention, vous depassez la taille maximum autorisee de '+maxlimit+' caracteres ! ('+text.value.length+')';
	}
}