package hu.bme.mit.mercury.diagramadapters.listeners;

import hu.bme.mit.mercury.diagramadapters.util.ResourceSetRegistry;
import hu.bme.mit.mercury.diagramadapters.util.SiriusEditorContentAdapter;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;


public class EditorPartListener implements IPartListener {
	
	private static EditorPartListener instance;

	public static EditorPartListener getInstance() {
		if (instance == null) {
			instance = new EditorPartListener();
		}
		return instance;
	}

	@Override
	public void partActivated(IWorkbenchPart part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partClosed(IWorkbenchPart part) {
		
//		if (part instanceof IEditorPart) {
//			IEditorPart editorPart = (IEditorPart) part;
//			
//			if (editorPart instanceof DDiagramEditor) {
//				System.out.println("Bye Sirius editor");
//				
//				DDiagramEditor siriusEditor = (DDiagramEditor)editorPart;
//				siriusEditor.getEditingDomain().getResourceSet().eAdapters().clear();
//			}
//		}

	}

	@Override
	public void partDeactivated(IWorkbenchPart part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partOpened(IWorkbenchPart part) {

		if (part instanceof IEditorPart) {
			IEditorPart editorPart = (IEditorPart) part;
			
			if (editorPart instanceof DDiagramEditor) {
				DDiagramEditor siriusEditor = (DDiagramEditor)editorPart;
				ResourceSet resourceSet = siriusEditor.getEditingDomain().getResourceSet();
				
				if(ResourceSetRegistry.register(resourceSet)){
					siriusEditor.getEditingDomain().getResourceSet().eAdapters().add(new SiriusEditorContentAdapter());
				}
				
			}
		}

	}

}
