import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MovieCollection collection = new MovieCollection();
        Scanner scanner = new Scanner(System.in);
        String enteredTitle;
        int choice = 0;

        try {
            collection.readFromFile("/Users/berk/Documents/JAVA 2024/Assignment4/src/SavedMovie");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            e.printStackTrace();
        }

        while (choice != 11) {
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
                    
                    10. Save & Exit""");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        collection.addMovieFromUserInput();
                        collection.displayMovies();
                        break;
                    case 2:
                        System.out.print("Enter movie title you want the new character in: ");
                        enteredTitle = scanner.nextLine();
                        collection.addCharacterToMovie(enteredTitle);
                        collection.displayCharacters();
                        break;
                    case 3:
                        System.out.println("Enter a year to delete the movies made in that year:");
                        int enteredYear = scanner.nextInt();
                        collection.removeMovieByYear(enteredYear);
                        System.out.println("\nAfter removing movies from the year entered:");
                        collection.displayMovies();
                        break;
                    case 4:
                        System.out.print("Enter the movie name you want to delete: ");
                        enteredTitle = scanner.nextLine();
                        collection.removeMovieByTitle(enteredTitle);
                        System.out.println("After removing movies from the title entered:");
                        collection.displayMovies();
                        break;
                    case 5:
                        System.out.print("Enter the character name to delete the movies that contain: ");
                        String enteredCharacter = scanner.next();
                        collection.removeMovieByCharacter(enteredCharacter);
                        System.out.println("After removing movies from the character entered:");
                        collection.displayMovies();
                        break;
                    case 6:
                        collection.displayMovies();
                        System.out.print("Enter movie title: ");
                        enteredTitle = scanner.nextLine();
                        collection.removeCharacterInAMovie(enteredTitle);
                        System.out.println("After removing character from movie: ");
                        collection.displayCharacters();
                        break;
                    case 7:
                        collection.displayMovies();
                        break;
                    case 8:
                        collection.displayCharacters();
                        break;
                    case 9:
                            System.out.println("Enter movie year: ");
                            enteredYear = scanner.nextInt();
                            collection.getMoviesByYear(enteredYear);
                        break;
                    case 10:
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
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number (1-10).");
                scanner.next();
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}