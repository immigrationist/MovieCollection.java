/**
 * The HorrorCharacter class represents a character in a horror-themed movie or story.
 * Each character has attributes such as name, subtype, age, year of rebirth, and vulnerability.
 * The class implements the Comparable interface to allow sorting by name.
 */
public class HorrorCharacter implements Comparable<HorrorCharacter> {
    private String name;
    private String subtype;
    private int age;
    private int rebirth;
    private String vulnerability;

    /**
     * Constructs a new HorrorCharacter with the specified attributes.
     *
     * @param name         the name of the character (must not be null)
     * @param age          the age of the character (must not be negative)
     * @param subtype      the subtype of the character (must not be null)
     * @param rebirth      the year of rebirth for the character (must not be negative)
     * @param vulnerability the character's vulnerability (must not be null)
     * @throws IllegalArgumentException if any argument violates the constraints
     */
    public HorrorCharacter(String name, int age, String subtype, int rebirth, String vulnerability) {
        setName(name);
        setAge(age);
        setSubtype(subtype);
        setRebirth(rebirth);
        setVulnerability(vulnerability);
    }

    /**
     * Gets the name of the character.
     *
     * @return the name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the character.
     *
     * @param name the new name (must not be null)
     * @throws IllegalArgumentException if the name is null
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.name = name;
    }

    /**
     * Gets the subtype of the character.
     *
     * @return the subtype of the character
     */
    public String getSubtype() {
        return subtype;
    }

    /**
     * Sets the subtype of the character.
     *
     * @param subtype the new subtype (must not be null)
     * @throws IllegalArgumentException if the subtype is null
     */
    public void setSubtype(String subtype) {
        if (subtype == null) {
            throw new IllegalArgumentException("Subtype cannot be null.");
        }
        this.subtype = subtype;
    }

    /**
     * Gets the age of the character.
     *
     * @return the age of the character
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the character.
     *
     * @param age the new age (must not be negative)
     * @throws IllegalArgumentException if the age is negative
     */
    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        this.age = age;
    }

    /**
     * Gets the year of rebirth for the character.
     *
     * @return the year of rebirth
     */
    public int getRebirth() {
        return rebirth;
    }

    /**
     * Sets the year of rebirth for the character.
     *
     * @param rebirth the new rebirth year (must not be negative)
     * @throws IllegalArgumentException if the rebirth year is negative
     */
    public void setRebirth(int rebirth) {
        if (rebirth < 0) {
            throw new IllegalArgumentException("Rebirth year cannot be negative.");
        }
        this.rebirth = rebirth;
    }

    /**
     * Gets the vulnerability of the character.
     *
     * @return the vulnerability of the character
     */
    public String getVulnerability() {
        return vulnerability;
    }

    /**
     * Sets the vulnerability of the character.
     *
     * @param vulnerability the new vulnerability (must not be null)
     * @throws IllegalArgumentException if the vulnerability is null
     */
    public void setVulnerability(String vulnerability) {
        if (vulnerability == null) {
            throw new IllegalArgumentException("Vulnerability cannot be null.");
        }
        this.vulnerability = vulnerability;
    }

    /**
     * Compares this character to another HorrorCharacter based on their names.
     * The comparison is case-insensitive.
     *
     * @param character the HorrorCharacter to compare to (must not be null)
     * @return a negative integer, zero, or a positive integer as this character's name
     *         is lexicographically less than, equal to, or greater than the specified character's name
     * @throws NullPointerException if the specified character is null
     */
    @Override
    public int compareTo(HorrorCharacter character) {
        if (character == null) {
            throw new NullPointerException("Cannot compare to a null character.");
        }
        return this.name.compareToIgnoreCase(character.name);
    }

    /**
     * Returns a string representation of the HorrorCharacter.
     *
     * @return a string describing the character's attributes
     */
    @Override
    public String toString() {
        return "  Name: " + name + " || Age: " + age +
                " || Subtype: " + subtype + " || Rebirth: " + rebirth +
                " || Vulnerability: " + vulnerability;
    }
}