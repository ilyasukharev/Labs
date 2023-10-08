import javax.swing.*;

public interface TableViewer {
    default void execute(JFrame jFrame) {
        jFrame.setSize(1280, 720);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
