package emfnotification.plugin;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class StartupClass implements IStartup {

	@Override
	public void earlyStartup() {
		System.out.println("Niceee");
		
//		boolean over = false;
//		try {
//			do{
//				Thread.sleep(500);
//				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
//				over = window == null;
//				if (window != null)
//				{
//					System.out.println("Niceee");
//					IWorkbenchPage page = window.getActivePage();
//					page.addPartListener(EditorPartListener.getInstance());
//				}
//			}while(over);
//			System.out.println("Niceee");
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		final IWorkbench workbench = PlatformUI.getWorkbench();
		 workbench.getDisplay().asyncExec(new Runnable() {
		   public void run() {
		     IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		     if (window != null) {
		       System.out.println("Toltott");
		       IWorkbenchPage page = window.getActivePage();
		       if(page != null){
		    	   System.out.println("Nagyon jooo!");
		    	   page.addPartListener(EditorPartListener.getInstance());
		       }
		     }
		   }
		 });
	}

}
