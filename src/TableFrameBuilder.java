import javax.swing.*;
import java.util.List;

public class TableFrameBuilder {
    private TableFrameBuilder instance;
    public JFrame createTableFrame(Object[][] rows, List<String> columsNames) {
        final JFrame frame = new JFrame();
        final var table = new JTable(rows, columsNames.toArray());
        frame.add(table);
        frame.add(new JScrollPane(table));
        return frame;
    }

    public TableFrameBuilder getInstance() {
        if (instance == null) {
            instance = new TableFrameBuilder();
        }
        return instance;
    }
}
