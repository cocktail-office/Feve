package org.cocktail.feve.components;

import org.cocktail.feve.components.common.FeveWebComponent;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

public class PageBibliotheque 
	extends FeveWebComponent {
	
	public boolean isModification = false;
	
	public PageBibliotheque(WOContext context) {
		super(context);
	}
	
	public boolean isAfficherLnkModification() {
		boolean isAfficherLnkModification = false;
		
		if (feveUserInfo().isAdmin()) {
			isAfficherLnkModification = true;
		}
		
		return isAfficherLnkModification;
	}
	
	public WOComponent toModification() {
		isModification = true;
		return null;
	}
	
	public WOComponent toLectureSeule() {
		isModification = false;
		return null;
	}
	
	public boolean isDisabled() {
		boolean isDisabled = true;
		
		if (isModification) {
			isDisabled = false;
		}
		
		return isDisabled;
	}
}