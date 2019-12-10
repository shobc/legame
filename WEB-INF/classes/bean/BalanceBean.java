package bean;

import java.io.Serializable;

public class BalanceBean implements Serializable{
    private int moneyTotal;
    private int pointTotal;

    public int getMoneyTotal() {
        return moneyTotal;
    }

    public int getPointTotal() {
        return pointTotal;
    }

    public void setMoneyTotal(int moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    public void setPointTotal(int pointTotal) {
        this.pointTotal = pointTotal;
    }
}