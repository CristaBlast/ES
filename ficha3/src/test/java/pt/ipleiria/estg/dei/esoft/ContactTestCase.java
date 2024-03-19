package pt.ipleiria.estg.dei.esoft;
import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.dei.esoft.Contact;
import static org.junit.jupiter.api.Assertions.*;
public class ContactTestCase {
    @Test
    public void testCreateContact() {
        var contact = new Contact("foo", "bar", "912 345 678");
        assertEquals("foo", contact.getFirstName());
        assertEquals("bar", contact.getLastName());
        assertEquals("912 345 678", contact.getPhone());
    }

    /*@Test
    public void testCreateContactFails() {
        var contact = new Contact("foo", "912 345 678");
        assertEquals("foo", contact.getFirstName());
        assertNotNull(contact.getLastName(),"O ultimo nome nah tá definido");
        assertEquals("912 345 678", contact.getPhone());
    }*/
}
