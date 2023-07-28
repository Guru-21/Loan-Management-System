import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanManagementSystem {

    Map<String, User> Users;
    List<Loan> loanList;

    public LoanManagementSystem(){
        this.Users = new HashMap<>();
        this.loanList = new ArrayList<>();
    }

    //add User
    public void addUser(String userName, boolean isAdmin){
        User user = new User(userName, isAdmin);
        Users.put(userName, user);
    }

    //create loan for customer
    public void createLoan(String adminUsername, String customerUsername, double principalAmount, double rate, int timeInYear){
        // validate users
        User admin = Users.get(adminUsername);
        if(admin == null || !admin.isAdmin()){
            System.out.println(adminUsername + " is not an admin.");
            return;
        }

        User customer = Users.get(customerUsername);
        if(customer == null || customer.isAdmin()){
            System.out.println(customerUsername + " is not a customer.");
            return;
        }

        Loan loan = new Loan(adminUsername, customerUsername, principalAmount, rate, timeInYear);
        loanList.add(loan);
        System.out.println("Loan Created successfully for customer : "+ customerUsername);

    }


    public void makeEmiPayments(String customerUserName, int emiNumber){

        User customer = Users.get(customerUserName);
        if(customer == null || customer.isAdmin()){
            System.out.println(customerUserName + " is not a customer.");
            return;
        }

        Loan loan = getLoanForCustomer(customerUserName);
        if(loan==null){
            System.out.println("Loan is not found for customer : "+ customerUserName);
            return;
        }

        List<EMI> emiPaymentList = loan.getEmiPaymentList();

        if(emiNumber>=1 && emiNumber<=emiPaymentList.size()){
            EMI emi = emiPaymentList.get(emiNumber -1);
            if(!emi.isPaid()){
                emi.setPaid(true);
                System.out.println("EMI #" + emiNumber + " Paid successfully");
            }else{
                System.out.println("EMI #"+emiNumber + " already paid.");
            }

        }else{
            System.out.println("Please Enter a valid emi number.");
        }

    }


    public Loan getLoanInfo(String customerUsername){
        User customer = Users.get(customerUsername);
        if(customer == null || customer.isAdmin()){
            System.out.println(customerUsername + " is not a customer.");
            return null;
        }

        for(Loan loan: loanList){
            if(loan.getCustomerUsername().equalsIgnoreCase(customerUsername)){
                return loan;
            }
        }
        return null;
    }


    public List<Loan> getAllLoanInfo(String adminUsername){
        User admin = Users.get(adminUsername);
        if(admin == null || !admin.isAdmin()){
            System.out.println(adminUsername + " is not an Admin.");
            return null;
        }

        List<Loan> adminLoanList = new ArrayList<>();
        for(Loan loan:loanList){
            if (loan.getAdminUsername().equalsIgnoreCase(adminUsername)) {
                adminLoanList.add(loan);
            }
        }
        return adminLoanList;
    }

    private Loan getLoanForCustomer(String customerUserName) {
        for(Loan loan: loanList){
            if(loan.getCustomerUsername().equalsIgnoreCase(customerUserName)){
                return loan;
            }
        }
        return null;
    }


}
