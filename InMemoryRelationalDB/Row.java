import java.util.Map;

public class Row {
    private Integer rowId;
    private Map<String, Object> columnData;

    public Row(Integer rowId, Map<String, Object> columnData) {
        this.rowId = rowId;
        this.columnData = columnData;
    }

    public Integer getRowId() {
        return rowId;
    }

    public Map<String, Object> getColumnData() {
        return this.columnData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Row ID: ").append(rowId).append("\n");
        for (Map.Entry<String, Object> entry: columnData.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
