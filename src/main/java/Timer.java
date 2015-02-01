import java.util.TimerTask;
import java.awt.Toolkit;

public class Timer extends TimerTask {
	 Toolkit toolkit;

	  Timer timer;
	    public void run() {
	      System.out.println("Time's up!");
	      toolkit.beep();
	      System.exit(0); //Stops the AWT thread (and everything else)
	    }
  }


