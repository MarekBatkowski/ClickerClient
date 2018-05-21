import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class Game
{
    JFrame frame;
    private JButton button1;
    private JProgressBar progressBar;
    private JPanel MainPanel;
    PrintWriter out;
    BufferedReader in;
    String response;

    void setTitle(String newTitle)
    {
        frame.setTitle(newTitle);
    }

    JProgressBar getProgressBar()
    {
        return progressBar;
    }

    public Game(BufferedReader in, PrintWriter out)
    {
        this.in = in;
        this.out = out;
        progressBar.setMinimum(0);
        progressBar.setMaximum(200);
        progressBar.setValue(100);



        button1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                out.println("click");
            }
        });
    }

    public void showGameWindow()
    {
        frame = new JFrame();
        //frame.setContentPane(new Game().MainPanel);
        frame.setContentPane(this.MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
