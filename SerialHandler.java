import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.*;
import java.io.*;
import java.util.Enumeration;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.*;
import marvin.gui.MarvinImagePanel;
import marvin.image.MarvinImage;
import marvin.image.MarvinImageMask;
import marvin.io.MarvinImageIO;
import marvin.plugin.MarvinImagePlugin;
import marvin.util.MarvinPluginLoader;

public class SerialHandler implements SerialPortEventListener {
    SerialPort serialPort;
    static ArrayList<MarvinImage> images;
    static ImgFrame frame=new ImgFrame();
    static Map<String, ArrayList<MarvinImage>> lib;
    static int index;
    // SERIAL INPUT NUMBER FOR RFID CARDS
    static String TAG1="127";
    static String TAG2;
    static String TAG3;
    static String TAG4;
    // SERIAL INPUT NUMBER FOR BUTTONS
    static String BUTTONNEXT;
    static String BUTTONPREVIOUS;
    static String BUTTON3;
    // KEYS IN HASHMAP
    static String CAT1="cat";
    static String CAT2;
    static String CAT3;
    static String CAT4;

    /** The port we're normally going to use. */
    private static final String PORT_NAMES[] = {
	"/dev/ttyACM0", // Raspberry Pi
    };

    /**
     * A BufferedReader which will be fed by a InputStreamReader
     * converting the bytes into characters
     * making the displayed results codepage independent
     */
    private BufferedReader input;
    /** The output stream to the port */
    private OutputStream output;
    /** Milliseconds to block while waiting for port open */
    private static final int TIME_OUT = 2000;
    /** Default bits per second for COM port. */
    private static final int DATA_RATE = 9600;

    //private Visegutt vg;

    //public SerialHandler(Visegutt vg) {
    //	this.vg = vg;
    //}


    public void initialize() {
	// the next line is for Raspberry Pi and
	// gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
	System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyCOM3");
	System.out.println("IN INITIALIZE");

	CommPortIdentifier portId = null;
	Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

	//First, Find an instance of serial port as set in PORT_NAMES.
	while (portEnum.hasMoreElements()) {
	    CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
	    for (String portName : PORT_NAMES) {
		if (currPortId.getName().equals(portName)) {
		    portId = currPortId;
		    break;
		}
	    }
	}
	if (portId == null) {
	    System.out.println("Could not find COM port.");
	    return;
	}

	try {
	    // open serial port, and use class name for the appName.
	    serialPort = (SerialPort) portId.open(this.getClass().getName(),
						  TIME_OUT);

	    // set port parameters
	    serialPort.setSerialPortParams(DATA_RATE,
					   SerialPort.DATABITS_8,
					   SerialPort.STOPBITS_1,
					   SerialPort.PARITY_NONE);

	    // open the streams
	    input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
	    output = serialPort.getOutputStream();

	    // add event listeners
	    serialPort.addEventListener(this);
	    serialPort.notifyOnDataAvailable(true);
	    System.out.println("END OF INITIALIZE");
	} catch (Exception e) {
	    System.err.println(e.toString());
	}
    }

    /**
     * This should be called when you stop using the port.
     * This will prevent port locking on platforms like Linux.
     */
    public synchronized void close() {
	if (serialPort != null) {
	    serialPort.removeEventListener();
	    serialPort.close();
	}
    }

    /**
     * Handle an event on the serial port. Read the data and print it.
     */
    public synchronized void serialEvent(SerialPortEvent oEvent) {
	System.out.println("IN SERIAL EVENT");
	if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
	    try {
		// Get input from serial
    String serialInput = input.readLine();
    if(serialInput.equals(TAG1)){
      show("CAT1");
    }
    else if(serialInput.equals(TAG2)){
      show("CAT2");
    }
    else if(serialInput.equals(TAG3)){
      show("CAT3");
    }
    else if(serialInput.equals(TAG4)){
      show("CAT4");
    }
    else if(serialInput.equals(BUTTONNEXT)){
      next();
    }
    else if(serialInput.equals(BUTTONPREVIOUS)){
      previous();
    }
    else if(serialInput.equals(BUTTON3)){
      show(""); // DO SOMETHING
    }

		System.out.println("Got serial input: " + serialInput);


		/*if (serialInput.startsWith("a")) {
		// If input starts with a, add product
		vg.addProduct(serialInput.substring(1));

		} else if (serialInput.startsWith("b")) {
		// If input starts with b, remove product
		vg.removeProduct(serialInput.substring(1));

		} else if (serialInput.startsWith("c1")) {
		// If input starts with c, it's from a button
		// c1 :	powerButton
		// c2 :	orderButton
		// c3 :	clearButton

		System.out.println("Shutting down visegutt...");
		vg.stopVisegutt();

		} else if (serialInput.startsWith("c2")) {
		// Order button is pushed

		System.out.println("Order button pushed");
		if (vg.getStatus() == 1) {
		vg.confirmOrder();
		} else if (vg.getStatus() == 2) {
		vg.sendOrder();
		}

		} else if (serialInput.startsWith("c3")) {

		vg.clearList();

		} else {
		// Should check if the input is purely numerical
		int scrollInput = Integer.parseInt(serialInput);
		vg.scroll(scrollInput);
		}
		*/

	    } catch (Exception e) {
		System.err.println(e.toString());
	    }
	}
	// Ignore all the other eventTypes, but you should consider the other ones.
    }

    private String trimId(String tempId) {
	// Converts byte output from serial to product id

	tempId = tempId.trim();
	String id = "";
	String[] digits = tempId.split(" ");
	for (int i = 0; i <= 5; i++) {
	    id += digits[i].charAt(1);
	}

	return id;
    }

  public static void main(String[] args){
    lib=parse("./ref images/");
	  SerialHandler test = new SerialHandler();
	  test.initialize();
	  while(true){
    }
  }

  private static void show(String tag){
    images=lib.get(tag);
    index=0;
    frame.imagePanel.setImage(images.get(index));
  }

  private static void next(){
    index++;
    if(index==images.size()){
      index=0;
    }
    frame.imagePanel.setImage(images.get(index));
  }

  private static void previous(){
    index--;
    if(index<0){
      index=images.size()-1;
    }
    frame.imagePanel.setImage(images.get(index));
  }

private static Map<String, ArrayList<MarvinImage>> parse(String path){
  String fn;
  String tag;
  File[] files = new File(path).listFiles();
  ArrayList<MarvinImage> inputList;
  Map<String, ArrayList<MarvinImage>> tagMap = new HashMap<String, ArrayList<MarvinImage>>();
  for (File file : files) {
    fn=file.getName();
    if(fn.endsWith(".jpg") || fn.endsWith(".png")){
      tag=fn.substring(0, fn.indexOf("_"));
      if(tagMap.containsKey(tag)){
        tagMap.get(tag).add(MarvinImageIO.loadImage(path+fn));
      }
      else{
        inputList=new ArrayList<MarvinImage>();
        inputList.add(MarvinImageIO.loadImage(path+fn));
        tagMap.put(tag, inputList);
      }
    }
  }
  return tagMap;
}
}
