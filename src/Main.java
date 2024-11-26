import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Main class is the entry point for the Movie Collection Management Program.
 * This program allows users to manage a collection of movies and their associated characters,
 * with features such as adding, removing, displaying, and saving movies and characters.
 * The program interacts with the user through a menu-driven interface.
 */
public class Main {

    /**
     * The main method provides a menu-driven interface for managing a movie collection.
     * Users can perform the following actions:
     * <ul>
     *     <li>Create a new movie</li>
     *     <li>Add a new character to a movie</li>
     *     <li>Remove a movie by year, title, or character name</li>
     *     <li>Remove a character from a movie</li>
     *     <li>Display all movies or characters</li>
     *     <li>Display movies by year</li>
     *     <li>Edit movie title and/or year</li>
     *     <li>Edit a character from a movie</li>
     *     <li>Save the collection and exit</li>
     * </ul>
     * The program handles invalid inputs and ensures data persistence by reading from and saving to a file.
     *
     * @param args command-line arguments (not used in this program)
     */
    public static void main(String[] args) {

        String title;
        MovieCollection collection = new MovieCollection();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        try {
            collection.readFromFile("/Users/berk/Documents/JAVA 2024/Assignment4/src/SavedMovie");
        } catch (IOException e) {
            System.err.println("Error reading from file");
        }

        while (choice != 12) {
            System.out.println("""
                    \nChoose from the options below\
                    
                    1. Create a new movie\
                    
                    2. Add a new character to a movie\
                    
                    3. Remove movie by year\
                    
                    4. Remove movie by title\
                    
                    5. Remove movie by character name\
                    
                    6. Remove character inside a movie\
                    
                    7. Display all movies\
                    
                    8. Display all characters\
                    
                    9. Display movies by year\
                    
                    10. Edit a character in a movie\
                    
                    11. Edit a movie\
                    
                    12. Save & Exit""");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter the number of movies: ");
                        int numberOfMovies = scanner.nextInt();
                        scanner.nextLine();

                        for (int i = 0; i < numberOfMovies; i++) {
                            System.out.print("Enter movie title " + (i + 1) + ": ");
                            title = scanner.nextLine();

                            System.out.print("Enter year released: ");
                            int yearReleased = scanner.nextInt();
                            scanner.nextLine();

                            MonsterMovie movie = new MonsterMovie(title, yearReleased);
                            collection.addMovie(movie);
                            System.out.println("\nMovie added successfully!");
                        }
                        collection.displayMovies();
                        // sleep for 5 seconds
                        Thread.sleep(2000);
                        break;
                    case 2:
                        System.out.print("Enter movie title you want the new character in: ");
                        collection.addCharacterToMovie(scanner.nextLine());
                        collection.displayCharacters();
                        Thread.sleep(5000);
                        break;
                    case 3:
                        System.out.println("Enter a year to delete the movies made in that year:");
                        // takes input to match year of a movie
                        collection.removeMovieByYear(scanner.nextInt());
                        scanner.nextLine();
                        System.out.println("\nAfter removing movies from the year entered:");
                        collection.displayMovies();
                        Thread.sleep(3000);
                        break;
                    case 4:
                        System.out.print("Enter the movie name you want to delete: ");
                        // takes input to match a title of a movie
                        collection.removeMovieByTitle(scanner.nextLine());
                        System.out.println("After removing movies from the title entered:");
                        collection.displayMovies();
                        Thread.sleep(3000);
                        break;
                    case 5:
                        System.out.print("Enter the character name to delete the movies that contain: ");
                        // takes input to match character in movie(s)
                        collection.removeMovieByCharacter(scanner.nextLine());
                        System.out.println("After removing movies from the character entered:");
                        collection.displayMovies();
                        Thread.sleep(3000);
                        break;
                    case 6:
                        collection.displayMovies();
                        System.out.print("Enter movie title: ");
                        // takes input to match a title of a movie
                        title = scanner.nextLine();
                        collection.removeCharacterInAMovie(title);
                        Thread.sleep(5000);
                        break;
                    case 7:
                        // displays all the movies
                        collection.displayMovies();
                        Thread.sleep(3000);
                        break;
                    case 8:
                        // displays all characters with detail
                        collection.displayCharacters();
                        Thread.sleep(3000);
                        break;
                    case 9:
                        System.out.println("Enter movie year: ");
                        // displays movies by the year entered
                        collection.getMoviesByYear(scanner.nextInt());
                        scanner.nextLine();
                        Thread.sleep(3000);
                        break;
                    case 10:
                        System.out.println("Enter movie title");
                        title = scanner.nextLine();
                        collection.editCharacterInAMovie(title);
                        break;
                    case 11:
                        System.out.println("Enter movie title");
                        title = scanner.nextLine();
                        collection.editAMovie(title);
                        break;
                    case 12:
                        try {
                            collection.saveMovie("/Users/berk/Documents/JAVA 2024/Assignment4/src/SavedMovie");
                        } catch (IOException e) {
                            System.err.println("Error saving movie collection");
                        }
                        System.out.println("Exiting program. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number (1-10).");
                scanner.next();
            } catch (Exception e) {
                System.err.println("An unexpected error occurred");
            }
        }
    }
}