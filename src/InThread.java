import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;

public class InThread extends Thread
{
    public InThread(BufferedReader in,PrintWriter out, Game game)
    {
        this.in = in;
        this.out = out;
        this.game = game;
    }

    private PrintWriter out;
    private BufferedReader in;
    private Game game;

    private JProgressBar progressBar;
    private String response;
    
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                response = in.readLine();
            }
            catch (SocketException s)
            {
                System.out.println("Server unexpectedly stopped");
                return;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            if (response != null)
            {
                System.out.println(response);

                if (response.contains("Added to game"))
                {
                    String[] command = response.split("\\s+");
                    String gameName = command[4];
                    game = new Game(in, out);
                    game.showGameWindow();
                    game.setTitle(gameName);
                    progressBar = game.getProgressBar();
                }

                if(response.contains("Game state:"))
                {
                    String[] command = response.split("\\s+");
                    int state = Integer.parseInt(command[2]);
                    progressBar.setValue(state);
                }

                if(response.contains("Game over"))
                {
                    String endGameState = "";
                    String[] resp = response.split("\\s+");
                    for(int i=2; i<resp.length; i++)
                        endGameState+=resp[i]+" ";

                    JOptionPane GameOverWindow = new JOptionPane();
                    GameOverWindow.showMessageDialog(null, endGameState, "Game over", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
