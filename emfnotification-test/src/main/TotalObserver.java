package main;

import model.Person;
import model.PersonList;
import model.ModelFactory;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import  org.eclipse.emf.ecore.impl.EReferenceImpl;

public class TotalObserver {
  private PersonList persons;

  public TotalObserver() {
    ModelFactory factory = ModelFactory.eINSTANCE;
    persons = factory.createPersonList();

    EContentAdapter adapter = new EContentAdapter() {
      public void notifyChanged(Notification notification) {
        super.notifyChanged(notification);
        System.out
            .println("Notfication received from the data model. Data model has changed!!!");
        
        if(notification.getFeature() instanceof EReferenceImpl){
        	EReferenceImpl ref = (EReferenceImpl)notification.getFeature();
        	System.out.println(ref.getName());
        	
        	System.out.println(notification.getNewValue());
        	if (notification.getNewValue() instanceof Person){
        		Person person = (Person)notification.getNewValue();
        		System.out.println(person.getFirstName());
        	}
        }
        System.out.println(notification.getFeature().toString());
      }
    };
    persons.eAdapters().add(adapter);
  }

  public void doStuff() {
    ModelFactory factory = ModelFactory.eINSTANCE;
    Person person = factory.createPerson();
    person.setFirstName("Lars");
    System.out.println("I'm adding a person.");
    persons.setPerson(person);
    System.out.println("I'm changing a entry");
    Person person2 = factory.createPerson();
    person2.setFirstName("Lars2");
    persons.setPerson(person2);
  }
}