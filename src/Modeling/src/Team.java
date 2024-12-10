package src.Modeling.src;

public enum Team {
    MODELING,
    MAKEUP,
    CLOTHING,
    STORAGE;

    public String toString() {
        return switch (this) {
            case MODELING -> "Modeling";
            case MAKEUP -> "Makeup";
            case CLOTHING -> "Clothing";
            case STORAGE -> "Storage";
        };
    }

    public static Team parseTeam(String team) {
        return switch (team) {
            case "Modeling" -> MODELING;
            case "Makeup" -> MAKEUP;
            case "Clothing" -> CLOTHING;
            case "Storage" -> STORAGE;
            default -> throw new IllegalArgumentException();
        };
    }
}
