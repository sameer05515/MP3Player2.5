package com.prem.mp3.app.xml.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.prem.mp3.app.xml.PlaylistException;
import com.prem.mp3.app.xml.PlaylistXMLConstants;
import com.prem.mp3.app.xml.PlaylistXMLConstants.File;
import com.prem.mp3.app.xml.PlaylistXMLConstants.Folder;
import com.prem.mp3.app.xml.PlaylistXMLConstants.Playlist;
import com.prem.mp3.app.xml.bo.PlaylistObject;
import com.prem.mp3.app.xml.bo.PlaylistObject.Song;
import com.prem.mp3.app.xml.bo.PlaylistObject.SongDirectory;
import com.prem.mp3.app.xml.bo.PlaylistObject.SongElement;

public class XMLUtilityImpl implements PlaylistActions {

	String xmlFileName = "D:/javaEclipseRoot/MP3Player2.5/playlist-xml/playlist.premxml";
	// SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy hh:mm:ss");

	XMLUtility utility = null;

	public XMLUtilityImpl() throws ParserConfigurationException, SAXException,
			IOException {
		utility = XMLUtility.getInstance(xmlFileName);
	}

	@Override
	public List<PlaylistObject> getAllPlayList()
			throws ParserConfigurationException, SAXException, IOException {
		List<PlaylistObject> objPlaylists = new ArrayList<PlaylistObject>();
		Element rootNode = utility.getRootNodeElement();
		Node playlistGroupNode = utility.getNthNodeOfType(
				PlaylistXMLConstants.PLAYLIST_GROUP, rootNode, 0);
		Element playlistGroupElement = utility.getElement(playlistGroupNode);

		NodeList playlistNodeList = utility.getAllNodesOfType(
				PlaylistXMLConstants.Playlist.node, playlistGroupElement);
		for (int playlistNodeCount = 0; playlistNodeCount < playlistNodeList
				.getLength(); playlistNodeCount++) {
			Node playlistNode = playlistNodeList.item(playlistNodeCount);
			Element playlistElement = utility.getElement(playlistNode);
			if (playlistElement != null) {
				String value = utility.getAttribute(Playlist.NAME,
						playlistElement);
				String createdOn = utility.getAttribute(Playlist.CREATED_ON,
						playlistElement);
				PlaylistObject objPlaylist = new PlaylistObject();
				objPlaylist.setName(value);
				objPlaylist.setCreatedOnStr(createdOn);
				// --songs or folders -- start
				NodeList songNodeList = utility.getAllNodesOfType(
						PlaylistXMLConstants.File.node, playlistElement);
				List<SongElement> objSongElements = new ArrayList<SongElement>();
				List<Song> objSongs = new ArrayList<Song>();
				for (int songCount = 0; songCount < songNodeList.getLength(); songCount++) {
					Node songNode = songNodeList.item(songCount);
					Element songElement = utility.getElement(songNode);
					if (songElement != null) {
						Song objSong = new Song(utility.getAttribute(File.URL,
								playlistElement));
						objSongs.add(objSong);
						objSongElements.add(objSong);
					}
				}
				objPlaylist.setListSong(objSongs);
				// --songs or folders -- end

				// --songs or folders -- start
				NodeList songDirectoryNodeList = utility.getAllNodesOfType(
						PlaylistXMLConstants.Folder.node, playlistElement);
				List<SongDirectory> objSongDirectories = new ArrayList<SongDirectory>();
				for (int songCount = 0; songCount < songDirectoryNodeList
						.getLength(); songCount++) {
					Node songNode = songDirectoryNodeList.item(songCount);
					Element songElement = utility.getElement(songNode);
					if (songElement != null) {
						SongDirectory objSongDirectory = new SongDirectory(
								utility.getAttribute(Folder.URL,
										playlistElement), utility.getAttribute(
										Folder.URL, playlistElement));
						objSongDirectories.add(objSongDirectory);
						objSongElements.add(objSongDirectory);
					}
				}
				objPlaylist.setListSongDirectory(objSongDirectories);
				objPlaylist.setListElement(objSongElements);
				objPlaylists.add(objPlaylist);
				// --songs or folders -- end

				// System.out.println(objPlaylist);
			}
		}

		// System.out.println(utility.getXMLString());
		return objPlaylists;
	}

	@Override
	public List<String> addPlayList(String playlistName)
			throws ParserConfigurationException, SAXException, IOException,
			PlaylistException {
		List<PlaylistObject> objPlaylists = new ArrayList<PlaylistObject>();

		/**
		 * -- ## Validation ## -- start --
		 */
		if (playlistName == null) {
			throw new PlaylistException(
					"Please provide some valid playlist name",
					PlaylistException.Response.PLAYLIST_NAME_NULL);
		}
		if (playlistName.trim().equalsIgnoreCase("")) {
			throw new PlaylistException(
					"Please provide some valid playlist name",
					PlaylistException.Response.PLAYLIST_NAME_EMPTY);
		} else {
			playlistName = playlistName.trim();
		}
		int pointer = 0;
		while (pointer < playlistName.length()) {
			char charac = playlistName.charAt(pointer);
			boolean valid = (charac >= 'a' && charac <= 'z')
					|| (charac >= 'A' && charac <= 'Z') || charac == '1'
					|| charac == '2' || charac == '3' || charac == '4'
					|| charac == '5' || charac == '6' || charac == '7'
					|| charac == '8' || charac == '9' || charac == '0'
					|| charac == '_';
			if (!valid) {
				throw new PlaylistException(
						"Please provide some valid playlist name, illegal character encountered : [\""
								+ charac + "\"] ",
						PlaylistException.Response.PLAYLIST_NAME_MUST_NOT_CONTAIN_SPECIAL_CHARACTERS);
			}
			pointer++;
		}

		if (ifExistsPlaylist(playlistName)) {
			throw new PlaylistException("Playlist with given name exists - [\""
					+ playlistName + "\"]",
					PlaylistException.Response.PLAYLIST_NAME_EXISTS);
		}

		/**
		 * -- ## Validation ## -- end --
		 * */

		Element rootNode = utility.getRootNodeElement();
		Node playlistGroupNode = utility.getNthNodeOfType(
				PlaylistXMLConstants.PLAYLIST_GROUP, rootNode, 0);
		Element playlistGroupElement = utility.getElement(playlistGroupNode);

		Element playlistElement = utility
				.createElement(PlaylistXMLConstants.Playlist.node);
		playlistElement = utility.setAttribute(Playlist.NAME, playlistElement,
				playlistName, false);
		playlistElement = utility.setAttribute(Playlist.CREATED_ON,
				playlistElement, System.currentTimeMillis() + "", false);

		playlistGroupElement.appendChild(playlistElement);
		rootNode.appendChild(playlistGroupElement);

		utility.printToFile();
		return null;
	}

	private boolean ifExistsPlaylist(String playlistName) {

		Element rootNode = utility.getRootNodeElement();
		Node playlistGroupNode = utility.getNthNodeOfType(
				PlaylistXMLConstants.PLAYLIST_GROUP, rootNode, 0);
		Element playlistGroupElement = utility.getElement(playlistGroupNode);

		NodeList playlistNodeList = utility.getAllNodesOfType(
				PlaylistXMLConstants.Playlist.node, playlistGroupElement);
		for (int playlistNodeCount = 0; playlistNodeCount < playlistNodeList
				.getLength(); playlistNodeCount++) {
			Node playlistNode = playlistNodeList.item(playlistNodeCount);
			Element playlistElement = utility.getElement(playlistNode);
			if (playlistElement != null) {
				String value = utility.getAttribute(Playlist.NAME,
						playlistElement);
				if (value.equalsIgnoreCase(playlistName)) {
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public List<String> removePlayList(String playlistName,
			boolean removeContent) throws ParserConfigurationException,
			SAXException, IOException, PlaylistException {
		// TODO Auto-generated method stub
		/**
		 * -- ## Validation ## -- start --
		 */
		if (playlistName == null) {
			throw new PlaylistException(
					"Please provide some valid playlist name",
					PlaylistException.Response.PLAYLIST_NAME_NULL);
		}
		if (playlistName.trim().equalsIgnoreCase("")) {
			throw new PlaylistException(
					"Please provide some valid playlist name",
					PlaylistException.Response.PLAYLIST_NAME_EMPTY);
		} else {
			playlistName = playlistName.trim();
		}

		if (!ifExistsPlaylist(playlistName)) {
			throw new PlaylistException(
					"Playlist with given name not exists - [\"" + playlistName
							+ "\"]",
					PlaylistException.Response.PLAYLIST_NOT_EXISTS);
		}

		/**
		 * -- ## Validation ## -- end --
		 * */
		Element rootNode = utility.getRootNodeElement();
		Node playlistGroupNode = utility.getNthNodeOfType(
				PlaylistXMLConstants.PLAYLIST_GROUP, rootNode, 0);
		Element playlistGroupElement = utility.getElement(playlistGroupNode);

		NodeList playlistNodeList = utility.getAllNodesOfType(
				PlaylistXMLConstants.Playlist.node, playlistGroupElement);
		for (int playlistNodeCount = 0; playlistNodeCount < playlistNodeList
				.getLength(); playlistNodeCount++) {
			Node playlistNode = playlistNodeList.item(playlistNodeCount);
			Element playlistElement = utility.getElement(playlistNode);
			if (playlistElement != null) {
				String value = utility.getAttribute(Playlist.NAME,
						playlistElement);
				if (value.equalsIgnoreCase(playlistName)) {

					// --songs or folders -- start
					NodeList songNodeList = utility.getAllNodesOfType(
							PlaylistXMLConstants.File.node, playlistElement);
					NodeList songDirectoryNodeList = utility.getAllNodesOfType(
							PlaylistXMLConstants.Folder.node, playlistElement);
					if(!removeContent){
					if ((songNodeList != null && songNodeList.getLength() > 0)
							|| (songDirectoryNodeList != null && songDirectoryNodeList
									.getLength() > 0)) {
						throw new PlaylistException(
								"Playlist is not empty. Also permission to delete content not granted, i.e. - [\""
										+ (removeContent) + "\"]",
								PlaylistException.Response.PLAYLIST_NOT_EMPTY);
					}
					}

					playlistGroupElement.removeChild(playlistElement);
					break;

				}
			}
		}

		rootNode.appendChild(playlistGroupElement);
		
		utility.printToFile();

		return null;
	}

	@Override
	public List<String> editPlayList(String playlistOldName,
			String playlistNewName) throws ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllSongs(String playListName)
			throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> addSong(String playListName, String songName)
			throws ParserConfigurationException, SAXException, IOException,
			PlaylistException {
		/**
		 * -- ## Validation ## -- start --
		 */
		if (playListName == null) {
			throw new PlaylistException(
					"Please provide some valid playlist name",
					PlaylistException.Response.PLAYLIST_NAME_NULL);
		}
		if (playListName.trim().equalsIgnoreCase("")) {
			throw new PlaylistException(
					"Please provide some valid playlist name",
					PlaylistException.Response.PLAYLIST_NAME_EMPTY);
		} else {
			playListName = playListName.trim();
		}

		if (songName == null) {
			throw new PlaylistException("Please provide some valid song name",
					PlaylistException.Response.SONG_NAME_NULL);
		}
		if (songName.trim().equalsIgnoreCase("")) {
			throw new PlaylistException("Please provide some valid song name",
					PlaylistException.Response.SONG_NAME_EMPTY);
		} else {
			songName = songName.trim();
		}

		if (!new java.io.File(songName).exists()) {
			throw new PlaylistException("Song file does not exists [\""
					+ songName + "\"]",
					PlaylistException.Response.SONG_FILE_NOT_EXISTS);
		}

		if (ifExistsSong(songName, playListName)) {
			throw new PlaylistException("Song with given name exists - [\""
					+ songName + "\"]",
					PlaylistException.Response.SONG_NAME_EXISTS);
		}

		/**
		 * -- ## Validation ## -- end --
		 * */

		Element rootNode = utility.getRootNodeElement();
		Node playlistGroupNode = utility.getNthNodeOfType(
				PlaylistXMLConstants.PLAYLIST_GROUP, rootNode, 0);
		Element playlistGroupElement = utility.getElement(playlistGroupNode);

		NodeList playlistNodeList = utility.getAllNodesOfType(
				PlaylistXMLConstants.Playlist.node, playlistGroupElement);
		for (int playlistNodeCount = 0; playlistNodeCount < playlistNodeList
				.getLength(); playlistNodeCount++) {
			Node playlistNode = playlistNodeList.item(playlistNodeCount);
			Element playlistElement = utility.getElement(playlistNode);
			if (playlistElement != null) {
				String value = utility.getAttribute(Playlist.NAME,
						playlistElement);
				if (value.equalsIgnoreCase(playListName)) {

					// --songs or folders -- start
					NodeList songNodeList = utility.getAllNodesOfType(
							PlaylistXMLConstants.File.node, playlistElement);
					Element songElement = utility
							.createElement(PlaylistXMLConstants.File.node);
					songElement = utility.setAttribute(File.URL, songElement,
							songName, false);
					playlistElement.appendChild(songElement);
					playlistGroupElement.appendChild(playlistElement);

				}
			}
		}

		rootNode.appendChild(playlistGroupElement);

		utility.printToFile();
		return null;
	}

	private boolean ifExistsSong(String songName, String playListName) {
		Element rootNode = utility.getRootNodeElement();
		Node playlistGroupNode = utility.getNthNodeOfType(
				PlaylistXMLConstants.PLAYLIST_GROUP, rootNode, 0);
		Element playlistGroupElement = utility.getElement(playlistGroupNode);

		NodeList playlistNodeList = utility.getAllNodesOfType(
				PlaylistXMLConstants.Playlist.node, playlistGroupElement);
		for (int playlistNodeCount = 0; playlistNodeCount < playlistNodeList
				.getLength(); playlistNodeCount++) {
			Node playlistNode = playlistNodeList.item(playlistNodeCount);
			Element playlistElement = utility.getElement(playlistNode);
			if (playlistElement != null) {
				String value = utility.getAttribute(Playlist.NAME,
						playlistElement);
				if (value.equalsIgnoreCase(playListName)) {

					// --songs or folders -- start
					NodeList songNodeList = utility.getAllNodesOfType(
							PlaylistXMLConstants.File.node, playlistElement);
					for (int songCount = 0; songCount < songNodeList
							.getLength(); songCount++) {
						Node songNode = songNodeList.item(songCount);
						Element songElement = utility.getElement(songNode);
						if (songElement != null) {
							if (utility.getAttribute(File.URL, songElement)
									.equalsIgnoreCase(songName)) {
								return true;
							}
						}
					}

				}
			}

		}
		return false;
	}

	@Override
	public List<String> removeSong(String playListName, String songName)
			throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> addSongs(String playListName, List<String> songNames)
			throws ParserConfigurationException, SAXException, IOException,
			PlaylistException {
		if (songNames == null) {
			throw new PlaylistException("Song name list provided is null",
					PlaylistException.Response.SONG_NAME_LIST_NULL);
		}
		if (songNames.size() <= 0) {
			throw new PlaylistException("Song name list provided is empty",
					PlaylistException.Response.SONG_NAME_LIST_EMPTY);
		}
		for (String songName : songNames) {
			try {
				addSong(playListName, songName);
			} catch (ParserConfigurationException e) {
				System.out.println("ParserConfigurationException " + e);
				e.printStackTrace();
				throw e;
			} catch (SAXException e) {
				System.out.println("SAXException " + e);
				e.printStackTrace();
				throw e;
			} catch (IOException e) {
				System.out.println("IOException " + e);
				e.printStackTrace();
				throw e;
			} catch (PlaylistException e) {
				System.out.println("PlaylistException " + e);
				if (e.getErrorResponse().equals(
						PlaylistException.Response.PLAYLIST_NAME_EMPTY)) {
					throw e;
				}
				if (e.getErrorResponse().equals(
						PlaylistException.Response.PLAYLIST_NAME_NULL)) {
					throw e;
				}
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<String> removeSongs(String playListName, List<String> songNames)
			throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
