public class HorrorCharacter implements Comparable<HorrorCharacter> {

    private String name;
    private String subtype;
    private int age;
    private int rebirth;
    private String vulnerability;

    public HorrorCharacter(String name, int age, String subtype, int rebirth, String vulnerability) {
        setName(name);
        setAge(age);
        setSubtype(subtype);
        setRebirth(rebirth);
        setVulnerability(vulnerability);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.name = name;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        if (subtype == null) {
            throw new IllegalArgumentException("Subtype cannot be null.");
        }
        this.subtype = subtype;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        this.age = age;
    }

    public int getRebirth() {
        return rebirth;
    }

    public void setRebirth(int rebirth) {
        if (rebirth < 0) {
            throw new IllegalArgumentException("Rebirth year cannot be negative.");
        }
        this.rebirth = rebirth;
    }

    public String getVulnerability() {
        return vulnerability;
    }

    public void setVulnerability(String vulnerability) {
        if (vulnerability == null) {
            throw new IllegalArgumentException("Vulnerability cannot be null.");
        }
        this.vulnerability = vulnerability;
    }

    @Override
    public int compareTo(HorrorCharacter character) {
        if (character == null) {
            throw new NullPointerException("Cannot compare to a null character.");
        }
        return this.name.compareToIgnoreCase(character.name);
    }

    @Override
    public String toString() {
        return "  Name: " + name + " || Age: " + age +
                " || Subtype: " + subtype + " || Rebirth: " + rebirth +
                " || Vulnerability: " + vulnerability;
    }
}