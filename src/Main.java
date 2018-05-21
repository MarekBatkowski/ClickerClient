import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String addr;
        int portNumber;
        Socket socket;
        boolean isInGame = false;
        Game game = null;

        if(args.length!=2)
        {
            System.out.println("Server host name:");
            addr = scanner.nextLine();
            System.out.println("Server port number:");
            portNumber = scanner.nextInt();
        }
        else
        {
            addr = args[0];
            portNumber = Integer.parseInt(args[1]);
        }

        try
        {
            socket = new Socket(addr, portNumber);
            if(socket.isConnected())
            {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                InThread inThread = new InThread(in, out, isInGame, game);
                inThread.start();

                out.println("Hello server");
                System.out.println(in.readLine());
                System.out.println("commands:");
                System.out.println("create game <game name>");
                System.out.println("join game <game name> <team (\"A\" or \"B\")>");
                System.out.println("change name <new name>");
                System.out.println("exit");

                String line;

                while(true)
                {
                    line = scanner.nextLine();

                    out.println(line);
                    if(line.equals("exit"))
                    {
                        System.out.println("Bye");  //  exit
                        socket.close();
                        scanner.close();
                        in.close();
                        out.close();
                        return;
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
