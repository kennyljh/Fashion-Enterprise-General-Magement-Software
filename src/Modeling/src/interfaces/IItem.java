package src.Modeling.src.interfaces;

import src.Modeling.src.Team;

import java.util.Map;

public interface IItem {
    int getId();

    Team getAssociatedTeam();

    String getItemType();

    String getItemName();

    String getItemLocation();

    Map<String, String> toMap();
}
