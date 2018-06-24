package tanker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Menu {

	static Scanner sc;
	static ArrayList<Node> node;
	static Graph graph;

	public static void menu() {
		System.out.println("Choose one of the following:");
		System.out.println("1. Add city");
		System.out.println("2. Link city");
		System.out.println("3. Display cities");
		System.out.println("4. Create route");
		System.out.println("5. Exit");
		System.out.print(">> ");
	}

	public static void initGraph() {
		graph = new Graph();
		node = new ArrayList<Node>();

	}

	public static void chooseMenu() {
		menu();
		sc = new Scanner(System.in);
		int choose = sc.nextInt();
		switch (choose) {
		case 1:
			addCity();
			break;
		case 2:
			linkCity();
			break;
		case 3:
			displayCities();
			chooseMenu();
			break;
		case 4:
			createRoute();
			break;
		case 5:
			System.exit(0);
			break;
		/*
		 * default: menu();
		 */
		}
	}

	public static void addCity() {
		sc = new Scanner(System.in);
		System.out.print("Enter new city name : ");
		String cityName = sc.nextLine();
		// to do code

		node.add(new Node(cityName));
		graph.addNode(node.get(node.size() - 1));
		System.out.print("\n\n");

		chooseMenu();
	}

	public static void linkCity() {

		System.out.println("Link cities: ");
		displayCities();
		sc = new Scanner(System.in);
		System.out.print("Enter 1st city id:");
		// sc.close;
		int id1 = sc.nextInt();
		System.out.print("Enter 2st city id:");
		int id2 = sc.nextInt();
		System.out.print("Enter distance in km:");
		int distance = sc.nextInt();

		node.get(id1 - 1).addDestination(node.get(id2 - 1), distance);
		node.get(id2 - 1).addDestination(node.get(id1 - 1), distance);

		chooseMenu();
	}

	public static void displayCities() {
		// to do code
		Iterator<Node> it = node.iterator();
		Node no;
		while (it.hasNext()) {
			no = it.next();
			String name = no.getName();
			System.out.println((node.indexOf(no) + 1) + " " + name);
		}
		System.out.println("\n\n");
	}

	public static void createRoute() {
		sc = new Scanner(System.in);
		displayCities();
		System.out.print("Enter start city id:");
		int id1 = sc.nextInt();
		System.out.print("Enter end city id:");
		int id2 = sc.nextInt();
		// find route

		ArrayList<Node> node2;
		Graph graph2 = new Graph();
		graph2 = Dijkstra.calculateShortestPathFromSource(graph, node.get(id1 - 1));

		Node gr = node.get(id2 - 1);
		// System.out.println(gr.getName());
		System.out.print("Suggested route:    ");
		int numberOfShortestPathItems = gr.getShortestPath().size();
		int i = 0;
		for (Node gr2 : gr.getShortestPath()) {
			i = i + 1;

			System.out.print(gr2.getName() + " ");
			if (i <= numberOfShortestPathItems) {
				System.out.print(" ---> ");
			}

		}
		System.out.print(gr.getName());
		System.out.println("");
		// find distance
		System.out.print("Total distance: ");
		System.out.println(gr.getDistance());

		System.out.println("\n\n");
		chooseMenu();
	}

}