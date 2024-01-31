package ec.edu.espe.icecreamdeve.model;

import com.google.gson.reflect.TypeToken;
import static ec.edu.espe.icecreamdeve.model.Product.scan;
import ec.edu.espe.icecreamdeve.utils.UseJson;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Carlos Hernandez, Mateo Iza, Juan Granda, Josue Guayasamin
 */
public class ReportOfBusiness {

    private float entry;
    private float sale;
    private float profits;

    public ReportOfBusiness(float entry, float sale, float profits) {
        this.entry = entry;
        this.sale = sale;
        this.profits = profits;
    }

    public static float createEntrys(ArrayList<SaleNote> saleNotes, int yearReport, int monthReport) {
        float entry = 0;
        int monthReportLimit = monthReport + 1;
        Date dateYearReport = new Date(yearReport - 1900, monthReport, 0);
        Date dateLimit = new Date(yearReport - 1900, monthReportLimit, 0);
        for (SaleNote currentEntry : saleNotes) {
            Date dateOfSale = currentEntry.getDate();
            if (dateOfSale.after(dateYearReport) && dateOfSale.before(dateLimit)) {
                entry += currentEntry.getTotalValue();
            }
        }
        return entry;
    }

    public static float createSales(ArrayList<Invoice> invoices, int yearReport, int monthReport) {
        float sales = 0;
        int monthReportLimit = monthReport + 1;
        Date dateYearReport = new Date(yearReport - 1900, monthReport, 0);
        Date dateLimit = new Date(yearReport - 1900, monthReportLimit, 0);
        for (Invoice currentInvoice : invoices) {
            Date dateOfSale = currentInvoice.getDateI();
            if (dateOfSale.after(dateYearReport) && dateOfSale.before(dateLimit)) {
                sales += currentInvoice.getValue();
            }
        }
        return sales;
    }

    public static ReportOfBusiness createAReport(ArrayList<ReportOfBusiness> reports, ArrayList<SaleNote> saleNotes, ArrayList<Invoice> invoices) {
        System.out.println("Type the year that you want to see the report(In numbers)=");
        int yearReport = scan.nextInt();
        scan.nextLine();
        System.out.println("Type the month that you want to see the report(In numbers)=");
        int monthReport = scan.nextInt();
        scan.nextLine();
        float entry = createEntrys(saleNotes, yearReport, monthReport);
        float sale = createSales(invoices, yearReport, monthReport);
        float profit = entry - sale;
        return new ReportOfBusiness(entry, sale, profit);
    }

    public static void menuReportBusinness(ArrayList<ReportOfBusiness> reports) {
        UseJson<ReportOfBusiness> jsonUtilReport = new UseJson<>();
        reports = jsonUtilReport.readFile("reportdata.json", new TypeToken<ArrayList<ReportOfBusiness>>() {
        }.getType());

        UseJson<SaleNote> jsonUtilSaleNotes = new UseJson<>();
        ArrayList<SaleNote> saleNotes = jsonUtilSaleNotes.readFile("salenotedata.json", new TypeToken<ArrayList<SaleNote>>() {
        }.getType());

        UseJson<Invoice> jsonUtilInvoice = new UseJson<>();
        ArrayList<Invoice> invoices = jsonUtilInvoice.readFile("Invoicedata.json", new TypeToken<ArrayList<Invoice>>() {
        }.getType());

        int optionReport;
        while (true) {
            System.out.println("///////////Report////////");
            System.out.println("1.Get a new Report");
            System.out.println("2.Show the Reports");
            System.out.println("3.Return to the main menu");

            if (scan.hasNextInt()) {
                optionReport = scan.nextInt();
                scan.nextLine();  
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();  
                continue;  
            }

            switch (optionReport) {
                case 1:
                    reports.add(createAReport(reports, saleNotes, invoices));
                    jsonUtilReport.writeFile("reportdata.json", reports);
                    break;
                case 2:
                    System.out.println("Products" + reports);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * @return the entry
     */
    public float getEntry() {
        return entry;
    }

    /**
     * @param entry the entry to set
     */
    public void setEntry(float entry) {
        this.entry = entry;
    }

    /**
     * @return the sale
     */
    public float getSale() {
        return sale;
    }

    /**
     * @param sale the sale to set
     */
    public void setSale(float sale) {
        this.sale = sale;
    }

    /**
     * @return the profits
     */
    public float getProfits() {
        return profits;
    }

    /**
     * @param profits the profits to set
     */
    public void setProfits(float profits) {
        this.profits = profits;
    }

    @Override
    public String toString() {
        return "ReportOfBusiness" + "-entry=" + entry + ", sale=" + sale + ", profits=" + profits + '\n';
    }

}
