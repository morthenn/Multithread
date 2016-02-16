/**
 * Created by UC198883 on 16/02/2016.
 */
public class Game implements Runnable {

    private static int width = 300;
    private static int height = width / 16 * 9;
    private static int scale = 3;


    private Thread thread;
    private boolean running = false;

    public synchronized void start() {
        running=true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running=false;
        try {
            thread.join();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    public void run() {
        while ( running ) {

        }
    }
}
