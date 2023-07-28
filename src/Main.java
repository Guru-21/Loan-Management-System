import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Loan Management System!");
        LoanManagementSystem loanManagementSystem = new LoanManagementSystem();

        while(true){
            System.out.println("--------------------------------------");
            System.out.println("Please Enter the below choice : ");
            System.out.println("1. Add User");
            System.out.println("2. Create Loan");
            System.out.println("3. Make EMI Payment");
            System.out.println("4. Fetch Loan Details by Customer");
            System.out.println("5. Fetch all Loan Details by admin");
            System.out.println("6. Exit");
            System.out.println("--------------------------------------");

            int option = scanner.nextInt();

            switch(option){

                case 1:
                    System.out.println("Enter Username : ");
                    String username = scanner.next();
                    System.out.println("Is user is an admin(true/false) ? ");
                    boolean isAdmin = scanner.nextBoolean();

                    loanManagementSystem.addUser(username, isAdmin);
                    break;


                case 2:
                    System.out.println("Enter admin username : ");
                    String adminUsername = scanner.next();
                    System.out.println("Enter Customer Username : ");
                    String customerUsername = scanner.next();
                    System.out.println("Enter principal Amount : ");
                    double principalAmount = scanner.nextDouble();
                    System.out.println("Enter Rate of interest : ");
                    double roi = scanner.nextDouble();
                    System.out.println("Enter tenure of Loan in year : ");
                    int time = scanner.nextInt();

                    loanManagementSystem.createLoan(adminUsername,customerUsername,principalAmount, roi, time);
                    break;


                case 3:
                    System.out.println("Enter Username for customer");
                    String customerForEMIPayment =  scanner.next();
                    System.out.println("Enter EMI Number : ");
                    int emiNumber = scanner.nextInt();

                    loanManagementSystem.makeEmiPayments(customerForEMIPayment, emiNumber);
                    break;

                case 4:
                    System.out.println("Enter Customer username : ");
                    String customerForLoanInfo = scanner.next();

                    Loan customerLoanInfo = loanManagementSystem.getLoanInfo(customerForLoanInfo);

                    if(customerLoanInfo!=null){
                        System.out.println("Loan info for customer : "+ customerForLoanInfo);
                        System.out.println("Admin username : " + customerLoanInfo.getAdminUsername());
                        System.out.println("Principal Amount : " + customerLoanInfo.getPrincipalAmount());
                        System.out.println("Rate of interest : " + customerLoanInfo.getRate() +"%");
                        System.out.println("Tenure for Loan : " + customerLoanInfo.getTimeInYear() + " Years");
                        System.out.println("--------------------------------------");
                        List<EMI> emiList = customerLoanInfo.getEmiPaymentList();
                        int emiNum =1;
                        for(EMI emi: emiList){
                            System.out.println("EMI #"+ emiNum + " : "+String.format("%.2f", emi.getAmount()) + ":  Paid(" +(emi.isPaid()?"Yes":"No") + ")");
                            emiNum++;
                        }
                    }else{
                        System.out.println("No Loan found for Customer Username : " +customerForLoanInfo );
                    }
                    break;


                case 5:
                    System.out.println("Enter admin username : ");
                    String adminUsernameForLoan = scanner.next();
                    List<Loan> allLoanInfo = loanManagementSystem.getAllLoanInfo(adminUsernameForLoan);

                    if(allLoanInfo !=null){
                        int loanNum = 1;
                        System.out.println("Loan Admin username :  "+ adminUsernameForLoan);
                        for (Loan loan: allLoanInfo){
                            System.out.println("--------------------------------------");
                            System.out.println("Loan #"+ loanNum);
                            System.out.println("Customer Username : " + loan.getCustomerUsername());
                            System.out.println("Principal Amount : "+loan.getPrincipalAmount());
                            System.out.println("Rate of interest : " + loan.getRate() +"%");
                            System.out.println("Tenure for Loan : " + loan.getTimeInYear() + "Years");
                            loanNum ++;
                        }

                    }else{
                        System.out.println("No Loan found for admin username : "+ adminUsernameForLoan);
                    }
                    break;

                case 6:
                    System.out.println("Exiting Loan Management System. Thank You!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Please Enter a valid input.");

            }

        }
    }


}