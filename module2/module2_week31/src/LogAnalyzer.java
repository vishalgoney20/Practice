
/**
 * Write a description of class LogAnalyzer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.File;
import java.util.*;

import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile() {
        FileResource fr = new FileResource();
        for (String s : fr.lines()) {
            LogEntry logEntry = WebLogParser.parseEntry(s);
            records.add(logEntry);
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIps = new ArrayList<String>();
        for (LogEntry logEntry : records) {
            String ip = logEntry.getIpAddress();
            if (!uniqueIps.contains(ip))
                uniqueIps.add(ip);
        }
        return uniqueIps.size();
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry logEntry : records) {
            if (logEntry.getStatusCode() > num) {
                System.out.println(logEntry);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIps = new ArrayList<String>();
        for (LogEntry logEntry : records) {
            Date date = logEntry.getAccessTime();
            String convertedDate = date.toString();
            if (convertedDate.substring(4, 10).equals(someday) && !(uniqueIps.contains(logEntry.getIpAddress())))
                uniqueIps.add(logEntry.getIpAddress());
        }
        return uniqueIps;
    }

    public int countUniqueIPsInRange(int low, int high) {
        int uniqueIps = 0;
        ArrayList<String> ips = new ArrayList<String>();
        for (LogEntry logEntry : records) {
            if (logEntry.getStatusCode() >= low && logEntry.getStatusCode() <= high && !(ips.contains(logEntry.getIpAddress()))) {
                ips.add(logEntry.getIpAddress());
                uniqueIps += 1;
            }
        }
        return uniqueIps;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> map) {
        int maxValue = 0;
        for (String s : map.keySet()) {
            if (map.get(s) > maxValue)
                maxValue = map.get(s);
        }
        return maxValue;
    }


    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (LogEntry logEntry : records) {
            Date d = logEntry.getAccessTime();
            String convertedDate = d.toString();
            String day = convertedDate.substring(4, 10);
            if (!map.containsKey(day)) {
                ArrayList<String> ipList = new ArrayList<String>();
                ipList.add(logEntry.getIpAddress());
                map.put(day, ipList);
            } else {
                map.get(day).add(logEntry.getIpAddress());
            }
        }
        return map;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
        String maxVisitDay = "";
        int count = 0;
        for (String s : map.keySet()) {
            if (map.get(s).size() > count) {
                count = map.get(s).size();
                maxVisitDay = s;
            }
        }
        return maxVisitDay;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
        ArrayList<String> mostIpVisitList = new ArrayList<String>();
        int maxValue = mostNumberVisitsByIP(map);
        for (String s : map.keySet()) {
            if (map.get(s).equals(maxValue))
                mostIpVisitList.add(s);
        }
        return mostIpVisitList;
    }
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry logEntry : records) {
            String ip = logEntry.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }

    public HashMap<String, Integer> countVisitsPerIPFromList(ArrayList<String> ips) {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (String ip : ips) {
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }


    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String someday) {
        ArrayList<String> mostIpList = new ArrayList<String>();
        HashMap<String, Integer> counts = countVisitsPerIPFromList(map.get(someday));
        int maxValue = mostNumberVisitsByIP(counts);
        for (String s : counts.keySet()) {
            if (counts.get(s) == maxValue)
                mostIpList.add(s);
        }
        return mostIpList;
    }


    public void tester() {
        HashMap<String, ArrayList<String>> daysMapIp;
        daysMapIp = iPsForDays();
        System.out.println("\n\nIp's for a particular day");
        for (String day : daysMapIp.keySet()) {
            System.out.println("Day is " + day);
            System.out.println("Ip's list is as follows");
            for (String ip : daysMapIp.get(day)) {
                System.out.println(ip);
            }
            System.out.println("\n");
        }

        System.out.println("\nThe day with most ip visits is " + dayWithMostIPVisits(daysMapIp));
        System.out.println("\nTesting on weblog3-short_log");
        System.out.println("Ip's with most visits on day Sep 30 are ");
        ArrayList<String> ipList = iPsWithMostVisitsOnDay(daysMapIp, "Sep 30");
        for (String s : ipList) {
            System.out.println(s);
        }

    }
}
