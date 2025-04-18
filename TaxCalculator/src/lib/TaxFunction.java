package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
    // Fungsi utama untuk menghitung pajak
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int monthsWorked, int deductible, FamilyStatus familyStatus) {
        validateMonthsWorked(monthsWorked);  // Validasi bulan kerja
        int numberOfChildren = getAdjustedChildrenCount(familyStatus.getNumberOfChildren());  // Penyesuaian jumlah anak
        int yearlyIncome = calculateYearlyIncome(monthlySalary, otherMonthlyIncome, monthsWorked);  // Hitung penghasilan tahunan
        int ptkp = calculateNonTaxableIncome(familyStatus.isMarried(), numberOfChildren);  // Hitung penghasilan tidak kena pajak

        return calculateTaxAmount(yearlyIncome, deductible, ptkp);  // Hitung pajak dan kembalikan
    }

    // Validasi bulan kerja tidak lebih dari 12
    private static void validateMonthsWorked(int monthsWorked) {
        if (monthsWorked > 12) {
            System.err.println("More than 12 months working per year");
        }
    }

    // Menyesuaikan jumlah anak jika lebih dari 3
    private static int getAdjustedChildrenCount(int numberOfChildren) {
        return Math.min(numberOfChildren, MAX_CHILDREN);
    }

    // Hitung penghasilan tahunan
    private static int calculateYearlyIncome(int monthlySalary, int otherMonthlyIncome, int monthsWorked) {
        return (monthlySalary + otherMonthlyIncome) * monthsWorked;
    }

    // Hitung penghasilan tidak kena pajak
    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int ptkp = BASE_PTWP;
        if (isMarried) {
            ptkp += MARRIED_ADDITION;
        }
        ptkp += numberOfChildren * CHILD_ADDITION;
        return ptkp;
    }

    // Hitung jumlah pajak yang harus dibayar
    private static int calculateTaxAmount(int yearlyIncome, int deductible, int ptkp) {
        int taxableIncome = yearlyIncome - deductible - ptkp;
        return Math.max(0, (int) Math.round(0.05 * taxableIncome));
    }
}
