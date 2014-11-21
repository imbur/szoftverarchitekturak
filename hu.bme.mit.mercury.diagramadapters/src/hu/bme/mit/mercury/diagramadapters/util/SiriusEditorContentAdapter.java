package hu.bme.mit.mercury.diagramadapters.util;


import java.util.ArrayList;
import java.util.List;

import mercury.FunctionBlock;
import mercury.FunctionCall;
import mercury.FunctionInputVariableReference;
import mercury.FunctionOutputVariableReference;
import mercury.InputVariable;
import mercury.MercuryFactory;
import mercury.OutputVariable;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;

public class SiriusEditorContentAdapter extends EContentAdapter {

	@Override
	public void notifyChanged(Notification notification) {
        super.notifyChanged(notification);
        
        if(notification.getFeature() instanceof EReferenceImpl){
        	EReferenceImpl ref = (EReferenceImpl)notification.getFeature();
        	if(ref.getName().equals("calledFunction")){
        	
        		if(notification.getEventType() == Notification.SET){
        			
        			if(notification.getNewValue() == null){
        				FunctionCall functionCall = (FunctionCall)notification.getNotifier();
    					functionCall.getInputs().clear();
    					functionCall.getOutputs().clear();
        			}
        			else if (notification.getNewValue() instanceof FunctionBlock) {
						FunctionBlock functionBlock = (FunctionBlock) notification.getNewValue();
						
						List<FunctionInputVariableReference> inputReferences = new ArrayList<>();
						EList<InputVariable> inputVariables = functionBlock.getInputVariables();
						for (InputVariable inputVariable : inputVariables) {
							FunctionInputVariableReference functionInputVariableReference = MercuryFactory.eINSTANCE.createFunctionInputVariableReference();
							functionInputVariableReference.setVariable(inputVariable);
							inputReferences.add(functionInputVariableReference);
						}
						
						List<FunctionOutputVariableReference> outputReferences = new ArrayList<>();
						EList<OutputVariable> outputVariables = functionBlock.getOutputVariables();
						for (OutputVariable outputVariable : outputVariables) {
							FunctionOutputVariableReference functionOutputVariableReference = MercuryFactory.eINSTANCE.createFunctionOutputVariableReference();
							functionOutputVariableReference.setVariable(outputVariable);
							outputReferences.add(functionOutputVariableReference);
						}
						
						FunctionCall functionCall = (FunctionCall)notification.getNotifier();
						functionCall.getInputs().clear();
						functionCall.getOutputs().clear();
						functionCall.getInputs().addAll(inputReferences);
						functionCall.getOutputs().addAll(outputReferences);
					}
        		}
				
        	}
        }
      }
	
}
