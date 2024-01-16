package program1;
/**
 * Class to represent a person.
 * A person is an entity with an id, a name, a list of it's preferences, and it's match.
 * Functionality includes setters and getters for all fields.
 * This class is a subclass of Entity and can be instantiated.
 * @author Sumit Hotchandani
 */
public class Person extends Entity {
	/**
	 * Constructor for the Person class.
	 * pre: none
	 * post: instance variables are initialized.
	 */
	public Person(int id, String name, int size) {
		super(id, name, size);
	}
}