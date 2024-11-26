import java.util.*;

/**
 * The MonsterMovie class represents a movie in the horror genre
 * that contains a collection of HorrorCharacter objects, along with
 * features such as tracking character subtypes and common vulnerabilities.
 */
public class MonsterMovie {

    /**
     * The title of the movie.
     */
    private String title;

    /**
     * The year the movie was released.
     */
    private int yearReleased;

    /**
     * The list of horror characters in the movie.
     */
    private final List<HorrorCharacter> characters;

    /**
     * A map that tracks the count of each subtype of character in the movie.
     */
    private final Map<String, Integer> subtypeCount;

    /**
     * A map that tracks the most common vulnerabilities among the characters.
     */
    private final Map<String, Integer> commonVulnerabilities;

    /**
     * Constructs a MonsterMovie instance with the specified title and release year.
     *
     * @param title         the title of the movie
     * @param yearReleased  the year the movie was released
     */
    public MonsterMovie(String title, int yearReleased) {
        this.title = title;
        this.yearReleased = yearReleased;
        this.characters = new ArrayList<>();
        this.subtypeCount = new HashMap<>();
        this.commonVulnerabilities = new HashMap<>();
    }

    /**
     * Adds a HorrorCharacter to the movie.
     *
     * @param character  the HorrorCharacter to add
     *                   (must not be null; handles null subtypes or vulnerabilities with warnings)
     */
    public void addCharacter(HorrorCharacter character) {
        if (character == null) {
            System.err.println("Error: Null character cannot be added.");
            return;
        }
        try {
            characters.add(character);
            String subtype = character.getSubtype();
            if (subtype != null) {
                subtypeCount.put(subtype, subtypeCount.getOrDefault(subtype, 0) + 1);
            } else {
                System.err.println("Warning: Character has a null subtype.");
            }

            String vulnerabilities = character.getVulnerability();
            if (vulnerabilities != null) {
                commonVulnerabilities.put(vulnerabilities, commonVulnerabilities.getOrDefault(vulnerabilities, 0) + 1);
            } else {
                System.err.println("Warning: Character has null vulnerability.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred while adding the character");
        }
    }

    /**
     * Removes a HorrorCharacter from the movie.
     *
     * @param character  the HorrorCharacter to remove
     */
    public void removeCharacter(HorrorCharacter character) {
        if (character == null) {
            System.err.println("Error: Null character cannot be removed.");
            return;
        }
        try{
            characters.remove(character);
        }
        catch(Exception e){
            System.err.println("An error occurred while removing the character");
        }
    }

    /**
     * Sorts the characters in the movie alphabetically by their natural order and
     * displays them in the console.
     */
    public void sortedCharacters() {
        try {
            Collections.sort(characters);
            System.out.println("\nSorted Horror Characters in Movie: " + title.toUpperCase());
            for (HorrorCharacter character : characters) {
                System.out.println(character);
            }
        } catch (Exception e) {
            System.err.println("An error occurred while displaying characters");
        }
    }

    /**
     * Displays a count of each character subtype in the movie.
     */
    public void displayCharacterTypeCount() {
        try {
            System.out.println("Character types and counts: ");
            for (Map.Entry<String, Integer> subtypeName : subtypeCount.entrySet()) {
                System.out.println(subtypeName.getKey() + " " + subtypeName.getValue());
            }
        } catch (Exception e) {
            System.err.println("An error occurred while displaying character type counts");
        }
    }

    /**
     * Displays the most common vulnerability among the characters.
     * If there is no vulnerability with more than one occurrence, displays a relevant message.
     */
    public void displayMostCommonVulnerability() {
        System.out.println("Most common Vulnerability: ");
        String mostCommonVulnerability = null;
        int maxCount = 0;
        try {
            for (Map.Entry<String, Integer> commonVulnerability : commonVulnerabilities.entrySet()) {
                if (commonVulnerability.getValue() > maxCount) {
                    mostCommonVulnerability = commonVulnerability.getKey();
                    maxCount = commonVulnerability.getValue();
                }
            }
            if (maxCount > 1) {
                System.out.println(mostCommonVulnerability);
            } else {
                System.out.println("No common vulnerability found!!!");
            }
        } catch (Exception e) {
            System.err.println("An error occurred while finding the most common vulnerability");
        }
    }

    /**
     * Gets the title of the movie.
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the movie.
     * @param title the movie title entered to set title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the year the movie was released.
     * @return the release year of the movie
     */
    public int getYearReleased() {
        return yearReleased;
    }

    /**
     * Sets the year released of the movie.
     * @param yearReleased the year released entered to set released year of the movie
     */
    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

    /**
     * Gets the list of horror characters in the movie, sorted alphabetically.
     *
     * @return a sorted list of HorrorCharacter objects
     */
    public List<HorrorCharacter> getHorrorCharacters() {
        Collections.sort(characters);
        return characters;
    }
}