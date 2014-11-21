package main;

import model.Person;
import model.PersonList;
import model.ModelFactory;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

public class ElementObserver {
  private PersonList persons;

  public ElementObserver() {
    ModelFactory factory = ModelFactory.eINSTANCE;
    persons = factory.createPersonList();

    Adapter adapter = new AdapterImpl() {
      public void notifyChanged(Notification notification) {
        System.out
            .println("Notfication received from the data model. Data model has changed!!!");
      }
    };
    persons.eAdapters().add(adapter);
  }

//  public void doStuff() {
//    ModelFactory factory = ModelFactory.eINSTANCE;
//    Person person = factory.createPerson();
//    person.setFirstName("Lars");
//    System.out.println("I'm adding a person.");
//    persons.getPersons().add(person);
//    System.out.println("I'm changing a entry");
//    persons.getPersons().get(0).setFirstName("Lars2");
//  }
}