package eu.athenasBinary.chromeDriver;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LoginRobot extends Thread {
    @Getter
    @Setter
    private boolean notNeededAnymore;

    @Override
    public void run() {
        //MyLogger.debug("---Start robota---");
        try {
            int repeats = 10;
            int timeStep = 1500;
            Robot robot = new Robot();
            while (repeats-- > 0 && !notNeededAnymore) {
                Thread.sleep(timeStep);
                if (notNeededAnymore) {
                    break;
                }
                robot.keyPress(KeyEvent.VK_ESCAPE);
                robot.keyRelease(KeyEvent.VK_ESCAPE);
            }
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
        //MyLogger.debug("---Konec robota---");
    }
}