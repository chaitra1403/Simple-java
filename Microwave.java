// libraries

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.lang.Thread;
/*<applet code="Microwave" width="400" height="400"></applet>*/


public class Microwave extends Applet {



  Button Button1;
  Button Button2;
  Button Button3;
  Button Button4;
  Button Button5;
  Button Button6;
  Button ButtonConfigure;
  TextField Display;
  int Counter;
  myCounter counterthread;
  Frame frame;
  boolean countdown = false;
  Checkbox printIt;
  Choice powerSetting;
  Panel heatPanel;
  Panel sillyChoicePanel;

  
  CheckboxGroup wavelengthGroup;
  Checkbox microwave;
  Checkbox infrared;
  Checkbox gamma;
	
  public void init(){

  
    setLayout(new GridLayout(3, 4));
    
   
    Button1 = new Button("cook");
    Button2 = new Button("clear");
    Button3 = new Button("frozen");
    Button4 = new Button("quick");
    Button5 = new Button("potato");
    Button6 = new Button("bev");
    ButtonConfigure = new Button("Configure");

    Display = new TextField("0", 5);

    add(Button1);
    add(Button2);
    add(Button3);

    add(new Label());
    add(Button4);
    add(Button5);
    add(Button6);
    add(Display);
    add(new Label());
  add(ButtonConfigure);

    Button1.addMouseListener(new myTimingListener());
    Button2.addMouseListener(new myTimingListener());
    Button3.addMouseListener(new myTimingListener());
    Button4.addMouseListener(new myTimingListener());
    Button5.addMouseListener(new myTimingListener());
    Button6.addMouseListener(new myTimingListener());
    ButtonConfigure.addMouseListener(new myConfigListener());
    
  
    counterthread = new myCounter();
    counterthread.start();
  }

    class configListener implements WindowListener {
	
	public void windowDeactivated(WindowEvent e) {
	}
	public void windowClosed(WindowEvent e) {
	}
	public void windowDeiconified(WindowEvent e) {
	}
	public void windowOpened(WindowEvent e) {
	}
	public void windowIconified(WindowEvent e) {
	}    
	public void windowActivated(WindowEvent e) {
	}
	public void windowClosing(WindowEvent e) {
	    if (printIt.getState()) {
		System.out.println("You want me to use heat setting:");
		System.out.println(powerSetting.getSelectedItem());
		System.out.println("and you want me to use EM wavelength:");
		if (microwave.getState()) 
		    System.out.println("microwave");
		if (infrared.getState())
		    System.out.println("infrared");
		if (gamma.getState())
		    System.out.println("gamma");
	    }
	    frame.setVisible(false);
	}
    }

  class myCounter extends Thread{
      public void run(){
          int counter;
          while(true)
	      {
		  while (countdown)
		      {
			  counter = Integer.parseInt(Display.getText());
			  counter--;
			  if (counter > 0)
			      {
				  Display.setText(Integer.toString(counter));
				  try {Thread.sleep(1000);}
				  catch(InterruptedException ie) { countdown = false; }
			      }
			  else
			      {
				  Display.setText("0");
				  countdown = false;
			      }
		      }
		  try {Thread.sleep(100);}
		  catch(InterruptedException ie) {}
	      }
      }
  }
    
  class myTimingListener implements MouseListener{

    public void mouseClicked(MouseEvent e){

      if(e.getSource()==Button1)
        countdown = true;
      else
      {
        if(e.getSource()==Button2)
          Counter = 0;
        if(e.getSource()==Button3)
          Counter = 60;
        if(e.getSource()==Button4)
          Counter = 30;
        if(e.getSource()==Button5)
          Counter = 50;
        if(e.getSource()==Button6)
          Counter = 45;
  
        Display.setText(Integer.toString(Counter));
      }
    }
  
    public void mouseEntered(MouseEvent e){};
    public void mouseExited(MouseEvent e){};
    public void mousePressed(MouseEvent e){};
    public void mouseReleased(MouseEvent e){};
  }   

  class myConfigListener implements MouseListener{

    public void mouseClicked(MouseEvent e){

	frame = new Frame();
	
	frame.addWindowListener(new configListener());
	
	frame.setSize(400,125);

	frame.setLayout(new BorderLayout());
	
	printIt = new Checkbox("Output result to stdout?");
	frame.add("North", printIt);
	
	heatPanel = new Panel();
	frame.add ( "Center", heatPanel );
	
	
	heatPanel.add(new Label("Set Heat Level:"));
	
	powerSetting = new Choice();
	powerSetting.add("Replicate Big Bang Conditions");
	powerSetting.add("Induce Gluon Spaghetti");
	powerSetting.add("Plasmify");
	powerSetting.add("Vaporize");
	powerSetting.add("Carbonize");
	powerSetting.add("Sear");
	powerSetting.add("Boil");
	powerSetting.add("Really Hot");
	powerSetting.add("Warm");
	powerSetting.add("Tepid");
	powerSetting.add("Lightly Warm");
	powerSetting.add("Just whir and turn on the light");
	powerSetting.select("Warm");
	
	heatPanel.add(powerSetting);
	
	sillyChoicePanel = new Panel();
	sillyChoicePanel.setLayout(new FlowLayout());
	
	sillyChoicePanel.add(new Label("Select spectrum:"));
	
	wavelengthGroup = new CheckboxGroup();
	microwave = new Checkbox("Microwave", wavelengthGroup, true);
	infrared = new Checkbox("Infrared", wavelengthGroup, false);
	gamma = new Checkbox("Gamma", wavelengthGroup, false);
	sillyChoicePanel.add(microwave);
	sillyChoicePanel.add(infrared);
	sillyChoicePanel.add(gamma);
	
	frame.add("South", sillyChoicePanel );
	frame.setVisible(true);
    }
  
    public void mouseEntered(MouseEvent e){};
    public void mouseExited(MouseEvent e){};
    public void mousePressed(MouseEvent e){};
    public void mouseReleased(MouseEvent e){};
  }   
  
}






