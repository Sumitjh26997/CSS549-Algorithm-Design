/**
 * Class to represent an entity in the program.
 * An entity can be a person or a pet.
 * It is represented by an id, a name, a list of it's preferences, and it's match.
 * Functionality includes setters and getters for all fields.
 * This class is abstract and cannot be instantiated.
 * @author Sumit Hotchandani
 */
package program1;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Entity {
	private int id;
	private String name;
	private Queue<Integer> preferences;
	private int match;
	private int[]	rank;
	
	/**
	 * Constructor for the Entity class.
	 * pre: none
	 * post: instance variables are initialized.
	 */
	public Entity (int id, String name, int size) {
		this.id = id;
		this.name = name;
		this.preferences = new LinkedList<>();
		this.match = -1;
		this.rank = new int[size + 1];
	}

	/**
	 * accessor for the id of the entity.
	 * pre: none
	 * post: returns id of the entity
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * accessor for the name of the entity.
	 * pre: none
	 * post: returns name of the entity
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * accessor for the preference list of the entity.
	 * pre: none
	 * post: returns preference list of the entity
	 */
	public Queue<Integer> getPreferences() {
		return this.preferences;
	}

	/**
	 * accessor for the match of the entity.
	 * pre: none
	 * post: returns match of the entity
	 */
	public int getMatch() {
		return this.match;
	}

	/**
	 * accessor for the rank i.e. inverse preferences of the entity.
	 * pre: none
	 * post: returns rank of the entity for the given index
	 */
	public int getRank(int index) {
		return this.rank[index];
	}

	/**
	 * setter for the match of the entity.
	 * pre: none
	 * post: match is set.
	 */
	public void setMatch(int match) {
		this.match = match;
	}

	/**
	 * setter for the preference list of the entity.
	 * pre: linked list has been initialized.
	 * post: preference list is set.
	 */
	public void setPreferences(Queue<Integer> preferences) {
		this.preferences = preferences;
	}

	/**
	 * setter for the rank i.e. inverse preferences of the entity.
	 * pre: rank has been allocated memory.
	 * post: rank is set.
	 */
	public void setRank(int[] rank) {
		this.rank = rank;
	}
}