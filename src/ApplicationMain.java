import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ApplicationMain extends JDialog {

    private static final long serialVersionUID = 1L;
    public ApplicationMain()
    {
        //Create a frame

        Frame f = new Frame();
        f.setSize(500, 300);

        //Prepare font
        Font font = new Font( "SansSerif", Font.PLAIN, 22 );

        //Write something
        Label label = new Label("Santa's Gift Loading System");
        label.setForeground(Color.RED);
        label.setFont(font);
        f.add(label);

        Button button = new Button("RUN!");
        button.setBounds(200, 150, 90, 50);

        f.add(button);

        // sets 500 width and 600 height

        // uses no layout managers
        f.setLayout(null);




        //Make visible
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    public static void main(final String[] args)
    {
        new ApplicationMain();
    }
}
