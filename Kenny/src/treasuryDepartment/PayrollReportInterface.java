package treasuryDepartment;

public interface PayrollReportInterface {

	double getReportSummary();
	
	double getGrossPay();
	
	double getOvertimePay();
	
	double getBenefitsPay();
	
	double getBasePay();
	
	double getNetPay();
	
	String getdateCreated();
}
