package program1;
/**
 * Class for stable matching.
 * A stable matching is a matching between people and pets such that there are no two entities
 * that prefer each other over their current match.
 * Functionality includes reading input from a file, matching people and pets, and printing the matches.
 * @author Sumit Hotchandani
 */
import java.io.*;
import java.util.*;

public class StableMatching {
	
	private int n;
	private ArrayList<Person> people;
	private ArrayList<Pet> pets;

	/** 
	 * function to read preferences from a file.
	 * pre: names are read from file and stored in an arraylist.
	 * post: preferences are read from file and stored in an arraylist
	*/
	private void readPreferences(Scanner scanner, ArrayList<? extends Entity> entities) {
		for (int i = 0; i < n; i++) {		
			int[] rank = new int[n+1];
			Queue<Integer> preferences = new LinkedList<>();
			
			for (int j = 1; j <= n; j++) {
					int p = scanner.nextInt();
					preferences.offer(p);
					rank[p] = j;
			}
			
			entities.get(i).setPreferences(preferences);
			entities.get(i).setRank(rank);
		}
	}

	/** 
	 * function to read input from a file.
	 * pre: none
	 * post: input is read from a file and stored in instance variables.
	*/
	private void readInputFromFile(String filename) {
		try {
				Scanner scanner = new Scanner(new File(filename));
				this.n = scanner.nextInt();

				// Read people and their preferences
				this.people = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					String name = scanner.next();
					this.people.add(new Person(i+1, name, n));
				}
				readPreferences(scanner, this.people);

				// Read pets and their preferences
				this.pets = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					String name = scanner.next();
					this.pets.add(new Pet(i+1, name, n));
				}
				readPreferences(scanner, this.pets);

				scanner.close();
		} catch (FileNotFoundException e) {
				System.err.println("Error opening file: " + filename);
				System.exit(1);
		}
	}

	/**
	 * function to match people and pets using the Gale-Shapley algorithm.
	 * pre: preferences have been read from file and stored in instance variables.
	 * post: people and pets are matched. 
	 */
	private void match() {
		Queue<Person> unmatchedPeople = new LinkedList<>(this.people);
		
		while (!unmatchedPeople.isEmpty()) {
			Person person = unmatchedPeople.poll();
			
			int petIndex = person.getPreferences().poll();
			Pet pet = this.pets.get(petIndex - 1);
			
			if (pet.getMatch() == -1) {
				person.setMatch(petIndex);
				pet.setMatch(person.getId());
			} else {
				int currentMatch = pet.getMatch();
				if (pet.getRank(person.getId()) < pet.getRank(currentMatch)) {
					person.setMatch(petIndex);
					pet.setMatch(person.getId());
					unmatchedPeople.offer(this.people.get(currentMatch - 1));
				} else {
					unmatchedPeople.offer(person);
				}
			}
		}
	}

	/**
	 * function to print the matches.
	 * pre: matches have been set for all people and pets.
	 * post: matches are printed.
	 */
	private void printMatches() {
		for (Person person : this.people) {
			System.out.println(person.getName() + " / " + this.pets.get(person.getMatch() - 1).getName());
		}
	}

	/**
	 * main function.
	 * pre: none
	 * post: stable matching class is instantiated, matches are made and printed.
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		StableMatching sm = new StableMatching();
		sm.readInputFromFile("program1/program1data.txt");
		sm.match();
		sm.printMatches();
		System.out.println("Time = " + (System.currentTimeMillis() - startTime));
	}
}