/**
 * @author Sam Gumm
 */
public enum Department {
    ENGINEERING,
    MARKETING,
    HUMAN_RESOURCES,
    FINANCE,
    DESIGN,
    MODELING,
    MANUFACTURING;

    @Override
    public String toString() {
        switch (this) {
            case ENGINEERING: return "Engineering";
            case MARKETING: return "Marketing";
            case HUMAN_RESOURCES: return "Human Resources";
            case FINANCE: return "Finance";
            case DESIGN: return "Design";
            case MODELING: return "Modeling";
            case MANUFACTURING: return "Manufacturing";
            default: throw new IllegalArgumentException();
        }
    }
}
