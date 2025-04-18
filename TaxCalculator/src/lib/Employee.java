package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private PersonalInfo personalInfo;
	
	private LocalDate dateJoined;
	private int monthWorkingInYear;

	public enum IsForeigner{
		LOCAL, FOREIGNER
	}
	public enum Gender {
		MALE, FEMALE
	}
	
	private IsForeigner isForeigner;
	private Gender gender; 

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private Spouse spouse;
	private List<Child> children;
	
	public Employee(PersonalInfo personalInfo, LocalDate dateJoined, IsForeigner isForeigner, Gender gender) {
		this.personalInfo = personalInfo;
		this.dateJoined = dateJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		children = new LinkedList<String>();
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {	
		int salary = switch(grade){
			case 1 -> 3000000;
			case 2 -> 5000000;
			case 3 -> 7000000;
			default -> throw new IllegalArgumentException("Invalid grade: " + grade); 
		}
		
		if(isForeigner == IsForeigner.FOREIGNER){
			salary *= 1.5; 
		}

		this.monthlySalary = salary;
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String name, String idNumber) {
    	this.spouse = new Spouse(name, idNumber);
	}

	
	public void addChild(String name, String idNumber) {
		children.add(new Child(name, idNumber));
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == dateJoined.getYear()) {
			monthWorkingInYear = date.getMonthValue() - dateJoined.getMonthValue;
		}else {
			monthWorkingInYear = 12;
		}
		
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouse.isEmpty(), children.size());
	}
}
