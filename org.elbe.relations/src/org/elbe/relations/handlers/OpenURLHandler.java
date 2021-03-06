/***************************************************************************
 * This package is part of Relations application.
 * Copyright (C) 2004-2016, Benno Luthiger
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 ***************************************************************************/

package org.elbe.relations.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.Shell;
import org.elbe.relations.RelationsConstants;
import org.elbe.relations.internal.services.ISelectedTextProvider;
import org.elbe.relations.internal.utility.BrowserUtil;

/**
 * Handler that gets selected text from the inspector view and pastes this text
 * as url in the default browser.
 *
 * @author lbenno
 */
public class OpenURLHandler {

	@Execute
	void openURL(final EPartService inPartService,
	        final MApplication inApplication,
	        @Named(IServiceConstants.ACTIVE_SHELL) final Shell inShell) {
		final MPart lPart = inPartService
		        .findPart(RelationsConstants.PART_INSPECTOR);
		if (lPart != null) {
			if (lPart.getObject() instanceof ISelectedTextProvider) { // NOPMD
				String lURL = ((ISelectedTextProvider) lPart.getObject())
				        .getSelection();
				if (!lURL.isEmpty()) {
					if (!BrowserUtil.textIsURL(lURL)) {
						lURL = BrowserUtil.PREFIX_HTTP + lURL;
					}
					BrowserUtil.startBrowser(lURL);
				}
			}
		}
	}

}
