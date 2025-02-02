import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

enum DATATYPE {
    STRING,
    INT,
    BOOLEAN
}   

enum ALTER_OPERATION {
    ADD,
    DROP,
    RENAME
}

public class Database {
    private static Integer tableCounter = 0;
    private String name;
    private Map<String, Table> tableMap = new HashMap<>();
    

    public Database(String name) {
        this.name = name;
    }

    public Boolean checkIfTableExists(String tableName) {
        if (!tableMap.containsKey(tableName)) {
            System.out.println(tableName + " Table does not exist");
            return false;
        }
        return true;
    }

    public void createTable(String tableName, List<Column> columns) {
        if (checkIfTableExists(tableName)) return;

        Table table = new Table(tableCounter, tableName, columns);
        tableMap.put(tableName, table);
        tableCounter += 1;
    }

    public void deleteTable(String tableName) {
        if (!checkIfTableExists(tableName)) return;

        Table table = tableMap.get(tableName);
        table.delete();
        System.out.println("Successfully deleted table " + tableName);
    }

    public List<Table> getTableList() {
        return tableMap.values().stream().toList();
    }

    public void alterTable(String tableName, ALTER_OPERATION operation, String columnName, Optional<DATATYPE> datatype, Optional<String> newColumnName) {
        if (!tableMap.containsKey(tableName)) {
            System.out.println(tableName + " Table does not exist");
            return;
        }
    
        Table table = tableMap.get(tableName);
    
        switch (operation) {
            case ADD:
                DATATYPE dataTypeValue = datatype.orElseThrow(() -> new IllegalArgumentException("Data Type is required for ADD operation"));
                table.addColumn(columnName, dataTypeValue);
                break;
    
            case DROP:
                table.deleteColumn(columnName);
                break;
    
            case RENAME:
                String newColumn = newColumnName.orElseThrow(() -> new IllegalArgumentException("New column name is required for RENAME operation"));
                table.renameColumn(columnName, newColumn);
                break;
    
            default:
                System.out.println("Invalid ALTER operation");
        }
    }

    public void printTable(String tableName) {
        if (!checkIfTableExists(tableName)) return;

        Table table = tableMap.get(tableName);
        table.printTable();
    }     

    public void filterTableRecordsByColumnValue(String tableName, String columnName, Object value) {
        if (!checkIfTableExists(tableName)) return;

        Table table = tableMap.get(tableName);
        table.filterTableRecordsByColumnValue(columnName, value);
    }

    public void insertRow(String tableName, Map<String, Object> columnData) {
        if (!checkIfTableExists(tableName)) return;

        Table table = tableMap.get(tableName);
        table.insertRow(columnData);
    }

    public void deleteRow(String tableName, Integer rowId) {
        if (!checkIfTableExists(tableName)) return;

        Table table = tableMap.get(tableName);
        table.deleteRow(rowId);
    }

    public List<Row> getRows(String tableName) {
        if (!checkIfTableExists(tableName)) return null;

        Table table = tableMap.get(tableName);
        return table.getRows();
    }

    public Column getColumn(String tableName, String columnName) {
        if (!checkIfTableExists(tableName)) return null;

        Table table = tableMap.get(tableName);
        return table.getColumn(columnName);
    }
}
