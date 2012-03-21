package org.cocktail.feve.app;

import org.cocktail.fwkcktlwebapp.common.CktlLog;

import com.webobjects.eoaccess.EOObjectNotAvailableException;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOSharedEditingContext;

import er.extensions.eof.ERXDatabaseContextDelegate;

public class FeveDatabaseContextDelegate
		extends ERXDatabaseContextDelegate {

	/**
	 * Delegate on EODatabaseContext that gets called when a to-one fault cannot
	 * find its data in the database. The object is a cleared fault. We raise here
	 * to restore the functionality that existed prior to WebObjects 4.5. Whenever
	 * a fault fails for a globalID (i.e. the object is NOT found in the
	 * database), we may raise.
	 * 
	 * Source: Kelly Hawk,
	 * http://wodeveloper.com/omniLists/eof/2000/December/msg00149.html
	 */
	@Override
	public boolean databaseContextFailedToFetchObject(com.webobjects.eoaccess.EODatabaseContext context,
																											java.lang.Object object,
																											com.webobjects.eocontrol.EOGlobalID globalID) {
		EOEditingContext ec = ((EOEnterpriseObject) object).editingContext();

		super.databaseContextFailedToFetchObject(context, object, globalID);

		// we need to refault the object before raising, otherwise, if the caller
		// traps the exception, it will be a successful
		// lookup the next time a fault with the same global id fires. NOTE:
		// refaulting in a sharedEditingContext is illegal,
		// so we specifically check for that special case.
		if (!(ec instanceof EOSharedEditingContext)) {
			context.refaultObject((EOEnterpriseObject) object, globalID, ec);
		}

		String failureMessage = "Failed to fetch " + object.getClass() + " with globalID " + globalID;
		CktlLog.log(failureMessage);
		throw new EOObjectNotAvailableException(failureMessage);
	}

}
