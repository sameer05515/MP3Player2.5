package com.prem.mp3.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;

import com.prem.mp3.app.comp.CTableRowListener;
import com.prem.mp3.app.comp.CommandButton;
import com.prem.mp3.app.comp.CommandButton.Command;
import com.prem.mp3.app.comp.FileViewerTable;
import com.prem.mp3.app.util.PlayerUtility;

/**
 * Simple gui to display an xml tree.
 */
public class MP3Player extends JFrame {

	private final String title = "JDOM XML Tree";
	private final MenuBar menuBar = new MenuBar();
	private final Menu fileMenu = new Menu();
	private final MenuItem open = new MenuItem();
	private final JFileChooser fileChooser = new JFileChooser();

	// private final XMLTree xmlTree;
	private File file;
	// private JTree tree;
	private FileViewerTable fileViewerTable;
	private Exception exception;

	// PlayerUtility objSoundJLayerLevelTwo = null;

	private final int windowHeight = 600;
	private final int leftWidth = 380;
	private final int rightWidth = 600;
	private final int windowWidth = leftWidth + rightWidth;
	private final Font treeFont = new Font("Lucida Console", Font.BOLD, 14);
	private final Font textFont = new Font("Lucida Console", Font.PLAIN, 13);

	PlayerUtility objPlayerUtility = new PlayerUtility();

	private static MP3Player thisInstance = null;

	public static MP3Player getInstance() {
		if (thisInstance == null) {
			System.out.println("new Instance of MP3Player created");
			thisInstance = new MP3Player();
		}
		return thisInstance;
	}

	/**
	 * Creates a simple gui for viewing xml in a tree.
	 */
	private MP3Player() {

		try {
			URL url16 = Paths
					.get("mp3-player-app/icon/flv_icon.png")
					.toUri().toURL();
			URL url32 = Paths
					.get("mp3-player-app/icon/prem1.png")
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
		//setTitle("MP3Player2.5");
		setTitle("PayalRaj2.5");
		setPreferredSize(new Dimension(windowWidth, windowHeight));
		setFocusable(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		fileViewerTable = new FileViewerTable();

		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.setFileFilter(new FileFilter() {

			@Override
			public boolean accept(File f) {

				if (f.isDirectory()) {
					return true;
				}

				String extension = getExtension(f);
				if (extension != null) {
					if (extension.equals("mp3")) {
						return true;
					}
				}
				return false;
			}

			@Override
			public String getDescription() {
				return ".mp3";
			}

			/**
			 * Get the lower case extension of a file.
			 */
			private String getExtension(File f) {
				String ext = null;
				String s = f.getName();
				int i = s.lastIndexOf('.');

				if (i > 0 && i < s.length() - 1) {
					ext = s.substring(i + 1).toLowerCase();
				}
				return ext;
			}

		});
		// fileChooser.setCurrentDirectory(new File("C:/Users/VINU/Desktop/"));
		fileChooser.setCurrentDirectory(null);

		fileMenu.setLabel("File");

		open.setLabel("Browse");
		open.addActionListener(new MyActionListener());

		makeFrame();

		fileViewerTable.getModel().addTableModelListener(
				new TableModelListener() {

					@Override
					public void tableChanged(TableModelEvent e) {

					}
				});

		fileViewerTable.addCTableRowListener(new CTableRowListener() {

			@Override
			public void rowDoubleClicked(int row) {
				playSelectedRow();
			}
		});
		// open.dispatchEvent(new ActionEvent(open, 1001,
		// open.getActionCommand()));
		display();
	}

	private void playSelectedRow() {
		if ((fileViewerTable.getSelectedRow() != -1)
				&& (fileViewerTable.getCaseDiaryVector() != null)
				&& (fileViewerTable.getCaseDiaryVector().size() > 0)) {
			// String name = fileViewerTable.getCaseDiaryVector().get(
			// fileViewerTable.getSelectedRow());
			// try {
			// objPlayerUtility.play(Paths
			// .get(new File(name).getAbsolutePath()).toUri().toURL());
			// } catch (MalformedURLException e) {
			// e.printStackTrace();
			// }
			objPlayerUtility.playTrackNo(fileViewerTable.getSelectedRow());

		}
	}

	public void refreshTable() {

		System.out.println("Refreshing table contents...");
		String[] filesArr = objPlayerUtility.getFilePaths();
		if (filesArr == null) {
			return;
		}
		List<String> fileNames = new ArrayList<>();
		for (int i = 0; i < filesArr.length; i++) {
			fileNames.add(filesArr[i]);
		}
		fileViewerTable.setCaseDiaryVector(fileNames);
	}

	/**
	 * Construct a frame of the most recently read-in document.
	 */
	private void makeFrame() {

		getContentPane().removeAll();

		fileMenu.add(open);
		menuBar.add(fileMenu);
		setMenuBar(menuBar);

		pack();
		setVisible(true);
	}

	private CommandButton btnStop, btnNext, btnPlay, btnPrevious,
			btnAddToPlaylist, btnNewPlaylist;

	/**
	 * Displays the tree.
	 * 
	 * @param fileViewerTable
	 *            JTree to display
	 */
	public void display() {
		try {
			makeFrame();

			JScrollPane treeScrollPane = null;
			JScrollPane textScrollPane = null;

			// Build left-side view
			if (fileViewerTable != null) {
				fileViewerTable.setFont(treeFont);
				treeScrollPane = new JScrollPane(fileViewerTable);
				treeScrollPane.setPreferredSize(new Dimension(leftWidth,
						windowHeight));
			} else {
				JEditorPane errorMessagePane = new JEditorPane();
				errorMessagePane.setEditable(false);
				errorMessagePane.setContentType("text/plain");
				errorMessagePane
						.setText("Error: unable to build tree from xml:\n"
								+ exception.toString());
				errorMessagePane.setCaretPosition(0);
				treeScrollPane = new JScrollPane(errorMessagePane);
			}

			JPanel buttonPanel = new JPanel();
			BoxLayout objBoxLayout = new BoxLayout(buttonPanel,
					BoxLayout.PAGE_AXIS);
			buttonPanel.setLayout(objBoxLayout);
			buttonPanel.setBorder(BorderFactory
					.createTitledBorder(" Commands "));

			// JButton
			// btnStop,btnNext,btnPlay,btnPrevious,btnAddToPlaylist,btnNewPlaylist;

			btnStop = new CommandButton("Stop", Command.STOP, objPlayerUtility);
			buttonPanel.add(btnStop);

			btnNext = new CommandButton("Next", Command.NEXT, objPlayerUtility);
			buttonPanel.add(btnNext);

			// btnPlay=new JButton("Play");
			// buttonPanel.add(btnPlay);

			btnAddToPlaylist = new CommandButton("Add to Playlist",
					Command.BROWSE_ADD, objPlayerUtility);
			buttonPanel.add(btnAddToPlaylist);

			btnNewPlaylist = new CommandButton("New Playlist",
					Command.BROWSE_NEW, objPlayerUtility);
			buttonPanel.add(btnNewPlaylist);

			btnPrevious = new CommandButton("Previous", Command.PREVIOUS,
					objPlayerUtility);
			buttonPanel.add(btnPrevious);

			// btnStop=new JButton("Stop");
			// buttonPanel.add(new JButton("Next"));

			textScrollPane = new JScrollPane(buttonPanel);
			textScrollPane.setPreferredSize(new Dimension(rightWidth,
					windowHeight));

			// Build split-pane view
			JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
					treeScrollPane, textScrollPane);

			splitPane.setContinuousLayout(true);
			splitPane.setDividerLocation(leftWidth);
			// --- Extra line & configurations # start
			splitPane.setOneTouchExpandable(true);
			splitPane.setResizeWeight(0.5);
			// ---Extra line & configurations # end
			splitPane.setPreferredSize(new Dimension(windowWidth + 10,
					windowHeight + 10));

			// if (objPlayerUtility.getCustomPlaybackListener() != null) {
			// objPlayerUtility.getCustomPlaybackListener().setMp3Player(this);
			// }

			// Add GUI components
			setLayout(new BorderLayout());
			add("Center", splitPane);
			pack();
			setVisible(true);
		} catch (Exception e) {
			System.err.println("error when updating xml viewer");
			e.printStackTrace();
		}
	}

	/** listener for when user selects a file to view */
	private class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == open) {

				// objSoundJLayerLevelTwo = new PlayerUtility(null);

				objPlayerUtility.setFilePaths(objPlayerUtility
						.getBrowsedFiles());
				List<String> fileNames = new ArrayList<>();

				for (int i = 0; i < objPlayerUtility.getFilePaths().length; i++) {
					fileNames.add(objPlayerUtility.getFilePaths()[i]);
				}

				// reset for currently selected message
				exception = null;

				// remember last directory used
				fileChooser.setCurrentDirectory(file);

				fileViewerTable.setCaseDiaryVector(fileNames);
				fileViewerTable.scrollRectToVisible(new Rectangle(
						fileViewerTable.getCellRect(0, 0, true)));
				String name = fileViewerTable.getCaseDiaryVector().get(
						fileViewerTable.getSelectedRow());
				try {
					objPlayerUtility.stop();
					objPlayerUtility.play(Paths
							.get(new File(name).getAbsolutePath()).toUri()
							.toURL());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				// objCaseDiaryTable.sets

			}
		}
	}

}
