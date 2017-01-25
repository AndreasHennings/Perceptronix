package IO;

import BUS.ButtonListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aend on 02.12.16.
 */


public class UserInterface extends JPanel

{
    //JFileChooser fc;
    public ButtonListener bl;
    private JPanel jPanel;
    private JButton trainAIUsingListButton;
    private JButton runAI;
    private JTextField trainFilename;
    private JTextArea textArea1;
    private JTextField categorizeFilename;

    // this main() is called from Perceptronix.main
    public static UserInterface main()
    {
        UserInterface ui = new UserInterface();

        JFrame frame = new JFrame("Perceptronix");
        frame.setContentPane(ui.jPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        return ui;
    }

    public UserInterface ()
    {
    }

    public void init(ButtonListener b)
    {
        bl = b;

        trainAIUsingListButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String filename=trainFilename.getText();
                bl.onButtonPressed("train", filename);
            }

        });


        runAI.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String filename = categorizeFilename.getText();
                bl.onButtonPressed("categorize", filename);
            }
        });


    }

    public void setText(String message)
    {
        textArea1.append(message);
        textArea1.append("\n");
    }
}
