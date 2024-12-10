package src.Marketing.src.interfaces;

import src.HR.src.Employee;

import java.util.Map;

public interface ICollabMember {
    int getId();

    String getName();

    Map<String, String> toMap();
}
