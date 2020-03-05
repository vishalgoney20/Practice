import edu.duke.*;
import org.apache.commons.csv.*;

public class FirstCsvCode
{
 public static void main(String[] args)
 {
    FileResource fr=new FileResource();
    CSVParser parser=fr.getCSVParser();
    for(CSVRecord record : parser){
     System.out.println(record.get("Name")+ " ");
     System.out.println(record.get("Food")+ " ");
     System.out.println(record.get("Colour")+ " ");
    }
 }
}
