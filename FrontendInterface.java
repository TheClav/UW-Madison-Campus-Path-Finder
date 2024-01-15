public interface FrontendInterface {

    /*
     * a method to start the main command loop
     */
    public void startMainCommandLoop();

    /**
     * Allows user to input commands
     */
    public void mainMenu();

    /**
     * a method that will prompt the user to enter a data file and load it
     */
    public void loadDataFile(String file);

    /**
     * displays statistics about the graph, such as total number of nodes or the sum of all edge
     * weights
     */
    public void showStatistics();

    /**
     * Displays the shortest path between a starting and ending point, while also showing statistics
     * about the path taken
     *
     * @param start - starting place
     * @param destination - name of destination
     */
    public void displayShortestPath(String start, String destination);

    /**
     * Exits the command loop and closes the program
     */
    public void exit();

}
