import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ApplicationMain extends JDialog {

    private static void createWindow() {
        JFrame frame = new JFrame("Swing Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(560, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    private static void createUI(final JFrame frame){
        JPanel panel = new JPanel();
        LayoutManager layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.setLayout(layout);


        String inputPath = JOptionPane.showInputDialog("Type in the path of the .csv file");
        String outputPath = "The .csv file is saved under: " + inputPath;
        JOptionPane.showMessageDialog(null, outputPath);

        panel.add(new JButton("RUN!"));
        frame.getContentPane().add(panel, BorderLayout.SOUTH);

    }


    public static void main(String[] args) {
        createWindow();
    }
}
