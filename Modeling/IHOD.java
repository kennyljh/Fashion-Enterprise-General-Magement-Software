package Modeling;

public interface IHOD {
    void haveEvent(Boolean type);

    Boolean requestAdvertisement(Event event);

    Boolean requestContract(String celebrity);

    Boolean requestCollab(String brand);
}

class HOD implements IHOD {

    @Override
    public void haveEvent(Boolean type) {
        Event event = new Event();
    }

    @Override
    public Boolean requestAdvertisement(Event event) {
        return null;
    }

    @Override
    public Boolean requestContract(String celebrity) {
        return null;
    }

    @Override
    public Boolean requestCollab(String brand) {
        return null;
    }
}
