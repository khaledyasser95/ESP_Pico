import javax.swing.*;

public class Run {
    public static void main(String[] args) {
        //1. Create the frame.
        JFrame frame = new JFrame("Menu");
        frame.setContentPane(new Interface().rootPanel);
        // frame.setSize(1000,1150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);

    }
}
