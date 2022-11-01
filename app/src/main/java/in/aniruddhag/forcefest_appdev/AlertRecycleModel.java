package in.aniruddhag.forcefest_appdev;

public class AlertRecycleModel {
    String sTopic, sDescription, sTime;
    Integer iItemType;

    public AlertRecycleModel(String sTopic, String sDescription, String sTime, Integer iItemType) {
        this.sTopic = sTopic;
        this.sDescription = sDescription;
        this.sTime = sTime;
        this.iItemType = iItemType;
    }

    public String getsTopic() {
        return sTopic;
    }

    public String getsDescription() {
        return sDescription;
    }

    public String getsTime() {
        return sTime;
    }

    public Integer getiItemType() {
        return iItemType;
    }
}
