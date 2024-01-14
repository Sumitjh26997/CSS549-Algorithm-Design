package program1;

import java.util.Queue;

public abstract class Entity {
	private String name;
	private Queue<Integer> preferences;
	private int match;
	private int[]	rank;

	public Entity (String name, Queue<Integer> preferences) {
		this.name = name;
		this.preferences = preferences;
		this.match = -1;
		this.rank = new int[preferences.size()];
	}
}
