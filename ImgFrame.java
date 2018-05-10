
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import marvin.gui.MarvinImagePanel;
import marvin.image.MarvinImage;
import marvin.image.MarvinImageMask;
import marvin.io.MarvinImageIO;
import marvin.plugin.MarvinImagePlugin;
import marvin.util.MarvinPluginLoader;

public class ImgFrame extends JFrame{
	public MarvinImagePanel 	imagePanel;
	public MarvinImage 		image;

	public ImgFrame()
	{
		super("ImgFrame");
		// ImagePanel
		this.setUndecorated(true);
		imagePanel = new MarvinImagePanel();
		//l_c.setLayout(new BorderLayout());
		Container con = getContentPane();
		con.add(imagePanel);
		setSize(1920,1080);
		setVisible(true);
	}

/*	public static void main(String args[]){
		ImgFrame t=new ImgFrame();
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.image=MarvinImageIO.loadImage("Capture.jpg");
		t.imagePanel.setImage(t.image);
		//Runnable r=new Run();
		//new Thread(r).start();
	}*/
}
