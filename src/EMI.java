public class EMI {

    private double amount;
    private boolean isPaid;

    public EMI(double amount, boolean isPaid) {
        this.amount = amount;
        this.isPaid = isPaid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
