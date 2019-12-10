package bean;

import java.io.Serializable;

public class PropertyBean implements Serializable{
    private String user_id;
    private int money;
    private int point;
    private String history;
    private String historydate;
    private String RandomString;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getHistorydate() {
        return historydate;
    }

    public void setHistorydate(String historydate) {
        this.historydate = historydate;
    }

    public String getRandomString() {
        return RandomString;
    }

    public void setRandomString(String randomString) {
        RandomString = randomString;
    }
}