package program1;
import java.io.*;
import java.util.*;

public class StableMatching {
	private int n;
	private ArrayList<Person> people;
	private ArrayList<Pet> pets;

	private void readInputFromFile(String filename) {
		try {
				Scanner scanner = new Scanner(new File(filename));
				n = scanner.nextInt();
				System.err.println("n = " + n);

				// Read people and their preferences
				people = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					String name = scanner.next();
					System.err.println("name = " + name);
					people.add(new Person(name, n));
				}
				
				for (int i = 0; i < n; i++) {		
					int[] rank = new int[n+1];
					Queue<Integer> preferences = new LinkedList<>();
					for (int j = 1; j <= n; j++) {
							int p = scanner.nextInt();
							preferences.offer(p);
							rank[p] = j;
							System.out.println("rank[" + p + "] = " + j);
							System.err.println("preferences.peek() = " + p);
					}
					people.get(i).setPreferences(preferences);
					people.get(i).setRank(rank);
				}

				// Read pets and their preferences
				pets = new ArrayList<>();
				for(int i = 0; i < n; i++) {
					String name = scanner.next();
					System.err.println("name = " + name);
					pets.add(new Pet(name, n));
				}

				for (int i = 0; i < n; i++) {		
					int[] rank = new int[n+1];
					Queue<Integer> preferences = new LinkedList<>();
					for (int j = 1; j <= n; j++) {
							int p = scanner.nextInt();
							preferences.offer(p);
							rank[p] = j;
							System.out.println("rank[" + p + "] = " + j);
							System.err.println("preferences.peek() = " + p);
					}
					pets.get(i).setPreferences(preferences);
					pets.get(i).setRank(rank);
				}

				scanner.close();
		} catch (FileNotFoundException e) {
				System.err.println("Error opening file: " + filename);
				System.exit(1);
		}
	}

	public static void main(String[] args) {
		StableMatching sm = new StableMatching();
		System.err.println("Running");
		sm.readInputFromFile("program1/program1data.txt");
		System.err.println("Read input");
	}
}