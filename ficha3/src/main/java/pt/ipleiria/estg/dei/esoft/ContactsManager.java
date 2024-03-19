package pt.ipleiria.estg.dei.esoft;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
public class ContactsManager {
    private List<Contact> contacts;
    private HashMap<String, List<Contact>> labels;
    public ContactsManager() {
        contacts = new LinkedList<>();
        labels = new HashMap<>(200);
    }
    public List<String> getLabels() {
        // TODO return all the labels
        return null;
    }
    public List<Contact> getContacts(String... labels) {
        // TODO get contacts in labels or all ...
        return contacts;
    }
    public List<Contact> search(String term, String... labels) {
        // TODO search for contacts with term, and also in specific labels
        for (int i = 0; i < size(); i++) {
            if (contacts.contains(term))
                return null;
        }
        return null;
    }
    public void addContact(Contact contact, String... labels) {
        if (!contacts.contains(contact)) contacts.add(contact);
        if (labels.length == 0) return;
        for (var label : labels) {
            if (!this.labels.containsKey(label)) {
                this.labels.put(label, new LinkedList<>());
            }
            var contactsLabel = this.labels.get(label);

            if (!contactsLabel.contains(contact)) {
                contactsLabel.add(contact);
            }
        }
    }
    public void removeContact(Contact contact) {
        contacts.remove(contact);
        labels.values().forEach(contacts -> contacts.remove(contact));
    }
    public int size() {
        return contacts.size();
    }

    public boolean isEmpty() { return contacts.isEmpty(); }
}