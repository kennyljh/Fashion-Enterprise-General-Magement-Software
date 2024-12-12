package src.Marketing.src;

import src.Marketing.MarketingDepartment;
import src.Marketing.src.interfaces.ICollabMember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public void changeName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nCollab Member " + id + ":" + "\n Name: " + getName();
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> brandDetails = new HashMap<>();
        brandDetails.put("id", Integer.toString(this.id));
        brandDetails.put("name", this.name);
        brandDetails.put("advertisements", this.advertisements.toString());
        return brandDetails;
    }

    public static Brand parse(Map<String, String> brand) {
        String[] elements = brand.get("advertisements").replaceAll("[\\[\\] ]", "").split(",");
        ArrayList<Integer> list = new ArrayList<>();
        for (String element : elements) {
            list.add(Integer.parseInt(element));
        }

        return new Brand(
                Integer.parseInt(brand.get("id")),
                brand.get("name"),
                list
        );
    }
}
