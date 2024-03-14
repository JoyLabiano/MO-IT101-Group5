import java.io.BufferedReader; // pair with file reader to read csv
import java.io.FileReader; // para mabasa ung csv
import java.io.IOException; // error trapping
import java.util.Scanner; // para enter employee number

public class payrollSystem {
	
   private static final String EMPLOYEE_DETAILS_FILE = "src\\MotorPH Employee Details.csv";
   private static final String ATTENDANCE_RECORD_FILE = "src\\MotorPH Attendance Record.csv";
     
   public static void main(String[] args) {
       // Step 1: Prompt for employee ID
       String employeeId = promptEmployeeId();
       // Step 2: Read employee details from the CSV file
       String[] employeeDetails = readEmployeeDetails(employeeId);
       if (employeeDetails != null) {
           // Step 3: Display employee details
           displayEmployeeDetails(employeeDetails);
           // Step 4: Display attendance record
           displayAttendanceRecord(employeeId);
       } else {
           System.out.println("Employee not found.");
       }
   }
   private static String promptEmployeeId() {
       try (Scanner scanner = new Scanner(System.in)) {
           System.out.print("Enter Employee ID: ");
           return scanner.nextLine();
       }
   }
   private static void displayAttendanceRecord(String employeeId) {
       try (BufferedReader br = new BufferedReader(new FileReader(ATTENDANCE_RECORD_FILE))) {
           String line;
           boolean found = false;
           // Read the header row
           String header = br.readLine();
           System.out.println(header); // Print the header
           // Read attendance records
           while ((line = br.readLine()) != null) {
               String[] parts = line.split(",");
               if (parts.length >= 4 && parts[0].equals(employeeId)) {
                   // If employee ID matches, print the attendance record
                   System.out.println(line);
                   found = true;
               }
           }
           if (!found) {
               System.out.println("No attendance record found for employee ID: " + employeeId);
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   private static String[] readEmployeeDetails(String employeeId) {
       String[] employeeDetails = null;
       try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE_DETAILS_FILE))) {
           String line;
           while ((line = br.readLine()) != null) {
               String[] parts = line.split(",");
               if (parts.length >= 19 && parts[0].equals(employeeId)) {
                   employeeDetails = new String[]{parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8], parts[9], parts[10], parts[11], parts[12], parts[13], parts[14], parts[15], parts[16], parts[17], parts[18]};
                   break; // Stop searching once employee details are found
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       return employeeDetails;
   }
   private static void displayEmployeeDetails(String[] employeeDetails) {
       if (employeeDetails != null) {
           System.out.println("Last name: " + employeeDetails[0]);
           System.out.println("First name: " + employeeDetails[1]);
           System.out.println("Birthday: " + employeeDetails[2]);
           System.out.println("Address: " + employeeDetails[3]);
           System.out.println("Phone number:" + employeeDetails[4]);
           System.out.println("Sss number:" + employeeDetails[5]);
           System.out.println("Philhealth number:" + employeeDetails[6]);
           System.out.println("Tin number:" + employeeDetails[7]);
           System.out.println("Pag-ibig:" + employeeDetails[8]);
           System.out.println("Status:" + employeeDetails[9]);
           System.out.println("Position:" + employeeDetails[10]);
           System.out.println("Immediate supervisor:" + employeeDetails[11]);
           System.out.println("Basic salary:" + employeeDetails[12]);
           System.out.println("Rice subsidy:" + employeeDetails[13]);
           System.out.println("Phone allowance:" + employeeDetails[14]);
           System.out.println("Clothing allowance:" + employeeDetails[15]);
           System.out.println("Gross semi-monthly rate:" + employeeDetails[16]);
           System.out.println("Hourly Rate:" + employeeDetails[17]);
           
           double basicSalary = Double.parseDouble(employeeDetails[12]);
           double riceSubsidy = Double.parseDouble(employeeDetails[13]);
           double phoneAllowance = Double.parseDouble(employeeDetails[14]);
           double clothingAllowance = Double.parseDouble(employeeDetails[15]);
         
           // Monthly calculations
           double grossIncomeMonthly = basicSalary + riceSubsidy + phoneAllowance + clothingAllowance;
           double sssDeduction = 1000;
           double philhealthDeduction = 500;
           double pagibigDeduction = 200; // Initialize Pag-IBIG deduction
           double withholdingTaxMonthly = 0.10 * (grossIncomeMonthly - sssDeduction - philhealthDeduction);
           double netSalaryMonthly = grossIncomeMonthly - sssDeduction - philhealthDeduction - withholdingTaxMonthly;
         
           // Weekly calculations (assuming 4 weeks in a month)
           double grossIncomeWeekly = grossIncomeMonthly / 4;
           double sssDeductionWeekly = sssDeduction / 4;
           double philhealthDeductionWeekly = philhealthDeduction / 4;
           double pagibigDeductionWeekly = pagibigDeduction / 4;
           double withholdingTaxWeekly = withholdingTaxMonthly / 4;
           double netSalaryWeekly = netSalaryMonthly / 4;   
           
           System.out.println("**********Deduction**********");
           System.out.println("Monthly Gross Income: " + grossIncomeMonthly);
           System.out.println("Weekly Gross Income: " + grossIncomeWeekly);
           
           System.out.println("Monthly SSS Deduction: " + sssDeduction);
           System.out.println("Weekly SSS Deduction: " + sssDeductionWeekly);
           
           System.out.println("Monthly Philhealth Deduction: " + philhealthDeduction);
           System.out.println("Weekly Philhealth Deduction: " + philhealthDeductionWeekly);
           
           System.out.println("Monthly Pag-IBIG Deduction: " + pagibigDeduction);
           System.out.println("Weekly Pag-IBIG Deduction: " + pagibigDeductionWeekly);
           
           System.out.println("Monthly Withholding Tax: " + withholdingTaxMonthly);
           System.out.println("Weekly Withholding Tax: " + withholdingTaxWeekly);
           
           System.out.println("Monthly Net Salary: " + netSalaryMonthly);
           System.out.println("Weekly Net Salary: " + netSalaryWeekly);
           System.out.println("**********Attendance Record**********");
           
       } else {
           System.out.println("Employee details not found.");
       }
   }
}