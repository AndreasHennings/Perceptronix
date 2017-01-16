package IO;

import javax.swing.*;

/**
 * Created by aend on 02.12.16.
 */


public class UserInterface extends JPanel

{
    private JPanel jPanel;
    private JButton trainAIUsingListButton;
    private JTextArea textArea1;
    private JPanel c1;
    private JButton b1;
    private JPanel pm;
    private JTextField brainStatus;

    // this main() is called from Perceptronix.main
    public static void main()
    {
        JFrame frame = new JFrame("Perceptronix");
        frame.setContentPane(new UserInterface().jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
    public UserInterface()
    {
        init();
    }

    public UserInterface (String[] categories)
    {
        init();
    }

    public void init()
    {



        /*
        for (int i =0; i<40;i++)
        {
            textArea1.append("\nblabla");
        }
        */



        /*
        trainAIUsingListButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser fc;
                fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(UserInterface.this);
            }
        });
        */

    }


}
