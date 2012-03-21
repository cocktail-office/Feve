// liste des etats initiaux des checkbox
var checkboxCheckedList = new Array();
var checkboxUncheckedList = new Array();

/* changer le fond de la couleur de la cellule contenant une checkbox */
function changeCouleur(checkbox) {
	
	// voir si l'etat initial est connu ou pas
	indexChecked = indexOfValue(checkboxCheckedList, checkbox.name);
	indexUnchecked = indexOfValue(checkboxUncheckedList, checkbox.name);
	if (indexChecked == -1 && indexUnchecked == -1) {
		if (checkbox.checked) {
			checkboxUncheckedList.push(checkbox.name);
			indexUnchecked = indexOfValue(checkboxUncheckedList, checkbox.name);
		} else {
			checkboxCheckedList.push(checkbox.name);
			indexChecked = indexOfValue(checkboxCheckedList, checkbox.name);
		}
	}

	if (checkbox.checked && indexUnchecked != -1) {
		checkbox.parentNode.bgColor='green';
	}	else {
		if (!checkbox.checked && indexChecked != -1) {
			checkbox.parentNode.bgColor='red';
		}	else {
			checkbox.parentNode.bgColor='';
		}
	}
}

/* retourne l'index d'un objet dans le tableau. -1 si pas trouvé */
function indexOfValue(list, value) {
	//alert("recherche de "+value+" dans "+list.length);
	for (var i=0; i<list.length; i++) {
		//alert("list[i] "+list[i]);
		if (list[i] == value) {
			return i;
		}
	}
	return -1;
}