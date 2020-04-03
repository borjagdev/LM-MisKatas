package tdd;

public class Reporter {
    private UserFinder finder;
    private ReportWriter writer;

    public Reporter(UserFinder finder, ReportWriter writer) {
        this.finder = finder;
        this.writer = writer;
    }

    public void generateReportBy(String profile){

    }
}
