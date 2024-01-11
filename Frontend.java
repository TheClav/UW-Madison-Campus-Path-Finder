import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is the frontend for a Pathfinder app
 *
 */
public class Frontend implements FrontendInterface {

  private BackendInterface backend;
  private Scanner scanner;
  private boolean shouldExit;

  /**
   * Constructor for the frontend
   * 
   * @param backend - an instance of Backend
   * @param scanner - an instance of Scanner(System.in)
   */
  public Frontend(BackendInterface backend, Scanner scanner) {
    this.backend = backend;
    this.scanner = scanner;
    this.shouldExit = false;
  }

  /**
   * as long as the user doesn't exit the app, this will run the mainMenu on loop
   */
  @Override
  public void startMainCommandLoop() {
    while (!shouldExit) {
      mainMenu();
    }
  }

  /**
   * calls readFile on backend and prints messages to the user depending on if they entered a valid
   * file
   */
  @Override
  public void loadDataFile(String file) {
    try {
      backend.readData(file);
      System.out.println("File loaded successfully!");
      System.out.println();
    } catch (Exception e) {
      System.out.println("Failed to load the file.");
      System.out.println();
    }

  }

  /**
   * The main menu of the frontend, prompting users to pick an option.
   */
  @Override
  public void mainMenu() {
    int choice = 0;
    boolean isValid = false;

    // Until a valid input is entered, the main menu will continue to be printed
    do {
      System.out.println("Menu:");
      System.out.println("1. Load data file");
      System.out.println("2. Show statistics about the dataset");
      System.out.println("3. Find shortest path between two buildings");
      System.out.println("4. Exit");
      System.out.println("Enter your choice: ");

      if (scanner.hasNextInt()) {
        choice = scanner.nextInt();
        isValid = true;
        // If the user enters something other than a number, an error message is printed
      } else {
        System.out.println("Invalid input. Please enter a valid number.");
        System.out.println();
        scanner.nextLine();
      }
    } while (!isValid);
    scanner.nextLine(); // skip the newLine character


    // Calls other methods based on user input
    switch (choice) {
      case 1:
        System.out.println("Enter a valid file name");
        String fileName = scanner.nextLine();
        loadDataFile(fileName);
        break;
      case 2:
        showStatistics();
        break;
      case 3:
        System.out.print("Enter the starting building: ");
        String start = scanner.nextLine();

        System.out.print("Enter the destination building: ");
        String destination = scanner.nextLine();

        displayShortestPath(start, destination);

        break;
      case 4:
        exit();
        break;
      // if an invalid number is entered, print error message
      default:
        System.out.println("Invalid choice. Please enter a valid option.");
        System.out.println();
    }
  }

  /**
   * This method prints the statistics of the graph to the user
   */
  @Override
  public void showStatistics() {
    System.out.println("Statistics:");
    System.out.println(backend.getDataSummary());
    System.out.println("(Each path to and from a building is only considered one path)");
    System.out.println();

  }

  /**
   * Displays the shortest path between two buildings, including the time between each building and
   * the total time
   */
  @Override
  public void displayShortestPath(String start, String destination) {

    try {
      PathInterface path = backend.getShortestRoute(start, destination);
      
      List<String> buildings = path.shortestPath();

      List<Double> walkingTimes = path.walkingTimes();

      // Print each building and time between that are returned from backend
      for (int i = 0; i < buildings.size() - 1; i++) {

        System.out.println("From: " + buildings.get(i) + ", To: " + buildings.get(i + 1) + " takes "
            + walkingTimes.get(i) + " seconds.");

      }

      // print total walking time
      System.out.println("Total walking time from " + start + " to " + destination + ": "
          + path.pathCost() + " seconds.");
      System.out.println();
    } catch (NoSuchElementException e) {
      // If the buildings the user inputs are invalid, print an error message
      System.out.println("No such path exists. Please try again.");
      System.out.println();
      return;
    }

  }

  /**
   * Exits the app
   */
  @Override
  public void exit() {
    System.out.println("Exiting the Pathfinder. Goodbye!");
    this.shouldExit = true;
  }

  public static void main(String[] args) {

    Backend backend = new Backend(new DijkstraGraph<String, Double>(new PlaceholderMap<>()));
    Scanner scanner = new Scanner(System.in);
    Frontend frontend = new Frontend(backend, scanner);
    frontend.startMainCommandLoop();

  }

}
