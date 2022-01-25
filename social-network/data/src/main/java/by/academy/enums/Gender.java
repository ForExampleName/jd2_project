package by.academy.enums;

public enum Gender {
    MALE("Мужской"), FEMALE("Женский");

    private static final String MALE_DEFINITION = "Мужской";
    private static final String FEMALE_DEFINITION = "Женский";

    private final String definition;

    Gender(String definition) {
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }

    public static Gender getGenderByDefinition(String definition) {
        switch (definition) {
            case MALE_DEFINITION:
                return Gender.MALE;
            case FEMALE_DEFINITION:
                return Gender.FEMALE;
            default:
                return null;
        }
    }
}
