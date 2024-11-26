import java.io.*;
import java.util.*;

/**
 * MovieCollection is a class that manages a collection of MonsterMovie objects.
 * It provides functionalities such as adding, removing, and displaying movies and their characters.
 * Additionally, it allows saving and loading the collection to/from a file.
 */
public class MovieCollection {
    // Composition instead of inheritance: a list of MonsterMovie objects
    private final List<MonsterMovie> movies;
    // "Has-a" relationship with a Map for storing movies by their release year
    protected final Map<Integer, List<MonsterMovie>> moviesByYear;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor initializes the movie collection and the moviesByYear map.
     */
    public MovieCollection() {
        this.movies = new ArrayList<>();
        this.moviesByYear = new HashMap<>();
    }

    /**
     * Displays movies from the collection released in a specified year.
     * @param year the year of movies to retrieve
     */
    public void getMoviesByYear(int year) {
        moviesByYear.put(year, new ArrayList<>());
        boolean movieFound = false;
        System.out.println("\nMovies in the Collection:");
        for (MonsterMovie movie : movies) {
            if (movie.getYearReleased() == year) {
                System.out.println("Title: " + movie.getTitle() + " || Year Released: " + movie.getYearReleased());
                movieFound = true;
            }
        }
        if (!movieFound) {
            System.out.println("Movie with year \"" + year + "\" not found.");
        }
    }

    /**
     * Adds a MonsterMovie object to the collection.
     * Adds a movie to the List of MonsterMovie.
     * @param movie the MonsterMovie object to add
     */
    public void addMovie(MonsterMovie movie) {
        if (movie == null) {
            System.err.println("Error: Cannot add a null movie.");
            return;
        }
        movies.add(movie);
    }

    /**
     * Adds characters to a specific movie in the collection.
     * @param enteredTitle the title of the movie to which characters will be added
     */
    public void addCharacterToMovie(String enteredTitle) {
        try {
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

                        System.out.print("Age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Subtype: ");
                        String subtype = scanner.nextLine();

                        System.out.print("Rebirth year: ");
                        int rebirth = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Vulnerability: ");
                        String vulnerability = scanner.nextLine();

                        HorrorCharacter character = new HorrorCharacter(name, age, subtype, rebirth, vulnerability);
                        m.addCharacter(character);
                    }
                }
            }
            if (!movieFound) {
                System.out.println("Movie with title \"" + enteredTitle + "\" not found.\n");
            }
        } catch (InputMismatchException e) {
            System.err.println("Invalid input format. Please enter numbers where required.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while adding characters");
        }
    }

    /**
     * Displays all movies in the collection.
     */
    public void displayMovies() {
        try {
            if (movies.isEmpty()) {
                System.out.println("No movies in the collection.\n");
                return;
            }
            System.out.println("Movies in the Collection:");
            for (MonsterMovie movie : movies) {
                System.out.println("Title: " + movie.getTitle() + " || Year Released: " + movie.getYearReleased());
            }
        } catch (Exception e) {
            System.err.println("An error occurred while displaying movies");
        }
    }

    /**
     * Displays all characters in each movie.
     * Displays character type count.
     * Displays the most common vulnerability in the movie.
     */
    public void displayCharacters() {
        try {
            for(MonsterMovie movie : movies) {
                movie.sortedCharacters();
                movie.displayCharacterTypeCount();
                movie.displayMostCommonVulnerability();
            }
        } catch (Exception e) {
            System.err.println("An error occurred while displaying characters");
        }
    }

    /**
     * Removes movies from the collection based on the title entered by the user.
     * @param enteredTitle the title of the movie to remove
     */
    public void removeMovieByTitle(String enteredTitle) {
        try {
            if (enteredTitle.isEmpty()) {
                System.err.println("Error: Title cannot be null or empty.");
            } else {
                // movie is an instance of a Movie object, for each movie it checks if input matches title
                movies.removeIf(movie -> movie.getTitle().equalsIgnoreCase(enteredTitle));
            }
        } catch (Exception e) {
            System.err.println("An error occurred while removing movie");
        }
    }

    /**
     * Removes movies from the collection based on the year entered by the user.
     * @param enteredYear the year of the movies to remove
     */
    public void removeMovieByYear(int enteredYear) {
        try {
            movies.removeIf(movie -> movie.getYearReleased() == enteredYear);
        } catch(InputMismatchException e){
            System.err.println("Invalid input format. Please enter numbers where required.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred while removing movie");
        }
    }

    /**
     * Removes movies from the collection based on a character's name.
     * If a movie contains a character with the specified name, the movie is removed.
     * @param enteredCharacter the name of the character used to remove movies
     */
    public void removeMovieByCharacter(String enteredCharacter) {
        try {
            if(enteredCharacter.isEmpty()) {
                System.err.println("Error: Character name cannot be null/empty.");
            } else {
                movies.removeIf(movie -> movie.getHorrorCharacters().removeIf(character ->
                        character.getName().equalsIgnoreCase(enteredCharacter)));
            }
        } catch (Exception e) {
            System.err.println("An error occurred while removing movies by character");
        }
    }

    /**
     * Removes a specific character from a movie in the collection based on the movie title.
     * @param enteredTitle the title of the movie containing the character to remove
     */
    public void removeCharacterInAMovie(String enteredTitle) {
        try {
            for (MonsterMovie movie : movies) {
                if (!movie.getTitle().isEmpty()) {
                    if (movie.getTitle().equalsIgnoreCase(enteredTitle)) {
                        displaySpecificCharacter(enteredTitle);
                        System.out.print("Enter the character name to delete character: ");
                        String name = scanner.next();
                        for (HorrorCharacter character : movie.getHorrorCharacters()) {
                            if (character.getName().equalsIgnoreCase(name)) {
                                movie.removeCharacter(character);
                            }
                        }
                    }
                } else {
                    System.out.println("Error: Movie name cannot be null/empty.");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while removing character in movie");
        }
    }

    /**
     * Edits a specific movie from a collection based on the movie title.
     * @param enteredTitle the title of the movie
     */
    public void editAMovie(String enteredTitle) {
        for(MonsterMovie movie : movies) {
            if(movie.getTitle().equalsIgnoreCase(enteredTitle)) {
                System.out.println("\nEditing movie: " + movie.getTitle() + " || Year Released: " + movie.getYearReleased());
                System.out.println("Enter new movie title to change title: ");
                String newTitle = scanner.nextLine();
                if(!newTitle.isEmpty()) {
                    movie.setTitle(newTitle);
                }
                System.out.println("Enter new movie year released to change year: ");
                int newYear = scanner.nextInt();
                if(newYear >= 1895) {
                    movie.setYearReleased(newYear);
                }
                scanner.nextLine();
            }
        }
    }

    /**
     * Edits a specific character from a movie in the collection based on the movie title.
     * @param enteredTitle the title of the movie containing the character to edit
     */
    public void editCharacterInAMovie(String enteredTitle) {
        for (MonsterMovie movie : movies) {
            if (!movie.getTitle().isEmpty()) {
                if (movie.getTitle().equalsIgnoreCase(enteredTitle)) {
                    displaySpecificCharacter(enteredTitle);
                    System.out.print("Enter the character name to edit character: ");
                    String name = scanner.nextLine();
                    for (HorrorCharacter character : movie.getHorrorCharacters()) {
                        if (character.getName().equalsIgnoreCase(name)) {
                            System.out.println("\nEditing character: " + character.getName());
                            System.out.println("Enter character name to change name: ");
                            String newName = scanner.nextLine();
                            if(!newName.isEmpty()) {
                                character.setName(newName);
                            }
                            System.out.println("Enter character age to change age: ");
                            int newAge = scanner.nextInt();
                            if(newAge >= 0){
                                character.setAge(newAge);
                            }
                            scanner.nextLine();
                            System.out.println("Enter character subtype to change subtype: ");
                            String newSubtype = scanner.nextLine();
                            if(!newSubtype.isEmpty()) {
                                character.setSubtype(newSubtype);
                            }
                            System.out.println("Enter character rebirth to change rebirth: ");
                            int newRebirth = scanner.nextInt();
                            if(newRebirth >= 0) {
                                character.setRebirth(newRebirth);
                            }
                            scanner.nextLine();
                            System.out.println("Enter character vulnerability to change vulnerability: ");
                            String newVulnerability = scanner.nextLine();
                            if(!newVulnerability.isEmpty()) {
                                character.setVulnerability(newVulnerability);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Displays a list of characters from a movie in the collection based on the movie title.
     * @param newTitle the title of the movie containing the character(s)
     */
    public void displaySpecificCharacter(String newTitle){
        System.out.println("\nSorted Horror Characters in Movie: " + newTitle.toUpperCase());
        for(MonsterMovie movie : movies){
            if(movie.getTitle().equalsIgnoreCase(newTitle)){
                for(HorrorCharacter character : movie.getHorrorCharacters()){
                    System.out.println(character);
                }
            }
        }
    }

    /**
     * Saves the movie collection to a file.
     * Each movie and its characters are saved in a specific format.
     * @param fileName the name of the file to save the collection to
     * @throws IOException if an I/O error occurs
     */
    public void saveMovie(String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write("Title || Year Released || Name || Age || Subtype || Rebirth || Vulnerability\n\n");
            for (MonsterMovie movie : movies) {
                for (HorrorCharacter character : movie.getHorrorCharacters()) {
                    bufferedWriter.write(movie.getTitle() + " || " + movie.getYearReleased() + " || " +
                            character.getName() + " || " + character.getAge() + " || " + character.getSubtype() +
                            " || " + character.getRebirth() + " || " + character.getVulnerability());
                    bufferedWriter.append("\n");
                }
                bufferedWriter.append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving movie collection to file");
        }
    }

    /**
     * Reads the movie collection from a file and populates the current collection.
     * The file must have a specific format for parsing the data.
     * @param fileName the name of the file to read from
     * @throws IOException if an I/O error occurs
     */
    public void readFromFile(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            bufferedReader.readLine();
            bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] objects = line.split(" \\|\\| ");
                if (objects.length == 7) {
                    String title = objects[0];
                    int yearReleased = Integer.parseInt(objects[1]);
                    String name = objects[2];
                    int age = Integer.parseInt(objects[3]);
                    String subtype = objects[4];
                    int rebirth = Integer.parseInt(objects[5]);
                    String vulnerability = objects[6];

                    MonsterMovie movie = findOrCreateMovie(title, yearReleased);
                    Objects.requireNonNull(movie).addCharacter(new HorrorCharacter(name, age, subtype, rebirth, vulnerability));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file");
        }
    }

    /**
     * Finds a movie in the collection by its title and year. If not found, creates a new movie.
     * @param title the title of the movie
     * @param yearReleased the release year of the movie
     * @return the found MonsterMovie object from the file
     */
    private MonsterMovie findOrCreateMovie(String title, int yearReleased) {
        try {
            for (MonsterMovie movie : movies) {
                if (movie.getTitle().equalsIgnoreCase(title)) {
                    return movie;
                }
            }
            MonsterMovie newMovie = new MonsterMovie(title, yearReleased);
            addMovie(newMovie);
            return newMovie;
        } catch (Exception e) {
            System.out.println("Error");
        }
        return null;
    }
}