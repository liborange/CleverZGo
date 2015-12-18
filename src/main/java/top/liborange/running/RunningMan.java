package top.liborange.running;

import com.pi4j.io.gpio.*;


/**
 * Created by liborange on 15/12/18.
 */
public class RunningMan {
    final GpioController gpio = GpioFactory.getInstance();
    final GpioPinDigitalOutput leftPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "left wheel", PinState.HIGH);

    public void run(){
        try {
            leftPin.setShutdownOptions(true,PinState.LOW);
            System.out.println("pin status: " + leftPin.getState());
            Thread.sleep(5000);

            leftPin.low();
            System.out.println("pin status: " + leftPin.getState());
            Thread.sleep(5000);

            leftPin.pulse(1000, true);
            System.out.println("pin status shoud be ON for 1 second.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RunningMan Dengchao = new RunningMan();
        Dengchao.run();
    }
}
