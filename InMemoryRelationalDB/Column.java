public class Column {
    private String columnName;
    private DATATYPE datatype;

    public Column(String columnName, DATATYPE datatype) {
        this.columnName = columnName;
        this.datatype = datatype;
    }

    public String getColumnName() {
        return columnName;
    }
    
    public DATATYPE getDatatype() {
        return datatype;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String toString() {
        return "Column: " + columnName + " Datatype: " + datatype;
    }
}
