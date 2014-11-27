package hu.bme.mit.mercury.rcp;

import java.net.URL;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.osgi.framework.Bundle;

@SuppressWarnings("restriction")
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "hu.bme.mit.mercury.rcp.perspective";

	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

    @Override
	public void postStartup() {
		super.postStartup();
	}

	@Override
    public IAdaptable getDefaultPageInput() {
        // Quick and dirty solution to refresh Project explorer at startup
        return ResourcesPlugin.getWorkspace().getRoot();
    }

    @Override
    public void initialize(IWorkbenchConfigurer configurer) {
        super.initialize(configurer);

        // Quick and dirty solution to make Project Explorer show texts and icons correctly
        IDE.registerAdapters();
        final String path = "icons/full/obj16/";
        Bundle ideBundle = Platform.getBundle(IDEWorkbenchPlugin.IDE_WORKBENCH);
        addImage(configurer, ideBundle, IDE.SharedImages.IMG_OBJ_PROJECT, path + "prj_obj.gif", true);
        addImage(configurer, ideBundle, IDE.SharedImages.IMG_OBJ_PROJECT_CLOSED, path + "cprj_obj.gif", true);
    }

    private void addImage(IWorkbenchConfigurer configurer, Bundle bundle, String symbolicName, String path, boolean shared) {
        URL url = bundle.getEntry(path);
        ImageDescriptor desc = ImageDescriptor.createFromURL(url);
        configurer.declareImage(symbolicName, desc, shared);
    }

}
