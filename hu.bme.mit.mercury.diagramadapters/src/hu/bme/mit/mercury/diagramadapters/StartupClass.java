package hu.bme.mit.mercury.diagramadapters;

import hu.bme.mit.mercury.diagramadapters.listeners.EditorPartListener;
import hu.bme.mit.mercury.diagramadapters.util.ResourceSetRegistry;
import hu.bme.mit.mercury.diagramadapters.util.SiriusEditorContentAdapter;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.ui.IEditorPart;
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
						IWorkbenchPage page = window.getActivePage();
						if (page != null) {
							page.addPartListener(EditorPartListener.getInstance());
						}
						
						IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							    .getActivePage().getEditorReferences();
						 for (IEditorReference iEditorReference : editorReferences) {
							IEditorPart editorPart = iEditorReference.getEditor(true);
							
							if (editorPart instanceof DDiagramEditor) {
								DDiagramEditor siriusEditor = (DDiagramEditor)editorPart;
								ResourceSet resourceSet = siriusEditor.getEditingDomain().getResourceSet();
								
								if(ResourceSetRegistry.register(resourceSet)){
									siriusEditor.getEditingDomain().getResourceSet().eAdapters().add(new SiriusEditorContentAdapter());
								}
								
							}
							
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
