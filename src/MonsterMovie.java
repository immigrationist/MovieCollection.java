import java.util.*;

public class MonsterMovie {
    private String title;
    private int yearReleased;

    private final List<HorrorCharacter> characters;
    private final Map<String, Integer> subtypeCount;
    private final Map<String, Integer> commonVulnerabilities;

    public MonsterMovie(String title, int yearReleased) {
        this.title = title;
        this.yearReleased = yearReleased;
        this.characters = new ArrayList<>();
        this.subtypeCount = new HashMap<>();
        this.commonVulnerabilities = new HashMap<>();
    }

    public MonsterMovie() {
        this.characters = new ArrayList<>();
        this.subtypeCount = new HashMap<>();
        this.commonVulnerabilities = new HashMap<>();
    }

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
            System.err.println("An error occurred while adding the character: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeCharacter(HorrorCharacter character) {
        if (character == null) {
            System.err.println("Error: Null character cannot be removed.");
            return;
        }
        try{
            characters.remove(character);
        }
        catch(Exception e){
            System.err.println("An error occurred while removing the character: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sortedCharacters() {
        try {
            Collections.sort(characters);
            System.out.println("\nSorted Horror Characters in Movie: " + title);
            for (HorrorCharacter character : characters) {
                System.out.println(character);
            }
        } catch (Exception e) {
            System.err.println("An error occurred while displaying characters: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void displayCharacterTypeCount() {
        try {
            System.out.println("\nCharacter types and counts: ");
            for (Map.Entry<String, Integer> subtypeName : subtypeCount.entrySet()) {
                System.out.println(subtypeName.getKey() + " " + subtypeName.getValue());
            }
        } catch (Exception e) {
            System.err.println("An error occurred while displaying character type counts: " + e.getMessage());
            e.printStackTrace();
        }
    }

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
            System.err.println("An error occurred while finding the most common vulnerability: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public List<HorrorCharacter> getHorrorCharacters() {
        Collections.sort(characters);
        return characters;
    }
}