package kurtsanor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalAsessment {

	public static void main(String[] args) {
		boolean options = true;
		while (options) { // loop that prompts the user to enter an option
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				userOptions(); // Shows what the user wants to do
				String userinput = reader.readLine();
				if (userinput.equals("1")) {
					view_employee_details(); // Views employee details
				} 
				else if (userinput.equals("2")) {
					attendance(); // Views login/logout of employees
				}
				else if (userinput.equals("3")) {
					calculate_net_and_gross_salary(); // Calculates gross/net salary
				}
				else if (userinput.equals("4")) {
					options = false;// Exits the program
				}
				else {
					System.out.println("Try again");
				}
				System.out.println("====== Terminated ======");
			}

			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void userOptions() {
		System.out.println("========================");
		System.out.println("***** Initializing *****");
		System.out.println("========================");
		System.out.println("1. View Employee Details");
		System.out.println("2. Login/Logout");
		System.out.println("3. Calculate gross/net salary");
		System.out.println("4. Exit");
		System.out.print("Choose a number: ");
	}

	public static void view_employee_details() {
		String file = "src\\details.csv";
		BufferedReader reader = null;
		String line = "";

		try {
			reader = new BufferedReader(new FileReader(file));
			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

			// asks the user to input employee number
			System.out.print("Enter employee number: ");
			String userinput = read.readLine();
			boolean found = false;

			// reads the csv
			while ((line = reader.readLine()) != null) {
				String replace = line.replaceAll(",(?!(([^\"]*\"){2})*[^\"]*$)", ";x;");
				String[] row = replace.split(",");


				// if userinput matches with the employee number, it displays employee information
				if (userinput.equals(row[0])) {
					found = true;
					System.out.println("Name: " + row[1] + ", " + row[2]);
					System.out.println("Birthdate: " + row[3]);
					System.out.println("Address: " + row[4].replace(";x;", ",").replace("\"", ""));
					System.out.println("Phone number: " + row[5]);
					System.out.println("SSS: " + row[6]);
					System.out.println("Philhealth: " + row[7]);
					System.out.println("TIN: " + row[8]);
					System.out.println("Pag-ibig: " + row[9]);
					System.out.println("Status: " + row[10]);
					System.out.println("Position: " + row[11]);
					System.out.println("Immediate Supervisor: " + row[12].replace(";x;", ",").replace("\"", ""));
					System.out.println("Basic Salary: ₱" + row[13].replace(";x;", ",").replace("\"", ""));
					System.out.println("Rice Salary: ₱" + row[14].replace(";x;", ",").replace("\"", ""));
					System.out.println("Phone allowance: ₱" + row[15].replace(";x;", ",").replace("\"", ""));
					System.out.println("Clothing allowance: ₱" + row[16].replace(";x;", ",").replace("\"", ""));
					System.out.println("Gross semi-monthly rate: ₱" + row[17].replace(";x;", ",").replace("\"", ""));
					System.out.println("Hourly Rate: ₱" + row[18]);

				}

			}
			if (!found) {
				System.out.println("Employee not found");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public static void attendance() {

		String file = "src\\attendance.csv";
		BufferedReader reader = null;
		String line = "";
		try {

			reader = new BufferedReader(new FileReader(file));
			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
			// asks for employee number
			System.out.print("Enter employee number: ");
			String userinput = read.readLine();
			boolean found = false;
			//reads csv
			while ((line = reader.readLine()) != null) {

				String[] row = line.split(",");
				// if userinput matches with employee number, it displays employee timeIn/timeOut
				if (userinput.equals(row[0])) {
					found = true;

					System.out.print("Employee number: " + row[0]);
					System.out.print("     Date: " + row[1]);
					System.out.println("");
					System.out.print("Time-in: " + row[2]);

					System.out.println("              Time-out: " + row[3]);
					double time_in = Double.parseDouble(row[2].replace(":", "."));
					double time_out = Double.parseDouble(row[3].replace(":", "."));
					double total_hours = time_out - time_in;
					System.out.println("Hours worked: " + total_hours);
					System.out.println("==================================================================");

				}

			}
			if (!found) {
				System.out.println("Employee not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void calculate_net_and_gross_salary() {
		String file = "src\\attendance.csv";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));

			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("Enter employee number: ");
			String userinput = read.readLine();

			System.out.print("Enter month(MM) to calculate (01-12): ");
			String Month = read.readLine();

			boolean found = false;
			double total_hours = 0;
			
			String line = "";
			// reads the csv
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				
				/* if user input and the date matched, it will display total hours worked
				  and will automatically calculate gross and net salary*/			 
					if (userinput.equals(row[0]) && row[1].startsWith(Month)) {
					// if the user does not follow date format it will display "INVALID"
					if (Month.equals("0")||Month.equals("1")||Month.equals("2")||Month.equals("3")
							||Month.equals("4")||Month.equals("5")||Month.equals("6")
							||Month.equals("7")||Month.equals("8")||Month.equals("9")) {
							System.out.println("Invalid. Follow date format (MM)");
							return;
					}
					found = true;

					System.out.println("Employee number: " + row[0]);
					System.out.println("Date: " + row[1]);
					System.out.println("Time-in: " + row[2]);
					System.out.println("Time-out: " + row[3]);

					double timein = Double.parseDouble(row[2].replace(":", "."));
					double timeout = Double.parseDouble(row[3].replace(":", "."));
					double hoursworked = timeout - timein;

					System.out.printf("Hours worked: %.0f hours", hoursworked);
					System.out.println();
					System.out.println("=============================================");

					total_hours = total_hours + hoursworked;
					

				}
			}

			if (!found) {
				System.out.println("Error! follow proper format");
				return;
			}
			else {
				System.out.printf("Total hours worked in the month: %.0f hours", total_hours);
				System.out.println();
			}

			String filepath = "src\\details.csv";
			BufferedReader reader2 = new BufferedReader(new FileReader(filepath));
			double total_worked_hours = total_hours;
			
			boolean employee_found = false;
			// reads csv
			while ((line = reader2.readLine()) != null) {
				String replace = line.replaceAll(",(?!(([^\"]*\"){2})*[^\"]*$)", ";x;");
				String[] rows = replace.split(",");
				// calculates gross pay
				if (userinput.equals(rows[0])) {
					employee_found = true;
					System.out.println("Name: " + rows[1] + ", " + rows[2]);
					System.out.println("Hourly rate: ₱" + rows[18]);
					double hourly_salary = Double.parseDouble(rows[18]);
					double monthly_pay = hourly_salary * total_worked_hours;
					System.out.printf("Gross Monthly Pay: ₱%.2f", monthly_pay);
					System.out.println();
					double weekly_pay = monthly_pay / 4;
					System.out.printf("Gross Weekly Pay: ₱%.2f", weekly_pay);
					System.out.println();
					System.out.println();
				
					
					// calculates net pay with deductions
					double salary = monthly_pay;
					System.out.println("====== Calculating Net Salary ======");
					System.out.printf("Monthly Salary: %.2f", salary);
					System.out.println();
					double SSS = 0;
					if (salary >= 24750) {
						SSS = 1125.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 24250 && salary < 24750) {
						SSS = 1102.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 23750 && salary <= 24250) {
						SSS = 1080.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 23250 && salary <= 23750) {
						SSS = 1057.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 22750 && salary <= 23250) {
						SSS = 1035.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 22250 && salary <= 22750) {
						SSS = 1012.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 21750 && salary <= 22250) {
						SSS = 990.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 21250 && salary <= 21750) {
						SSS = 967.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 20750 && salary <= 21250) {
						SSS = 945.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 20250 && salary <= 20750) {
						SSS = 922.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 19750 && salary <= 20250) {
						SSS = 900.00;
						System.out.println("SSS Deduction: " + (SSS));
					} 
					else if (salary >= 19250 && salary <= 19750) {
						SSS = 877.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 18750 && salary <= 19250) {
						SSS = 855.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 18250 && salary <= 18750) {
						SSS = 832.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 17750 && salary <= 18250) {
						SSS = 810.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 17250 && salary <= 17750) {
						SSS = 787.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 16750 && salary <= 17250) {
						SSS = 765.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 16250 && salary <= 16750) {
						SSS = 742.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 15750 && salary <= 16250) {
						SSS = 720.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 15250 && salary <= 15750) {
						SSS = 697.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 14750 && salary <= 15250) {
						SSS = 675.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 14250 && salary <= 14750) {
						SSS = 652.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 13750 && salary <= 14250) {
						SSS = 630.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 13250 && salary <= 13750) {
						SSS = 607.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 12750 && salary <= 13250) {
						SSS = 585.00;
						System.out.println("SSS Deduction: " + (SSS));
					} 
					else if (salary >= 12250 && salary <= 12750) {
						SSS = 562.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 11750 && salary <= 12250) {
						SSS = 540.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 11250 && salary <= 11750) {
						SSS = 517.50;
						System.out.println("SSS Deduction: " + (SSS));
					} 
					else if (salary >= 10750 && salary <= 11250) {
						SSS = 495.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 10250 && salary <= 10750) {
						SSS = 472.50;
						System.out.println("SSS Deduction: " + (SSS));
					} 
					else if (salary >= 9750 && salary <= 10250) {
						SSS = 450.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 9250 && salary <= 9750) {
						SSS = 427.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 8750 && salary <= 9250) {
						SSS = 405.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 8250 && salary <= 8750) {
						SSS = 382.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 7750 && salary <= 8250) {
						SSS = 360.00;
						System.out.println("SSS Deduction: " + (SSS));
					} 
					else if (salary >= 7250 && salary <= 7750) {
						SSS = 337.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 6750 && salary <= 7250) {
						SSS = 315.00;
						System.out.println("SSS Deduction: " + (SSS));
					} 
					else if (salary >= 6250 && salary <= 6750) {
						SSS = 292.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 5750 && salary <= 6250) {
						SSS = 270.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 5250 && salary <= 5750) {
						SSS = 247.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 4750 && salary <= 5250) {
						SSS = 225.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 4250 && salary <= 4750) {
						SSS = 202.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 3750 && salary <= 4250) {
						SSS = 180.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary >= 3250 && salary <= 3750) {
						SSS = 157.50;
						System.out.println("SSS Deduction: " + (SSS));
					}
					else if (salary < 3250) {
						SSS = 135.00;
						System.out.println("SSS Deduction: " + (SSS));
					}
					// ======================= End of SSS deduction
					// =============================================
					double Philhealth = 0;
					if (salary <= 10000) { // Philhealth Deduction
						Philhealth = 300;
						System.out.printf("Philhealth Deduction: %.0f", (Philhealth) / 2);
						System.out.println();
					}
					else if (salary >= 10000.01 && salary <= 59999.99) {
						Philhealth = salary * 0.03 / 2;
						System.out.printf("Philhealth Deduction: %.0f", Philhealth);
						System.out.println();
					} 
					else if (salary >= 60000) {
						Philhealth = 1800 / 2;
						System.out.printf("Philhealth Deduction: %.0f", Philhealth);
						System.out.println();
					}
					// ======================= End of Philhealth deduction
					// =============================================

					double pagibig = 0; // <----- PAG-IBIG DEDUCTION
					if (salary >= 1000 && salary <= 1500) {
						pagibig = (salary * 0.03);
						if (pagibig >= 100) {
							System.out.println("Pag-ibig Deduction: 100");
						}
						else {
							System.out.println("Pag-ibig Deduction: " + pagibig);
						}

					}
					else if (salary > 1500) {
						pagibig = (salary * 0.04);
						if (pagibig >= 100) {
							System.out.println("Pag-ibig Deduction: 100");
						}

					}
					else if (salary < 1000) {
						System.out.println("Pag-ibig Deduction: 0");
					}
					// ======================= End of Pag-ibig deduction
					// =============================================

					double deductions = SSS + Philhealth + pagibig;
					double total = salary - deductions;

					double Tax = 0;
					if (salary <= 20832) { // Withholding tax
						System.out.println("Withholding Tax: 0 ");
					}
					else if (salary >= 20833 && salary < 33333) {
						Tax = (total - 20833) * 0.2;
						if (Tax <= 0) {
							System.out.println("Withholding Tax: 0");
						}
						else {
							System.out.printf("Withholding Tax: %.2f", Tax);
							System.out.println();
						}
					}
					else if (salary >= 33333 && salary < 66667) {
						Tax = 2500 + (total - 33333) * 0.25;
						System.out.printf("Withholding Tax: %.2f", Tax);
						System.out.println();
					} 
					else if (salary >= 66667 && salary < 166667) {
						Tax = 10833 + (total - 66667) * 0.3;
						System.out.printf("Withholding Tax: %.2f", Tax);
						System.out.println();
					} 
					else if (salary >= 166667 && salary < 666667) {
						Tax = 40833.33 + (total - 166667) * 0.32;
						System.out.printf("Withholding Tax: %.2f", Tax);
						System.out.println();
					}
					else if (salary >= 666667) {
						Tax = 200833.33 + (total - 666667) * 0.35;
						System.out.printf("Withholding Tax: %.2f", Tax);
						System.out.println();
					}
					// ======================= End of Withholding tax
					// =============================================

					double finaldeductions = SSS + Philhealth + pagibig + Tax;
					System.out.println();
					System.out.printf("TOTAL DEDUCTIONS: %.2f ", finaldeductions); // Total deductions.
					System.out.println("");
					double finalsalary = salary - finaldeductions;
					System.out.println("====================================");
					System.out.printf("Net monthly salary is: ₱%.2f", finalsalary);
					System.out.println("");
					System.out.printf("Net weekly salary is: ₱%.2f", finalsalary / 4); // net monthly salary divided by
					// 4 work weeks to get the
					// weekly salary with
					// deductions.
					System.out.println();
				
					
				}

			}

			if (!employee_found) {
				System.out.println("no data found");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
