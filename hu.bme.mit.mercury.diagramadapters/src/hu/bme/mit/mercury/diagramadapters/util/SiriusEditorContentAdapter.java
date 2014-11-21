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
import org.eclipse.emf.ecore.EReference;
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
        
        if(notification.getNewValue() instanceof InputVariable && notification.getNotifier() instanceof FunctionBlock && notification.getFeature() instanceof EReference){
        	EReference ref = (EReference)notification.getFeature();
        	InputVariable inputVariabe = (InputVariable)notification.getNewValue();
        	if(ref.getName().equals("inputVariables")){
        		FunctionBlock functionBlock = (FunctionBlock)notification.getNotifier();
        		
        		EList<FunctionCall> calls = functionBlock.getCalls();
        		for (FunctionCall functionCall : calls) {
        			FunctionInputVariableReference functionInputVariableReference = MercuryFactory.eINSTANCE.createFunctionInputVariableReference();
            		functionInputVariableReference.setVariable(inputVariabe);
					functionCall.getInputs().add(functionInputVariableReference);
				}
        	}
        	
        }
        
        if(notification.getNewValue() instanceof OutputVariable && notification.getNotifier() instanceof FunctionBlock && notification.getFeature() instanceof EReference){
        	EReference ref = (EReference)notification.getFeature();
        	OutputVariable outputVariable = (OutputVariable)notification.getNewValue();
        	if(ref.getName().equals("outputVariables")){
        		FunctionBlock functionBlock = (FunctionBlock)notification.getNotifier();
        		
        		EList<FunctionCall> calls = functionBlock.getCalls();
        		for (FunctionCall functionCall : calls) {
        			FunctionOutputVariableReference functionOutputVariableReference = MercuryFactory.eINSTANCE.createFunctionOutputVariableReference();
            		functionOutputVariableReference.setVariable(outputVariable);
					functionCall.getOutputs().add(functionOutputVariableReference);
				}
        	}
        }
        
        if(notification.getOldValue() instanceof InputVariable && notification.getNotifier() instanceof FunctionBlock && notification.getFeature() instanceof EReference){
        	EReference ref = (EReference)notification.getFeature();
        	if(ref.getName().equals("inputVariables")){
        		FunctionBlock functionBlock = (FunctionBlock)notification.getNotifier();
        		
        		EList<FunctionCall> calls = functionBlock.getCalls();
        		for (FunctionCall functionCall : calls) {
        			FunctionInputVariableReference refToDelete = null;
        			EList<FunctionInputVariableReference> inputs = functionCall.getInputs();
        			for (FunctionInputVariableReference functionInputVariableReference : inputs) {
						if(functionInputVariableReference.getVariable() == null){
							refToDelete = functionInputVariableReference;
						}
					}
        			inputs.remove(refToDelete);
				}
        	}
        	
        	System.out.println(notification.getOldValue());
        	System.out.println(notification.getNotifier());
        	System.out.println(notification.getFeature());
        	System.out.println();
        }
        
        if(notification.getOldValue() instanceof OutputVariable && notification.getNotifier() instanceof FunctionBlock && notification.getFeature() instanceof EReference){
        	EReference ref = (EReference)notification.getFeature();
        	if(ref.getName().equals("outputVariables")){
        		FunctionBlock functionBlock = (FunctionBlock)notification.getNotifier();
        		
        		EList<FunctionCall> calls = functionBlock.getCalls();
        		for (FunctionCall functionCall : calls) {
        			FunctionOutputVariableReference refToDelete = null;
        			EList<FunctionOutputVariableReference> outputs = functionCall.getOutputs();
        			for (FunctionOutputVariableReference functionOutputVariableReference : outputs) {
						if(functionOutputVariableReference.getVariable() == null){
							refToDelete = functionOutputVariableReference;
						}
					}
        			outputs.remove(refToDelete);
				}
        	}
        	
        	System.out.println(notification.getOldValue());
        	System.out.println(notification.getNotifier());
        	System.out.println(notification.getFeature());
        	System.out.println();
        }
        
      }
	
}
