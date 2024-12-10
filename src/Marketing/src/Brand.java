package src.Marketing.src;

import src.HR.src.Department;
import src.HR.src.Employee;
import src.Marketing.MarketingDepartment;
import src.Marketing.src.interfaces.IAdvertisement;
import src.Marketing.src.interfaces.ICollabMember;
import src.Modeling.ModelingDepartment;
import src.Modeling.src.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Brand implements ICollabMember {
    static int nextid = 0;
    int id;

    String name;
    ArrayList<Integer> advertisements;

    public Brand(int i, String name, ArrayList<Integer> advertisements) {
        this.id = i;
        if (id >= nextid) nextid = id + 1;
        this.name = name;
        this.advertisements = advertisements;
    }

    public Brand(String name, ArrayList<Integer> advertisements) {
        id = nextid;
        nextid++;
        this.name = name;
        this.advertisements = advertisements;

        MarketingDepartment.fileManager.addCollabMember(this);
    }

    public Brand(String name) {
        id = nextid;
        nextid++;
        this.name = name;
        this.advertisements = new ArrayList<>();

        MarketingDepartment.fileManager.addCollabMember(this);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String str = "\nCollab Member " + id + ":" + "\n Name: " + getName() +
                "\n Advertisements: " + advertisements;
        return str;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> brandDetails = new HashMap<>();
        brandDetails.put("id", Integer.toString(this.id));
        brandDetails.put("name", this.name);
        brandDetails.put("advertisements", this.advertisements.toString());
        return brandDetails;
    }

    public static Brand parse(Map<String, String> event) {
        String[] elements = event.get("advertisements").replaceAll("[\\[\\] ]", "").split(",");
        ArrayList<Integer> list = new ArrayList<>();
        for (String element : elements) {
            list.add(Integer.parseInt(element));
        }

        return new Brand(
                Integer.parseInt(event.get("id")),
                event.get("name"),
                list
        );
    }
}
