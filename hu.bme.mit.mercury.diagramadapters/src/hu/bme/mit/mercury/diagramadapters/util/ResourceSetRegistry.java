package hu.bme.mit.mercury.diagramadapters.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.ResourceSet;

public class ResourceSetRegistry {

	private static List<ResourceSet> resourceSets = new ArrayList<>();
	
	public static boolean register(ResourceSet rs) {
		boolean isIn = false;
		
		for (ResourceSet resourceSet : resourceSets) {
			if(resourceSet.equals(rs)){
				isIn = true;
			}
		}
		
		if(!isIn) resourceSets.add(rs);
		
		return !isIn;
	}
	
}
