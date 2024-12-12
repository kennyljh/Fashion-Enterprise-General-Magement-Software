package src.Marketing.src;

import src.HR.src.Department;
import src.HR.src.Employee;
import src.Marketing.MarketingDepartment;
import src.Marketing.src.interfaces.ICollabMember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Celebrity implements ICollabMember {
    static int nextid = 0;
    int id;

    String name;
    ArrayList<Integer> designAdvertisements;
    ArrayList<Integer> eventAdvertisements;


    Employee tempEmployeeInfo;

    public Celebrity(int i, String name, ArrayList<Integer> advertisements, Employee employee) {
        this.id = i;
        if (id >= nextid) nextid = id + 1;
        this.name = name;
        this.advertisements = advertisements;
        this.tempEmployeeInfo = employee;
    }

    public Celebrity(String name, ArrayList<Integer> advertisements) {
        id = nextid;
        nextid++;
        this.name = name;
        this.advertisements = advertisements;
        this.tempEmployeeInfo = new Employee("Celebrity", name, Department.MODELING, "Temp Worker", "Contract", 1000000);

        MarketingDepartment.fileManager.addCollabMember(this);
    }

    public Celebrity(String name) {
        id = nextid;
        nextid++;
        this.name = name;
        this.advertisements = new ArrayList<>();
        this.tempEmployeeInfo = new Employee("Celebrity", name, Department.MODELING, "Temp Worker", "Contract", 1000000);

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

    public String getEmployeeInfo() {
        return this.tempEmployeeInfo.toString();
    }

    @Override
    public void addAdvertisement(int id) {
        this.advertisements.add(id);
    }

    @Override
    public void removeAdvertisement(Integer id) {
        this.advertisements.remove(id);
    }

    @Override
    public void changeName(String name) {
        this.name = name;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> brandDetails = new HashMap<>();
        brandDetails.put("id", Integer.toString(this.id));
        brandDetails.put("name", this.name);
        brandDetails.put("advertisements", this.advertisements.toString());
        brandDetails.put("contractDetail", this.getEmployeeInfo());
        return brandDetails;
    }

    public static Celebrity parse(Map<String, String> celeb) {
        String[] elements = celeb.get("advertisements").replaceAll("[\\[\\] ]", "").split(",");
        ArrayList<Integer> list = new ArrayList<>();
        for (String element : elements) {
            list.add(Integer.parseInt(element));
        }

        return new Celebrity(
                Integer.parseInt(celeb.get("id")),
                celeb.get("name"),
                list,
                Employee.parseEmployee(celeb.get("contractDetail"))
        );
    }

}
