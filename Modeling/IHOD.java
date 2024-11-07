package Modeling;

import HR.src.Employee;

public interface IHOD {
    void haveEvent(Boolean type, String celebrity, Boolean collab);

    Boolean requestAdvertisement(Event event);

    Boolean requestContract(String celebrity);

    Boolean requestCollab(String brand);
}

class HOD implements IHOD {

    Manager manager = new Manager();

    @Override
    public void haveEvent(Boolean type, String celebrity, Boolean collab) {
        Employee[] models = manager.getModels();

        Event event = new Event();
        event.addEvent(1,models, type, celebrity, collab);
        requestAdvertisement(event);
    }

    @Override
    public Boolean requestAdvertisement(Event event) {
        return false;
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
