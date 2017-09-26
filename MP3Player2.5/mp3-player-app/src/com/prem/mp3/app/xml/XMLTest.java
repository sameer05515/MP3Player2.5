package com.prem.mp3.app.xml;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.prem.mp3.app.xml.util.XMLUtilityImpl;

public class XMLTest {

	/**
	 * @param args
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public static void main(String[] args) {
		try {
			XMLUtilityImpl xmlUtilityImpl = new XMLUtilityImpl();

			xmlUtilityImpl.getAllPlayList();
			// xmlUtilityImpl.addPlayList("firstPlaylist"+System.currentTimeMillis());
			// xmlUtilityImpl.addPlayList(null);
			// xmlUtilityImpl.addPlayList("      ");
			// xmlUtilityImpl.addPlayList("firstPlaylist1413127169212");
			// xmlUtilityImpl.addSong("firstPlaylist1413127169212",
			// "D:/songs/1920 evil returns/ERKhudKoTereMPKhanCom.mp3");

			// ------------------ start
			// List<String> songNames = new ArrayList<String>();
			// File f = new File("D:/songs/Jab_Tak_Hai_Jaan_2012");
			// File[] farr = f.listFiles();
			// for (File ch : farr) {
			// if (ch != null && ch.isFile()) {
			// songNames.add(ch.getAbsolutePath().replace('\\', '/'));
			// // break;
			// }
			// }
			// System.out.println(songNames);
			// xmlUtilityImpl.addSongs("firstPlaylist1413127169212", songNames);
			// ------------------ end
			
			xmlUtilityImpl.removePlayList("testplaylist2", false);

			System.out.println(xmlUtilityImpl.getAllPlayList());
		} catch (ParserConfigurationException e) {
			System.out.println("ParserConfigurationException " + e);
			e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("SAXException " + e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException " + e);
			e.printStackTrace();
		} catch (PlaylistException e) {
			System.out.println("PlaylistException " + e);
			e.printStackTrace();
		}

	}

}
