FingerPrint

package com.ack.security.jce;

import java.io.FileInputStream;
import java.security.MessageDigest;
import javax.swing.*;

import sun.misc.BASE64Encoder;

/**
 * Builds the finger print of file, crypto hash value
 */
public class FingerPrint {
  public static void main(String[] args) throws Exception {
    // get the file path e.g. c:\Docs\zigzag.txt
    String inputText = JOptionPane.showInputDialog("Input your file path  ");

    // trying to build new message digest which represents and encapsulates
    // the Message Java Digest

    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    // calculating from the given file running its inside
    // while calculating the digest formula

    FileInputStream input = new FileInputStream(inputText);
    byte[] buffer = new byte[8192];
    int length;
    while( (length = input.read(buffer)) != -1 ) {
      messageDigest.update(buffer, 0, length);
    }
    byte[] raw = messageDigest.digest();

    //printout in 64 base
    BASE64Encoder encoder = new BASE64Encoder();
    String base64 = encoder.encode(raw);

    // and display the results
    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                                  "your file finger print is "
                                  + new String(base64.toString()));


  } // main method end

}  // class end
import java.applet.*;
import java.awt.*;

public class WalkingText extends Applet implements Runnable {
	protected String mesg = null;
	protected int  xloc, yloc, width, height, textWidth, textHeight;
	protected Thread t;
	protected boolean done = false;
	/** How long to nap for each move */
	protected int napTime = 150;

	/** Applet Initializer */
	public void init() {
		xloc = 0;
		width = getSize().width;
		height = getSize().height;

		if ((mesg = getParameter("text")) == null)
			mesg = "Hello World of Java";

		String pSize = getParameter("fontsize"); 
		if (pSize == null)
			pSize = "12";

		String fontName = getParameter("fontName"); 
		if (fontName == null)
			fontName = "Helvetica";

		// System.out.println("Font is " + pSize + " point " + fontName);
		Font f = new Font(fontName, Font.PLAIN, Integer.parseInt(pSize));
		setFont(f);

		FontMetrics fm = getToolkit().getFontMetrics(f);
		textWidth = fm.stringWidth(mesg);
		textHeight = fm.getHeight();
		// System.out.println("TextWidth " + textWidth + ", ht " + textHeight);

		// use textHeight in y coordinate calculation
		yloc = height - ((height-textHeight) / 2);
	}

	/** This is important: we create a thread, so we must kill it */
	public void stop() {
		done = true;
		t = null;
	}

	/** create the thread and start it. */
	public void start() {
		if (t == null)
			t = new Thread(this);
		done = false;
		t.start();
	}

	// Usage:
	public String[][] getParameterInfo() {
		String[][] params = {
			{ "text", "text", "Hi And Welcome User" },
			{ "fontName", "text", "Comic Sans" },
			{ "fontsize", "int", "72" },
		};

		return params;
	}


	/** Run is called by the Thread class when there is work to do */
	public void run() {
		while (!done) {
			if ((xloc+=5) > getSize().width)
				xloc = 0;
			repaint();
			try {
				Thread.sleep(napTime);
			} catch (Exception e) {
				System.out.println("Windroids()? " + e);
			};
		}
	}

	/** Paint is called by Applet to redraw the screen */
	public void paint(Graphics g) {
		g.drawString(mesg, xloc, yloc);
		// if ((xloc + textWidth) > getSize().width) {
		// 	int negLoc = textWidth-(width-xloc);
		// 	System.out.println("xloc, textWidth, negLoc: " + xloc + "," +
		// 			textWidth + ", " + negLoc);
		// 	g.drawString(mesg, negLoc, yloc);
		// }
	}
}
/*
<APPLET CODE="WalkingText" WIDTH=500 HEIGHT=70>
	<PARAM NAME=FontName Value="Helvetica">
	<PARAM NAME=FontSize Value="24">
</APPLET>
*/
package com.journaldev.files;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class JavaOpenFile {

    public static void main(String[] args) throws IOException {
        //text file, should be opening in default text editor
        File file = new File("/Users/pankaj/source.txt");
        
        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            System.out.println("Less MB And KB Than Expected");
            return;
        }
        
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(Droid.js);
        
        
