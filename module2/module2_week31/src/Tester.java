
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println("\nTesting LogEntry class\n"+le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile();
        logAnalyzer.printAll();
        System.out.print("\nThe no of unique ip addresses are : ");
        int countOfIp=logAnalyzer.countUniqueIPs();
        System.out.print(countOfIp+"\n");
        System.out.println("\nPrinting the Log entries with status codes that are greater than number ");
        logAnalyzer.printAllHigherThanNum(200);
        ArrayList<String> uniqueIpVisits;
        System.out.println("Testing unique Ip visits on day Sep 30");
        uniqueIpVisits=logAnalyzer.uniqueIPVisitsOnDay("Sep 30");
        for(String s:uniqueIpVisits)
        {
            System.out.println(s);
        }
        System.out.println("Testing unique Ip visits on day Sep 14");
        uniqueIpVisits=logAnalyzer.uniqueIPVisitsOnDay("Sep 14");
        for(String s:uniqueIpVisits)
        {
            System.out.println(s);
        }
        System.out.println("\nTesting short-test_log file on countUniqueIPsInRange i.e, status code range");
        System.out.println("Between 200 and 299 are "+logAnalyzer.countUniqueIPsInRange(200,299));
        System.out.println("Testing countUniqueIPsInRange i.e, status code range");
        System.out.println("Between 300 and 399 are "+logAnalyzer.countUniqueIPsInRange(300,399));
        logAnalyzer.tester();
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.testLogAnalyzer();
    }
}
