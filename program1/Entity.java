package program1;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Entity {
	private int id;
	private String name;
	private Queue<Integer> preferences;
	private int match;
	private int[]	rank;
	
	public Entity (int id, String name, int size) {
		this.id = id;
		this.name = name;
		this.preferences = new LinkedList<>();
		this.match = -1;
		this.rank = new int[size + 1];
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Queue<Integer> getPreferences() {
		return this.preferences;
	}

	public int getMatch() {
		return this.match;
	}

	public int getRank(int index) {
		return this.rank[index];
	}

	public void setMatch(int match) {
		this.match = match;
	}

	public void setPreferences(Queue<Integer> preferences) {
		this.preferences = preferences;
	}

	public void setRank(int[] rank) {
		this.rank = rank;
	}
}