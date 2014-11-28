package hu.bme.mit.mercury.rcp;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(400, 300));
		configurer.setShowCoolBar(false);
		configurer.setShowStatusLine(false);
		configurer.setTitle("RCP Mercury modeler");
	}
	

    @Override
    public void postWindowOpen() {
        super.postWindowOpen();
        
        IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
        for (int i = 0; i < windows.length; ++i) {
            IWorkbenchPage page = windows[i].getActivePage();
            if (page != null) {
                page.hideActionSet("org.eclipse.ui.actionSet.openFiles");
                page.hideActionSet("org.eclipse.ui.edit.text.actionSet.convertLineDelimitersTo");
                page.hideActionSet("org.eclipse.search.searchActionSet");
                page.hideActionSet("org.eclipse.ui.externaltools.ExternalToolsSet");
                page.hideActionSet("org.eclipse.ui.edit.text.actionSet.annotationNavigation");
                page.hideActionSet("org.eclipse.ui.edit.text.actionSet.navigation");
            }
        }
    }
}
