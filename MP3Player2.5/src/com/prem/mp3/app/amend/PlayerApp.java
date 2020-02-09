package com.prem.mp3.app.amend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;

import com.prem.mp3.app.amend.comp.FileExplorerTable;

@SuppressWarnings("serial")
public class PlayerApp extends JFrame {

	private static PlayerApp thisInstance = null;

	private final int windowHeight = 600;
	private final int leftWidth = 380;
	private final int rightWidth = 600;
	private final int windowWidth = leftWidth + rightWidth;
	private final Font treeFont = new Font("Lucida Console", Font.BOLD, 14);
	private final Font textFont = new Font("Lucida Console", Font.PLAIN, 13);

	public static PlayerApp start() {
		if (thisInstance == null) {
			System.out.println("new Instance of MP3Player created");
			thisInstance = new PlayerApp();
		}
		return thisInstance;

	}

	private PlayerApp() {
		try {
			URL url16 = Paths
					.get("/home/premendra/git/MP3Player2.5/MP3Player2.5/mp3-player-app/icon/flv_icon.png")
					.toUri().toURL();
			URL url32 = Paths
					.get("/home/premendra/git/MP3Player2.5/MP3Player2.5/mp3-player-app/icon/prem1.png")
					.toUri().toURL();
			// D:/javaEclipseRoot/ProjectBunch/swing-test-src/com/prem/examples/frame/icon/flv_icon.png

			final List<Image> icons = new ArrayList<Image>();
			icons.add(ImageIO.read(url16));
			icons.add(ImageIO.read(url32));
			this.setIconImages(icons);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// setTitle(getClass().getSimpleName());
		String version = 3.0 + "";
		setTitle(getClass().getSimpleName() + "_" + version);
		setPreferredSize(new Dimension(windowWidth, windowHeight));
		setFocusable(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// /////////

		display();
	}

	/**
	 * Displays the tree.
	 * 
	 * @param fileViewerTable
	 *            JTree to display
	 */
	public void display() {
		try {
			makeFrame();

			JScrollPane leftScrollPane = createLeftScrollPane();
			JScrollPane rightScrollPane = createRightScrollPane();

			/** ### Addition of panels to split pane configuration ** START ### **/
			JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
					leftScrollPane, rightScrollPane);

			splitPane.setContinuousLayout(true);
			splitPane.setDividerLocation(leftWidth);
			// --- Extra line & configurations # start
			splitPane.setOneTouchExpandable(true);
			splitPane.setResizeWeight(0.5);
			// ---Extra line & configurations # end
			splitPane.setPreferredSize(new Dimension(windowWidth + 10,
					windowHeight + 10));
			/** ### Addition of panels to split pane configuration ** END ### **/

			setLayout(new BorderLayout());
			add("Center", splitPane);
			pack();

			// /-- look and feel # start
			String lookAndFeel = null;
			// lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			 lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			//lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		        	//lookAndFeel=info.getClassName();
		        	System.out.println("lookAndFeel : "+lookAndFeel);
//		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
			//UIManager.setLookAndFeel(lookAndFeel);
			// /-- look and feel # end

			setVisible(true);
		} catch (Exception e) {
			System.err.println("error when updating xml viewer");
			e.printStackTrace();
		}
	}

	private JScrollPane createLeftScrollPane() {
		JScrollPane leftScrollPane = null;
		/** ### Left panel configuration ** START ### **/
		JPanel leftFilesPanel = new JPanel();
		BoxLayout leftPanelLayout = new BoxLayout(leftFilesPanel,
				BoxLayout.PAGE_AXIS);
		FileExplorerTable objFileExplorerTable = new FileExplorerTable();
		leftFilesPanel.add(objFileExplorerTable);
		leftFilesPanel.setLayout(leftPanelLayout);
		leftFilesPanel.setBorder(BorderFactory
				.createTitledBorder(" Selected Songs "));

		leftScrollPane = new JScrollPane(leftFilesPanel);
		leftScrollPane
				.setPreferredSize(new Dimension(rightWidth, windowHeight));
		/** ### Left panel configuration ** END ### **/
		return leftScrollPane;
	}

	private JScrollPane createRightScrollPane() {
		JScrollPane rightScrollPane = null;
		/** ### Right panel configuration ** START ### **/
		JPanel rightButtonPanel = new JPanel();
		BoxLayout objBoxLayout = new BoxLayout(rightButtonPanel,
				BoxLayout.PAGE_AXIS);
		rightButtonPanel.setLayout(objBoxLayout);
		rightButtonPanel.setBorder(BorderFactory
				.createTitledBorder(" Commands "));

		rightScrollPane = new JScrollPane(rightButtonPanel);
		rightScrollPane
				.setPreferredSize(new Dimension(rightWidth, windowHeight));
		/** ### Right panel configuration ** End ### **/
		return rightScrollPane;
	}

	private void makeFrame() {

		getContentPane().removeAll();

		// fileMenu.add(open);
		// menuBar.add(fileMenu);
		// setMenuBar(menuBar);

		pack();
		setVisible(true);
	}

}
