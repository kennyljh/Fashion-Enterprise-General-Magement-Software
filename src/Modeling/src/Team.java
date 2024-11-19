package src.Modeling.src;

public enum Team {
    MODELING,
    MAKEUP,
    HAIR,
    CLOTHING;

    public String toString() {
        return switch (this) {
            case MODELING -> "Modeling";
            case MAKEUP -> "Makeup";
            case HAIR -> "Hair";
            case CLOTHING -> "Clothing";
        };
    }

    public static Team parseTeam(String team) {
        return switch (team) {
            case "Modeling" -> MODELING;
            case "Makeup" -> MAKEUP;
            case "Hair" -> HAIR;
            case "Clothing" -> CLOTHING;
            default -> throw new IllegalArgumentException();
        };
    }
}
