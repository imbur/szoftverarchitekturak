package hu.bme.mit.mercury.editor.aux;

import java.util.Collection;

import hu.bme.mit.mercury.incquery.MyRuleMatch;
import hu.bme.mit.mercury.incquery.MyRuleMatcher;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class Sample {

	public void sample() throws IncQueryException{
		
		ResourceSet rs = null;
		
		IncQueryEngine eiqEngine = IncQueryEngine.on(rs);
		
		MyRuleMatcher meccser = MyRuleMatcher.on(eiqEngine);
		
		// Tadaaa
		Collection<MyRuleMatch> allMatches = meccser.getAllMatches();
		
	}
	
}
