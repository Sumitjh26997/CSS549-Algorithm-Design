package program1;
/**
 * Class to represent a pet.
 * A pet is an entity with an id, a name, a list of it's preferences, and it's match.
 * Functionality includes setters and getters for all fields.
 * This class is a subclass of Entity and can be instantiated.
 * @author Sumit Hotchandani
 */
public class Pet extends Entity {
	/**
	 * Constructor for the Pet class.
	 * pre: none
	 * post: instance variables are initialized.
	 */
	public Pet(int id, String name, int size) {
		super(id, name, size);
	}
}