package Design;

public enum DesignType{;

    private final String typeName;
    private final String description;

    DesignType(String typeName, String description) {
        this.typeName = typeName;
        this.description = description;
    }
    public String getTypeName() {
        return typeName;
    }
    public String getDescription() {
        return description;
    }


}
