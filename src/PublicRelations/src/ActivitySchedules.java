/**
 * @author Kenny
 */

package src.PublicRelations.src;

import java.util.List;

public class ActivitySchedules {

    private String scheduleID, activityName, activityType, duration, deadline, description,
                    location, status, dateIssued;
    private List<String> planningTeamAssign, reviewTeamAssign;

    public ActivitySchedules(){}

    public ActivitySchedules(String scheduleID, String activityName, String activityType,
                             String duration, String deadline, String description,
                             String location, String status, String dateIssued){

        this.scheduleID = scheduleID;
        this.activityName = activityName;
        this.activityType = activityType;
        this.duration = duration;
        this.deadline = deadline;
        this.description = description;
        this.location = location;
        this.status = status;
        this.dateIssued = dateIssued;
    }

    public String getScheduleID() {
        return scheduleID;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActivityType() {
        return activityType;
    }

    public String getDuration() {
        return duration;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public List<String> getPlanningTeamAssign() {
        return planningTeamAssign;
    }

    public List<String> getReviewTeamAssign() {
        return reviewTeamAssign;
    }

    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public void setPlanningTeamAssign(List<String> planningTeamAssign) {
        this.planningTeamAssign = planningTeamAssign;
    }

    public void setReviewTeamAssign(List<String> reviewTeamAssign) {
        this.reviewTeamAssign = reviewTeamAssign;
    }
}
