package org.cocktail.feve.components;

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.appserver.WOContext;

/**
 * Ecran d'affichage du fichier de logs
 * @author ctarade
 *
 */
public class PageLogs extends FeveWebComponent {
	public PageLogs(WOContext context) {
		super(context);
	}
    
	public String getLogs() {
		String logs = app.getLogs();
		if (logs == null) {
			return "Aucun log disponible.";
		}	else {
			logs = StringCtrl.replace(logs, "<", "&lt;");
			logs = StringCtrl.replace(logs, ">", "&gt;");
			logs = StringCtrl.replace(logs, "\n", "<br/>");
			logs = StringCtrl.replace(logs, " ", "&nbsp;");
			logs = StringCtrl.replace(logs, "\"", "&quot;");
			return logs;
			//return StringCtrl.replace(StringCtrl.replace(logs, "\n", "<br>"), " ", "&nbsp;"); 
		}
	}

}