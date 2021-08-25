package cn.chairc.blog.entity.statistics;

/**
 * @author chairc
 * @date 2021/8/13 23:14
 */
public class StatisticsDataResultSet {
    private String objectName = "";
    private String objectNumber = "";

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectNumber() {
        return objectNumber;
    }

    public void setObjectNumber(String objectNumber) {
        this.objectNumber = objectNumber;
    }

    @Override
    public String toString() {
        return "StatisticsDataResultSet{" +
                "objectName='" + objectName + '\'' +
                ", objectNumber='" + objectNumber + '\'' +
                '}';
    }
}
