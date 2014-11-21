package emfnotification.plugin;

import model.Person;
import model.presentation.ModelEditor;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
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
		System.out.println("Coool");

	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partClosed(IWorkbenchPart part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partDeactivated(IWorkbenchPart part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partOpened(IWorkbenchPart part) {
		
		if (part instanceof IEditorPart) {
			IEditorPart editorPart = (IEditorPart) part;
			
			if (editorPart instanceof ModelEditor) {
				System.out.println("Fudejooo");
				EContentAdapter adapter = new EContentAdapter() {
				      public void notifyChanged(Notification notification) {
				        super.notifyChanged(notification);
				        System.out
				            .println("Notfication received from the data model. Data model has changed!!!");
				        
				        if(notification.getFeature() instanceof EReferenceImpl){
				        	EReferenceImpl ref = (EReferenceImpl)notification.getFeature();
				        	System.out.println(ref.getName());
				        	
				        	if (notification.getNewValue() instanceof Person){
				        		Person person = (Person)notification.getNewValue();
				        		System.out.println(person.getFirstName());
				        		person.setFirstName("Big Daddy L");
				        	}
				        }
				      }
				};
				
				ModelEditor diagramDocumentEditor = (ModelEditor) editorPart;
	            diagramDocumentEditor.getEditingDomain().getResourceSet().eAdapters().add(adapter);
			}
		}

	}

}
