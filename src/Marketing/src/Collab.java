package src.Marketing.src;

import src.Marketing.MarketingDepartment;
import src.Marketing.src.interfaces.IAdvertisement;
import src.Marketing.src.interfaces.ICollab;
import src.Marketing.src.interfaces.ICollabMember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Collab implements ICollab {
    static int nextid = 0;
    int id;

    ICollabMember member;
    ArrayList<DesignAdvertisement> designAdvertisements;
    ArrayList<EventAdvertisement> eventAdvertisements;



    public Collab(int i, ICollabMember member, ArrayList<DesignAdvertisement> designAdvertisements, ArrayList<EventAdvertisement> eventAdvertisements) {
        this.id = i;
        if (id >= nextid) nextid = id + 1;
        this.member = member;
        this.designAdvertisements = designAdvertisements;
        this.eventAdvertisements = eventAdvertisements;
    }

    public Collab(ICollabMember member, ArrayList<DesignAdvertisement> designAdvertisements, ArrayList<EventAdvertisement> eventAdvertisements) {
        id = nextid;
        nextid++;
        this.member = member;
        this.designAdvertisements = designAdvertisements;
        this.eventAdvertisements = eventAdvertisements;
    }

    public Collab(ICollabMember member) {
        id = nextid;
        nextid++;
        this.member = member;
        this.designAdvertisements = new ArrayList<>();
        this.eventAdvertisements = new ArrayList<>();
    }

    @Override
    public void addAdvertisement(Boolean design, IAdvertisement advert) {
        if(design) {
            designAdvertisements.add((DesignAdvertisement) advert);
        } else {
            eventAdvertisements.add((EventAdvertisement) advert);
        }
    }

    @Override
    public void removeAdvertisement(Boolean design, IAdvertisement advert) {
        if(design) {
            designAdvertisements.remove((DesignAdvertisement) advert);
        } else {
            eventAdvertisements.remove((EventAdvertisement) advert);
        }
    }

    @Override
    public ArrayList<Integer> getAdvertisementIds(Boolean design) {
        ArrayList<Integer> tmp = new ArrayList<>();
        if(design) {
            for(IAdvertisement advert: designAdvertisements) {
                tmp.add(advert.getId());
            }
        } else {
            for(IAdvertisement advert: eventAdvertisements) {
                tmp.add(advert.getId());
            }
        }
        return tmp;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public ICollabMember getMember() {
        return member;
    }

    @Override
    public String toString() {
        return "\nCollab " + id + ":" + "\n Member: " + member.getName() +
                "\n DesignAdverts: " + getAdvertisementIds(true) +
                "\n EventAdverts: " + getAdvertisementIds(false);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> brandDetails = new HashMap<>();
        brandDetails.put("id", Integer.toString(this.id));
        brandDetails.put("member", Integer.toString(this.member.getId()));
        brandDetails.put("designAdverts", this.getAdvertisementIds(true).toString());
        brandDetails.put("eventAdverts", this.getAdvertisementIds(false).toString());
        return brandDetails;
    }

    public static Collab parse(Map<String, String> collab) {
        String[] elements = collab.get("designAdverts").equals("[]") ? new String[0] : collab.get("designAdverts").replaceAll("[\\[\\] ]", "").split(",");
        ArrayList<DesignAdvertisement> designAdverts = new ArrayList<>();
        for (String element : elements) {
            designAdverts.add(MarketingDepartment.fileManager.getDesignAdvertById(Integer.parseInt(element)));
        }


        elements = collab.get("eventAdverts").equals("[]") ? new String[0] : collab.get("eventAdverts").replaceAll("[\\[\\] ]", "").split(",");
        ArrayList<EventAdvertisement> eventAdverts = new ArrayList<>();
        for (String element : elements) {
            eventAdverts.add(MarketingDepartment.fileManager.getEventAdvertById(Integer.parseInt(element)));
        }

        return new Collab(
                Integer.parseInt(collab.get("id")),
                MarketingDepartment.fileManager.getBrandById(Integer.parseInt(collab.get("member"))),
                designAdverts,
                eventAdverts
        );
    }

}
