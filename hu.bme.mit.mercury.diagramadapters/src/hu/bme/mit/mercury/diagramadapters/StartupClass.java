package hu.bme.mit.mercury.diagramadapters;

import hu.bme.mit.mercury.diagramadapters.listeners.EditorPartListener;

import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class StartupClass implements IStartup {

	@Override
	public void earlyStartup() {

		final IWorkbench workbench = PlatformUI.getWorkbench();
		 workbench.getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchWindow window = null;
				do {
					window = workbench.getActiveWorkbenchWindow();
					if (window != null) {
						System.out.println("Toltott");
						IWorkbenchPage page = window.getActivePage();
						if (page != null) {
							System.out.println("Nagyon jooo!");
							page.addPartListener(EditorPartListener.getInstance());
						}
						IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							    .getActivePage().getEditorReferences();
							 
						 for (IEditorReference iEditorReference : editorReferences) {
							System.out.println(iEditorReference.getPartName());
						 }
					} else {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
						}
					}
				} while (window == null);
			}
		 });
		 

	}

}
