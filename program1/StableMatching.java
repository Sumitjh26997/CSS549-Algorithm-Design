package program1;
import java.io.*;
import java.util.*;

public class StableMatching {
	private int n;
	private ArrayList<Person> people;
	private ArrayList<Pet> pets;

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

	private void readInputFromFile(String filename) {
		try {
				Scanner scanner = new Scanner(new File(filename));
				this.n = scanner.nextInt();
				System.err.println("n = " + n);

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

	private void printMatches() {
		for (Person person : this.people) {
			System.out.println(person.getName() + " / " + this.pets.get(person.getMatch() - 1).getName());
		}
	}

	public static void main(String[] args) {
		StableMatching sm = new StableMatching();
		sm.readInputFromFile("program1/program1data.txt");
		sm.match();
		sm.printMatches();
	}
}