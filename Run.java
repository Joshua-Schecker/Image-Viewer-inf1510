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


public class Run implements Runnable{
	ImgFrame t;
	ArrayList<MarvinImage> images;

	public Run(ArrayList<MarvinImage> images){
		t=new ImgFrame();
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.images=images;
	}

	public void run(){
		int i=0;
		while(i<5){
			t.imagePanel.setImage(images.get(i));
			try{
				Thread.sleep(3000);
			}
			catch(Exception e){}
			i++;
		}
	}
}
