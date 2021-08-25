package cn.chairc.blog.entity.statistics;

/**
 * @author chairc
 * @date 2021/5/24 16:27
 */
public class StatisticsAdminEntity {

    private String currentVisitNumberInWeek;
    private String currentActiveDataInWeek;
    private String currentMessageAlerts;
    private String informationScaleMapNumber;

    public String getCurrentVisitNumberInWeek() {
        return currentVisitNumberInWeek;
    }

    public void setCurrentVisitNumberInWeek(String currentVisitNumberInWeek) {
        this.currentVisitNumberInWeek = currentVisitNumberInWeek;
    }

    public String getCurrentActiveDataInWeek() {
        return currentActiveDataInWeek;
    }

    public void setCurrentActiveDataInWeek(String currentActiveDataInWeek) {
        this.currentActiveDataInWeek = currentActiveDataInWeek;
    }

    public String getCurrentMessageAlerts() {
        return currentMessageAlerts;
    }

    public void setCurrentMessageAlerts(String currentMessageAlerts) {
        this.currentMessageAlerts = currentMessageAlerts;
    }

    public String getInformationScaleMapNumber() {
        return informationScaleMapNumber;
    }

    public void setInformationScaleMapNumber(String informationScaleMapNumber) {
        this.informationScaleMapNumber = informationScaleMapNumber;
    }

    @Override
    public String toString() {
        return "StatisticsAdminEntity{" +
                "currentVisitNumberInWeek='" + currentVisitNumberInWeek + '\'' +
                ", currentActiveDataInWeek='" + currentActiveDataInWeek + '\'' +
                ", currentMessageAlerts='" + currentMessageAlerts + '\'' +
                ", informationScaleMapNumber='" + informationScaleMapNumber + '\'' +
                '}';
    }
}
