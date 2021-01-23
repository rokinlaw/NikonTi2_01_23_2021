package Laser;

import Main.AcquisitionData;
import mmcorej.CMMCore;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.Random;

public class MouseController implements Runnable {
    private static AcquisitionData acquisitionData;
    private Dimension dim;
    private Random rand;
    private Robot robot;
    private volatile boolean stop = false;

    public MouseController() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        rand = new Random();
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
        this.acquisitionData = acquisitionData;
    }
    //setting up automated process
    public void run() {
        while(!stop) {
            robot.mouseMove(2010, 894);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(16000);
            //open FITC
            robot.mouseMove(2128, 638);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(500);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(5000);
            //close DAPI
            robot.mouseMove(2128, 538);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(5000);
            //capture FITC image
            robot.mouseMove(2010, 894);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(16000);
            //open TRITC
            robot.mouseMove(2128, 665);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(5000);
            //close FITC
            robot.mouseMove(2128, 563);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(5000);
            //capture DAPI image
            robot.mouseMove(2010, 894);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(30000);
        }
    }
    public synchronized void stop() {
        stop = true;
    }
    public static void main() {
        MouseController mc = new MouseController();
        Thread mcThread = new Thread(mc);
        System.out.println("Mouse Controller start");
        mcThread.start();
        try {
            Thread.sleep(40000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        mc.stop();
        System.out.println("Mouse Controller stoped");
    }
}
