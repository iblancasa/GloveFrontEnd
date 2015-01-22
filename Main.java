package guante;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


/**
 *
 * @author Israel Blancas
 */
public class Main {

    /**
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) throws AWTException {
        SerialPort serialPort = new SerialPort("/dev/ttyUSB0");
        Robot robot = new Robot();
        try {
            System.out.println("Port opened: " + serialPort.openPort());
            System.out.println("Params setted: " + serialPort.setParams(9600, 8, 1, 0));
            System.out.println("\"Hello World!!!\" successfully writen to port: " + serialPort.writeBytes("Iniciemos".getBytes()));
            while(true){
                 byte[] buffer = serialPort.readBytes(1);//Lee un carácter
                 String str = new String(buffer, "UTF-8");
                 System.out.println(str);
                 
                 if(str.contains("d")){
                        System.out.print("HA SIDO PULSADA");
                        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
                        str="";
                 }
            }
       
        }
        catch (SerialPortException ex){
            System.out.println(ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
