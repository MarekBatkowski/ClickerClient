import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;

public class InThread extends Thread
{
    public InThread(BufferedReader in,PrintWriter out, boolean isInGame, Game game)
    {
        this.in = in;
        this.out = out;
        this.isInGame = isInGame;
        this.game = game;
    }

    PrintWriter out;
    BufferedReader in;
    boolean isInGame;
    Game game;
    JProgressBar progressBar;

    String response;

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
            if (response != null) {
                System.out.println(response);

                if (response.contains("Added to game"))
                {
                    isInGame = true;
                    String[] command = response.split("\\s+");
                    String gameName = command[4];
                    game = new Game(in, out);
                    game.showGameWindow();
                    game.setTitle(gameName);
                    progressBar = game.getProgressBar();
                }

                if(response.contains("Game state:") && isInGame)
                {
                    String[] command = response.split("\\s+");
                    int state = Integer.parseInt(command[2]);
                    progressBar.setValue(state);
                }
                if(response.contains("Game over") && isInGame)
                {
                    JOptionPane jop = new JOptionPane();
                    jop.showMessageDialog(null, response, "Game over", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
