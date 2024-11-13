import java.io.*;
import java.util.*;

public class MovieCollection extends MonsterMovie {
    public final List<MonsterMovie> movies;
    private final Map<Integer, List<MonsterMovie>> moviesByYear;
    Scanner scanner = new Scanner(System.in);

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
                        scanner.nextLine(); // Consume the newline character

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
            scanner.nextLine();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while adding characters: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addMovieFromUserInput() {
        try {
            System.out.print("Enter the number of movies: ");
            int numberOfMovies = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < numberOfMovies; i++) {
                System.out.print("Enter movie title " + (i + 1) + ": ");
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

    public void displayAllMoviesWithCharacters() {
        try {
            if (movies.isEmpty()) {
                System.out.println("No movies in the collection.\n");
                return;
            }
            System.out.println("Movies in the Collection:");
            for (MonsterMovie movie : movies) {
                System.out.println("Title: " + movie.getTitle() + " || Year Released: " + movie.getYearReleased());
            }
            for (MonsterMovie movie : movies) {
                movie.sortedCharacters();
                movie.displayCharacterTypeCount();
                movie.displayMostCommonVulnerability();
            }
        }
        catch (Exception e) {
            System.err.println("An error occurred while displaying movies: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void displayMovies(){
        try {
            if (movies.isEmpty()) {
                System.out.println("No movies in the collection.\n");
                return;
            }
            System.out.println("Movies in the Collection:");
            for (MonsterMovie movie : movies) {
                System.out.println("Title: " + movie.getTitle() + " || Year Released: " + movie.getYearReleased());
            }
        }
        catch (Exception e) {
            System.err.println("An error occurred while displaying movies: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void displayCharacters() {
        try {
            for(MonsterMovie movie : movies) {
                movie.sortedCharacters();
            }
        } catch (Exception e) {
            System.err.println("An error occurred while displaying characters: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeMovieByTitle(String enteredTitle) {
        try {

            if (enteredTitle.isEmpty()) {
                System.err.println("Error: Title cannot be null or empty.");
            }
            else movies.removeIf(movie -> movie.getTitle().contains(enteredTitle));
        }
        catch(InputMismatchException e){
            System.err.println("Invalid input format. Please enter numbers where required.");
            scanner.nextLine();
        }
        catch (Exception e) {
            System.err.println("An error occurred while removing movie: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeMovieByYear(int enteredYear) {
        try {
            movies.removeIf(movie -> movie.getYearReleased() == enteredYear);
        }
        catch(InputMismatchException e){
            System.err.println("Invalid input format. Please enter numbers where required.");
            scanner.nextLine();
        }
        catch (Exception e) {
            System.err.println("An error occurred while removing movie: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeMovieByCharacter(String enteredCharacter) {
        try {
            if(enteredCharacter.isEmpty()) {
                System.err.println("Error: Character name cannot be null/empty.");
            }
            else {
                movies.removeIf(movie -> {
                    return movie.getHorrorCharacters().removeIf(character -> character.getName().equals(enteredCharacter));
                });
            }
            /*
               for(MonsterMovie movie : movies) {
                   for(HorrorCharacter character : movie.getHorrorCharacters()){
                       if(character.getName().equals(newName)){
                           movies.remove(movie);
                           removeMovieByCharacter(character.getName());
                       }
                   }
               }
             */
        }
        catch(InputMismatchException e){
            System.err.println("Invalid input format. Please enter numbers where required.");
            scanner.nextLine();
        }
        catch (Exception e) {
            System.err.println("An error occurred while removing movies by character: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void removeCharacterInAMovie(String enteredTitle) {
        try {
            for (MonsterMovie movie : movies) {
                if(!movie.getTitle().isEmpty()) {
                    movie.sortedCharacters();
                    if (movie.getTitle().equalsIgnoreCase(enteredTitle)) {
                        System.out.print("Enter the character name to delete character: ");
                        String name = scanner.nextLine();
                        for (HorrorCharacter character : movie.getHorrorCharacters()) {
                            if (character.getName().equals(name)) {
                                movie.removeCharacter(character);
                            }
                        }
                    }
                }
                else System.out.println("Error: Movie name cannot be null/empty.");
            }
        }
        catch(InputMismatchException e){
            System.err.println("Invalid input format. Please enter numbers where required.");
            scanner.nextLine();
        }
        catch (Exception e) {
            System.err.println("An error occurred while removing character in movie: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveMovie(String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write("Title || Year Released || Name || Age || Subtype || Rebirth || Vulnerability\n\n");
            for (MonsterMovie movie : movies) {
                for (HorrorCharacter character : movie.getHorrorCharacters()) {
                    bufferedWriter.write(movie.getTitle() + " || " + movie.getYearReleased() + " || " + character.getName() + " || " + character.getAge() +
                            " || " + character.getSubtype() + " || " + character.getRebirth() + " || " + character.getVulnerability());
                    bufferedWriter.append("\n");
                }
                bufferedWriter.append("\n");
            }

        }
        catch(InputMismatchException e){
            System.err.println("Invalid input format. Please enter numbers where required.");
            scanner.nextLine();
        }
        catch (IOException e) {
            System.err.println("Error saving movie collection to file: " + e.getMessage());
            throw e;
        }
    }

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
        }
        catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            throw e;
        }
    }

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
        }
        catch(InputMismatchException e){
            System.err.println("Invalid input format. Please enter numbers where required.");
            scanner.nextLine();
        }
        catch (Exception e) {
            System.err.println("An error occurred while adding movie: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}