package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerHelper {

    private static final Set<Integer> ID_PENGGUNAS = new HashSet<>();

    private static final Set<PrintWriter> USERS = new HashSet<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Server sedang berjalan...");

        var pool = Executors.newFixedThreadPool(500);
        try (ServerSocket listener = new ServerSocket(59001)) {
            while (true) {
                pool.execute(new Handler(listener.accept()));
            }
        }
    }

    private static class Handler implements Runnable {

        private int idPengguna;
        private final Socket socket;
        private Scanner in;
        private PrintWriter out;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    out.println("SUBMIT_ID");
                    idPengguna = in.nextInt();
                    if (idPengguna == 0) {
                        return;
                    }
                    synchronized (ID_PENGGUNAS) {
                        if (!ID_PENGGUNAS.contains(idPengguna)) {
                            ID_PENGGUNAS.add(idPengguna);
                            break;
                        }
                    }
                }
                System.out.println(idPengguna + " has joined");
                USERS.add(out);

                while (true) {
                    if (in == null) {
                        return;
                    }
                    String input = in.nextLine();

                    if (input.startsWith("PESANAN_ADDED")) {
                        USERS.forEach((_user) -> {
                            _user.println("PESANAN_ADDED" + input.substring(13));
                        });
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerHelper.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                if (out != null) {
                    USERS.remove(out);
                }
                if (idPengguna != 0) {
                    System.out.println(idPengguna + " is leaving");
                    ID_PENGGUNAS.remove(idPengguna);
                }

                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServerHelper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
