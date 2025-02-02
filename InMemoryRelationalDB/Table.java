import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private Integer tableId;
    private String name;
    private Map<String, Column> columns = new HashMap<>();
    private List<Row> rows = new ArrayList<>();

    public Table(Integer tableId, String name, List<Column> columns) {
        this.tableId = tableId;
        this.name = name;
        for (Column column: columns) {
            this.columns.put(column.getColumnName(), column);
        }
    }

    public Integer getTableId() {
        return tableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns.values().stream().toList();
    }

    public List<Row> getRows() {
        return rows;
    }

    public Column getColumn(String columnName) {
        return columns.get(columnName);
    }

    protected Boolean checkIfColumnExists(String columnName) {
        if (!columns.containsKey(columnName)) {
            System.out.println(columnName + " Column does not exist");
            return false;
        }
        return true;
    }

    public void insertRow(Map<String, Object> columnData) {
        for (String columnName: columnData.keySet()) {
            if (!checkIfColumnExists(columnName)) return;
        }
        Integer rowId = rows.size() + 1;
        Row row = new Row(rowId, columnData);
        rows.add(row);
    }

    public void deleteRow(Integer rowId) {
        rows.removeIf(row -> row.getRowId() == rowId);
    }

    public void addColumn(String Column, DATATYPE datatype) {
        Column column  = new Column(Column, datatype);
        columns.put(column.getColumnName(), column);

        for (Row row: rows) {
            row.getColumnData().put(column.getColumnName(), null);
        }
    }

    public void deleteColumn(String columnName) {
        columns.remove(columnName);
        for (Row row: rows) {
            row.getColumnData().remove(columnName);
        }
    }

    public void renameColumn(String columnName, String newColumnName) {
        Column column = columns.get(columnName);
        column.setColumnName(newColumnName);

        for (Row row: rows) {
            Object value = row.getColumnData().get(columnName);
            row.getColumnData().remove(columnName);
            row.getColumnData().put(newColumnName, value);
        }
    }

    public void delete() {
        rows.clear();
        columns.clear();
    }

    protected void printRecords(List<Row> FilteredRows, List<Column> selectedColumns) {
        System.out.print("\t");
        
        for (Column column: selectedColumns) {
            System.out.println("\t" + column.getColumnName() + "\t");
        }

        for(Row row: FilteredRows) {
            System.out.print("\n\t"+row.getRowId()+".");

            for(Map.Entry<String, Object> entry : row.getColumnData().entrySet()) {
                System.out.print("\t"+entry.getValue()+"\t");
            }
        }
        System.out.print("\n");
    }

    public void printTable() {
        if (rows.isEmpty()) {
            System.out.println("No records found");
            return;
        }
        this.printRecords(rows, columns.values().stream().toList());
    }

    public void filterTableRecordsByColumnValue(String columnName, Object value) {
        if (!checkIfColumnExists(columnName)) return;
        Column column = columns.get(columnName);
        List<Row> filteredRows = new ArrayList<>();
        for (Row row: rows) {
            if (row.getColumnData().get(column).equals(value)) {
                filteredRows.add(row);
            }
        }
        this.printRecords(filteredRows, columns.values().stream().toList());
    }
}
