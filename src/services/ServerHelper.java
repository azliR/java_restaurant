package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;

public class ServerHelper {

    private static final Set<String> NAMES = new HashSet<>();

    private static final Set<PrintWriter> WRITERS = new HashSet<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Server sedang berjalan...");
        var pool = Executors.newFixedThreadPool(500);
        try (var listener = new ServerSocket(59001)) {
            while (true) {
                pool.execute(new Handler(listener.accept()));
            }
        }
    }

    private static class Handler implements Runnable {

        private String name;
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

                // Keep requesting a name until we get a unique one.
                while (true) {
                    out.println("SUBMITNAME");
                    name = in.nextLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (NAMES) {
                        if (!name.isBlank() && !NAMES.contains(name)) {
                            NAMES.add(name);
                            break;
                        }
                    }
                }

                // Now that a successful name has been chosen, add the socket's print writer
                // to the set of all writers so this client can receive broadcast messages.
                // But BEFORE THAT, let everyone else know that the new person has joined!
                out.println("NAMEACCEPTED " + name);
                WRITERS.forEach((writer) -> {
                    writer.println("MESSAGE " + name + " has joined");
                });
                WRITERS.add(out);

                // Accept messages from this client and broadcast them.
                while (true) {
                    String input = in.nextLine();
                    if (input.toLowerCase().startsWith("/quit")) {
                        return;
                    }
                    WRITERS.forEach((writer) -> {
                        writer.println("MESSAGE " + name + ": " + input);
                    });
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (out != null) {
                    WRITERS.remove(out);
                }
                if (name != null) {
                    System.out.println(name + " is leaving");
                    NAMES.remove(name);
                    WRITERS.forEach((writer) -> {
                        writer.println("MESSAGE " + name + " has left");
                    });
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
