import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class Game
{
    JFrame frame;
    private JButton clickButton;
    private JProgressBar progressBar;
    private JPanel MainPanel;
    private JButton exitGameButton;
    PrintWriter out;
    BufferedReader in;

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
        progressBar.setMaximum(100);
        progressBar.setValue(50);

        clickButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                out.println("click");
            }
        });

        exitGameButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                out.println("quit game");
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    public void showGameWindow()
    {
        frame = new JFrame();
        frame.setContentPane(this.MainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
