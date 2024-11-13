import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MovieCollection collection = new MovieCollection();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        try {
            collection.readFromFile("/Users/berk/Documents/JAVA 2024/Assignment4/src/SavedMovie");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            e.printStackTrace();
        }

        while (choice != 8) {
            System.out.println("\nChoose from the options below" +
                    "\n1. Create a new movie" +
                    "\n2. Add a new character to a movie" +
                    "\n3. Remove movie by year" +
                    "\n4. Remove movie by title" +
                    "\n5. Remove movie by character name" +
                    "\n6. Display all movies" +
                    "\n7. Remove character inside a movie" +
                    "\n8. Save & Exit");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        collection.addMovieFromUserInput();
                        collection.displayAllMovies();
                        break;
                    case 2:
                        collection.addCharacterToMovie();
                        collection.displayCharacters();
                        break;
                    case 3:
                        collection.removeMovieByYear();
                        System.out.println("\nAfter removing movies from the year entered:");
                        collection.displayAllMovies();
                        break;
                    case 4:
                        collection.removeMovieByTitle();
                        System.out.println("After removing movies from the title entered:");
                        collection.displayAllMovies();
                        break;
                    case 5:
                        collection.removeMovieByCharacter();
                        System.out.println("After removing movies from the character entered:");
                        collection.displayAllMovies();
                        break;
                    case 6:
                        collection.displayAllMovies();
                        collection.displayCharacters();
                        break;
                    case 7:
                        collection.removeCharacterInAMovie();
                        System.out.println("After removing character from movie: ");
                        collection.displayCharacters();
                        break;
                    case 8:
                        try {
                            collection.saveMovie("/Users/berk/Documents/JAVA 2024/Assignment4/src/SavedMovie");
                        } catch (IOException e) {
                            System.err.println("Error saving movie collection: " + e.getMessage());
                            e.printStackTrace();
                        }
                        System.out.println("Exiting program. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Please select a valid option (1-7).");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number (1-7).");
                scanner.next();
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}