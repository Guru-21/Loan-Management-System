import java.util.ArrayList;
import java.util.List;

public class Loan {

    private String adminUsername;
    private String customerUsername;
    private double principalAmount;
    private double rate;
    private int timeInYear;
    private List<EMI> emiPaymentList;


    public Loan(String adminUsername, String customerUsername, double principalAmount, double rate, int timeInYear) {
        this.adminUsername = adminUsername;
        this.customerUsername = customerUsername;
        this.principalAmount = principalAmount;
        this.rate = rate;
        this.timeInYear = timeInYear;
        this.emiPaymentList = new ArrayList<>();
        calculateEMI();
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public double getRate() {
        return rate;
    }

    public int getTimeInYear() {
        return timeInYear;
    }

    public List<EMI> getEmiPaymentList() {
        return emiPaymentList;
    }


    private void calculateEMI(){
        double interest = (principalAmount * rate * timeInYear)/100;
        double totalAmount = principalAmount + interest;

        double emiPerMonths = totalAmount / (timeInYear * 12);

        for(int i =0 ; i< timeInYear*12; i++){
            boolean isLastEMI = (i == timeInYear *12 );
            emiPaymentList.add(new EMI(emiPerMonths, isLastEMI));
        }

    }

}
