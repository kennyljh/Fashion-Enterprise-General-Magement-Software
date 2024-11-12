package src.Modeling.src;

public enum Team {
    MODELING,
    MAKEUP,
    HAIR,
    CLOTHING;

    public String toString() {
        switch (this) {
            case MODELING: return "Modeling";
            case MAKEUP: return "Makeup";
            case HAIR: return "Hair";
            case CLOTHING: return "Clothing";
            default: throw new IllegalArgumentException();
        }
    }
}
