package src.Modeling.src;

public enum RecurrenceType {
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY,
    NONE;

    public String toString() {
        return switch (this) {
            case DAILY -> "DAILY";
            case WEEKLY -> "WEEKLY";
            case MONTHLY -> "MONTHLY";
            case YEARLY -> "YEARLY";
            case NONE -> "NONE";
        };
    }

    public String getRecurrence() {
        return switch (this) {
            case DAILY -> "Daily at 9am";
            case WEEKLY -> "Weekly on Mondays at 9am";
            case MONTHLY -> "First of the Month at 9am";
            case YEARLY -> "January 1st at 9am";
            case NONE -> "None";
        };
    }

    public static RecurrenceType parseType(String type) {
        return switch (type) {
            case "DAILY" -> DAILY;
            case "WEEKLY" -> WEEKLY;
            case "MONTHLY" -> MONTHLY;
            case "YEARLY" -> YEARLY;
            case "NONE" -> NONE;
            default -> throw new IllegalArgumentException();
        };
    }
}
