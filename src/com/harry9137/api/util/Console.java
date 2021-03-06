package com.harry9137.api.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PipedInputStream;

public class Console extends WindowAdapter implements WindowListener, ActionListener, Runnable
{

    private JFrame frame;
    private JTextArea textArea;
    private Thread reader;
    private Thread reader2;
    private boolean quit;

    private final PipedInputStream pin=new PipedInputStream();
    private final PipedInputStream pin2=new PipedInputStream();

    Thread errorThrower; // just for testing (Throws an Exception at this Console

    public Console()
    {
        // create all components and add them
        frame=new JFrame("TSAGame Developer Console");
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize=new Dimension((int)(screenSize.width/2),(int)(screenSize.height/2));
        int x=(int)(frameSize.width/2);
        int y=(int)(frameSize.height/2);
        frame.setBounds(x,y,frameSize.width,frameSize.height);

        textArea=new JTextArea();
        textArea.setEditable(false);
        JButton button=new JButton("clear");

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(new JScrollPane(textArea),BorderLayout.CENTER);
        frame.getContentPane().add(button,BorderLayout.SOUTH);
        frame.setVisible(true);

        frame.addWindowListener(this);
        button.addActionListener(this);

        quit=false; // signals the Threads that they should exit

        // Starting two seperate threads to read from the PipedInputStreams
        //
        reader=new Thread(this);
        reader.setDaemon(true);
        reader.start();
        //
        reader2=new Thread(this);
        reader2.setDaemon(true);
        reader2.start();


    }

    public synchronized void windowClosed(WindowEvent evt)
    {
        quit=true;
        this.notifyAll(); // stop all threads
        try { reader.join(1000);pin.close();   } catch (Exception e){}
        try { reader2.join(1000);pin2.close(); } catch (Exception e){}
        System.exit(0);
    }

    public synchronized void windowClosing(WindowEvent evt)
    {
        frame.setVisible(false); // default behaviour of JFrame
        frame.dispose();
    }

    public synchronized void actionPerformed(ActionEvent evt)
    {
        textArea.setText("");
    }

    public synchronized void run()
    {
        try
        {
            while (Thread.currentThread()==reader)
            {
                try { this.wait(100);}catch(InterruptedException ie) {}
                if (pin.available()!=0)
                {
                    String input=this.readLine(pin);
                    textArea.append(input);
                }
                if (quit) return;
            }

            while (Thread.currentThread()==reader2)
            {
                try { this.wait(100);}catch(InterruptedException ie) {}
                if (pin2.available()!=0)
                {
                    String input=this.readLine(pin2);
                    textArea.append(input);
                }
                if (quit) return;
            }
        } catch (Exception e)
        {
            textArea.append("\nConsole reports an Internal error.");
            textArea.append("The error is: "+e);
        }

        // just for testing (Throw a Nullpointer after 1 second)
        if (Thread.currentThread()==errorThrower)
        {
            try { this.wait(1000); }catch(InterruptedException ie){}
            throw new NullPointerException("Application test: throwing an NullPointerException It should arrive at the console");
        }

    }

    public synchronized String readLine(PipedInputStream in) throws IOException
    {
        String input="";
        do
        {
            int available=in.available();
            if (available==0) break;
            byte b[]=new byte[available];
            in.read(b);
            input=input+new String(b,0,b.length);
        }while( !input.endsWith("\n") &&  !input.endsWith("\r\n") && !quit);
        return input;
    }
    public void addText(String text){
        textArea.append("[" + "] " + text + "\n");
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}

