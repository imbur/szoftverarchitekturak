package hu.bme.mit.mercury.design.service;

import java.util.ArrayList;
import java.util.List;

import hu.bme.mit.mercury.design.MarkerUtil2;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;

public class MercuryValidationService {
	
	public MercuryValidationService () {}
	
	public boolean errorService(EObject o) throws CoreException{
		return countMarkersOfSeverity(o, IMarker.SEVERITY_ERROR);
	}
		
	public boolean warningService(EObject o) throws CoreException{
		return countMarkersOfSeverity(o, IMarker.SEVERITY_WARNING);
	}

	private boolean countMarkersOfSeverity(EObject o, int severity) {
		IMarker[] markers = MarkerUtil2.getMarkers(o);
		
		List<IMarker> l = new ArrayList<>();
		
		for (IMarker iMarker : markers) {
			if(iMarker.getAttribute("severity", 0) == severity){
				l.add(iMarker);
			}
		}
		
		return l.size() == 0;
	}
	
	public String messageService(EObject o) throws CoreException {
		IMarker[] markers = MarkerUtil2.getMarkers(o);
		
		if (markers.length > 0) {
			return (String) markers[0].getAttribute(IMarker.MESSAGE);
		}
		
		return "";
	}
	
	

	
}
