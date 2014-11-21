package emfnotification.plugin;

import model.presentation.ModelEditor;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.ui.IEditorPart;

public class TestModelConnector {
	
	protected IEditorPart editorPart;
	
	
	public TestModelConnector(IEditorPart editorPart) {
		this.editorPart = editorPart;
	}
	
	public Notifier getNotifier() {
		Notifier result = null;
		if (editorPart instanceof ModelEditor) {
			ModelEditor diagramDocumentEditor = (ModelEditor) editorPart;
            return diagramDocumentEditor.getEditingDomain().getResourceSet();
		}
		return result;
	}

}

