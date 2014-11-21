package emfnotification.plugin;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.IEditorPart;

public class TestEditorAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == TestModelConnector.class) {
            if (adaptableObject instanceof IEditorPart) {
            	System.out.println("cooleditor");
                return new TestModelConnector((IEditorPart) adaptableObject);
            }
        }
        return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] { TestModelConnector.class };
	}

}
