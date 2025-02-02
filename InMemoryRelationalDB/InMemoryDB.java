import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryDB {
    public static void main(String[] args) {
        // Create a database    
        Database db = new Database("TestDB");

        // Create a table
        List<Column> columns = new ArrayList<>();
        columns.add(new Column("EmployeeName", DATATYPE.STRING));
        columns.add(new Column("Age", DATATYPE.INT));
        columns.forEach(column -> System.out.println(column.toString()));

        db.createTable("Employee", columns);
        db.printTable("Employee");

        // Insert a row
        Map<String, Object> columnData = new HashMap<>();
        columnData.put("EmployeeName", "John");
        columnData.put( "Age", 25);

        db.insertRow("Employee", columnData);
        db.printTable("Employee");

        // Insert another row
        Map<String, Object> columnData2 = new HashMap<>();
        columnData2.put("EmployeeName", "Jane");
        columnData2.put("Age", 30);

        db.insertRow("Employee", columnData2);

        // insert another row
        Map<String, Object> columnData3 = new HashMap<>();
        columnData3.put("EmployeeName", "Doe");
        columnData3.put("Age", 35);

        db.insertRow("Employee", columnData3);
        db.printTable("Employee");

        
        // Alter table
        db.alterTable("Employee", ALTER_OPERATION.ADD, "Salary", Optional.of(DATATYPE.INT), Optional.empty());
        System.out.println("Printing table after adding column");
        db.printTable("Employee");    

        
        // Alter table
        db.alterTable("Employee", ALTER_OPERATION.RENAME, "EmployeeName", Optional.empty(), Optional.of("Name"));
        System.out.println("Printing table after Renaming column");
        db.printTable("Employee");


        // Alter table
        db.alterTable("Employee", ALTER_OPERATION.DROP, "Age", Optional.empty(), Optional.empty());
        System.out.println("Printing table after dropping column");
        db.printTable("Employee");
    }
    
}