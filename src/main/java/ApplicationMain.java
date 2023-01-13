import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ApplicationMain extends JDialog {


    static String[][] solution = new String[2][4];

    private static JFrame createWindow() {
        JFrame frame = new JFrame("Swing Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(560, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }

    private static void createUI(final JFrame frame){
        JPanel panel = new JPanel();
        LayoutManager layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.setLayout(layout);
        String inputPath = JOptionPane.showInputDialog("Type in the path of the .csv file");
        String outputPath = "The .csv file is saved under: " + inputPath;
        JOptionPane.showMessageDialog(null, outputPath);

        results(inputPath);
        JLabel title = new JLabel("Standard Solution", JLabel.CENTER);
        title.setForeground(Color.red);
        panel.add(title);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.add(new JLabel("Number of edges: " + solution[0][0]));
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.add(new JLabel("Max flow: " + solution[0][1]));
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.add(new JLabel("Number of rounds: " + solution[0][2]));
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.add(new JLabel("number of useless edges: " + solution[0][3]));
        frame.getContentPane().add(panel, BorderLayout.SOUTH);

        panel.add(new JLabel("\n"));
        frame.getContentPane().add(panel, BorderLayout.SOUTH);

        JLabel title2 = new JLabel("Solution where L2 is removed", JLabel.CENTER);
        title2.setForeground(Color.red);
        panel.add(title2);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.add(new JLabel("Number of edges: " + solution[1][0]));
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.add(new JLabel("Max flow: " + solution[1][1]));
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.add(new JLabel("Number of rounds: " + solution[1][2]));
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.add(new JLabel("number of useless edges: " + solution[1][3]));
        frame.getContentPane().add(panel, BorderLayout.NORTH);

    }

    public static void results(String inputField) {

        /**Solution 1*/
        Flows g1 = new Flows(inputField);
        String edgeCount1 = "" + g1.getEdgeCount();
        FordFulkerson algo1 = new FordFulkerson(g1);

        float maxFlow1 = algo1.computeMaxFlow();
        solution[0][0] = "" + edgeCount1; // number of edges
        solution[0][1] = "" + maxFlow1; // max flow
        solution[0][2] = "" + Math.ceil(1000 / maxFlow1); // number of rounds
        solution[0][3] = "" + algo1.showUselessFlowEdges().size(); // number of useless edges

        /**Solution 2*/
        Flows g2 = new Flows(inputField);
        float maxFlow2 = 0f;
        Integer L2 = -1;
        for (Integer i = 1; i < g2.size(); i++) {
            if (g2.getVertexName(i).equals("L2")) {
                L2 = i;
                break;
            }
        }
        if (L2 == -1) {
            for (int i = 0; i < 4; i++) {
                solution[1][i] = "No L2 storage found.";
            }
            System.out.println("Nil. (No 'L2' storage found.)");
        }
        else {
            g2.deleteVertex(L2);
            String edgeCount2 = "" + g2.getEdgeCount();
            FordFulkerson algo2 = new FordFulkerson(g2);
            maxFlow2 = algo2.computeMaxFlow();
            solution[1][0] = "" + edgeCount2;
            solution[1][1] = "" + maxFlow2;
            solution[1][2] = "" + Math.ceil(1000 / maxFlow2);
            solution[1][3] = "" + algo2.showUselessFlowEdges().size();
        }
    }



    public static void main(String[] args) {
        createWindow();

    }
}

