package src.Marketing.src.interfaces;

import src.HR.src.Employee;

import java.util.Map;

public interface ICollabMember {
    int getId();

    String getName();

    void addAdvertisement(int id);

    void removeAdvertisement(Integer id);

    void changeName(String name);

    Map<String, String> toMap();
}
