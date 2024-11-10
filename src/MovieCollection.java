import java.io.*;
import java.util.*;

public class MovieCollection extends MonsterMovie {
    public final List<MonsterMovie> movies;
    private final Map<Integer, List<MonsterMovie>> moviesByYear;

    public MovieCollection() {
        this.movies = new ArrayList<>();
        this.moviesByYear = new HashMap<>();
    }

    public void addMovie(MonsterMovie movie) {
        if (movie == null) {
            System.err.println("Error: Cannot add a null movie.");
            return;
        }

        movies.add(movie);
        int year = movie.getYearReleased();
        moviesByYear.putIfAbsent(year, new ArrayList<>());
        moviesByYear.get(year).add(movie);
    }

    public void addCharacterToMovie() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter movie title you want the new character in:");
            String enteredTitle = scanner.nextLine();

            System.out.print("Enter the number of horror characters: ");
            int numCharacters = scanner.nextInt();
            scanner.nextLine();

            boolean movieFound = false;
            for (MonsterMovie m : movies) {
                if (m.getTitle().equalsIgnoreCase(enteredTitle)) {
                    movieFound = true;
                    for (int x = 0; x < numCharacters; x++) {
                        System.out.println("Enter details for character " + (x + 1) + ":");
                        System.out.print("Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Subtype: ");
                        String subtype = scanner.nextLine();

                        System.out.print("Age: ");
                        int age = scanner.nextInt();

                        System.out.print("Rebirth year: ");
                        int rebirth = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        System.out.print("Vulnerability: ");
                        String vulnerability = scanner.nextLine();

                        HorrorCharacter character = new HorrorCharacter(name, age, subtype, rebirth, vulnerability);
                        m.addCharacter(character);
                    }
                }
            }
            if (!movieFound) {
                System.out.println("Movie with title \"" + enteredTitle + "\" not found.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Invalid input format. Please enter numbers where required.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while adding characters: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addMovieFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter the number of movies: ");
            int numberOfMovies = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < numberOfMovies; i++) {
                System.out.print("Enter movie title " + (i + 1) + " (don't use more than a word): ");
                String title = scanner.nextLine();

                System.out.print("Enter year released: ");
                int yearReleased = scanner.nextInt();
                scanner.nextLine();

                MonsterMovie movie = new MonsterMovie(title, yearReleased);
                addMovie(movie);
                System.out.println("\nMovie added successfully!");
            }
        } catch (InputMismatchException e) {
            System.err.println("Invalid input format. Please enter numbers where required.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while adding movies: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void displayAllMovies() {
        if (movies.isEmpty()) {
            System.out.println("No movies in the collection.\n");
            return;
        }
        System.out.println("Movies in the Collection:");
        for (MonsterMovie movie : movies) {
            System.out.println("Title: " + movie.getTitle() + " || Year Released: " + movie.getYearReleased());
        }
    }

    public void displayCharacters() {
        try {
            for (MonsterMovie movie : movies) {
                movie.displayCharactersInMovie();
                movie.displayCharacterTypeCount();
                movie.displayMostCommonVulnerability();
            }
        } catch (Exception e) {
            System.err.println("An error occurred while displaying characters: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeMovieByTitle(String title) {
        if (title == null || title.isEmpty()) {
            System.err.println("Error: Title cannot be null or empty.");
            return;
        }

        movies.removeIf(movie -> movie.getTitle().equalsIgnoreCase(title));
    }

    public void removeMovieByYear(int year) {
        movies.removeIf(movie -> movie.getYearReleased() == year);
    }

    public void removeMovieByCharacter(String name) {
        if (name == null || name.isEmpty()) {
            System.err.println("Error: Character name cannot be null or empty.");
            return;
        }

        try {
            movies.removeIf(movie -> movie.getHorrorCharacters().removeIf(character -> character.getName().equalsIgnoreCase(name)));
        } catch (Exception e) {
            System.err.println("An error occurred while removing movies by character: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveMovie(String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (MonsterMovie movie : movies) {
                bufferedWriter.write(movie.getTitle() + ", " + movie.getYearReleased());
                for (HorrorCharacter character : movie.getHorrorCharacters()) {
                    bufferedWriter.write(", " + character.getName() + ", " + character.getAge() +
                            ", " + character.getSubtype() + ", " + character.getRebirth() + ", " + character.getVulnerability());
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.err.println("Error saving movie collection to file: " + e.getMessage());
            throw e;
        }
    }

    public void readFromFile(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] objects = line.split(", ");

                if (objects.length == 7) {
                    try {
                        String title = objects[0];
                        int yearReleased = Integer.parseInt(objects[1]);
                        String name = objects[2];
                        int age = Integer.parseInt(objects[3]);
                        String subtype = objects[4];
                        int rebirth = Integer.parseInt(objects[5]);
                        String vulnerability = objects[6];

                        MonsterMovie movie = new MonsterMovie(title, yearReleased);
                        movie.addCharacter(new HorrorCharacter(name, age, subtype, rebirth, vulnerability));
                        addMovie(movie);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number format in line: " + line);
                    }
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            throw e;
        }
    }
}