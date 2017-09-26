package com.prem.mp3.app.xml.util;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.prem.mp3.app.xml.PlaylistException;
import com.prem.mp3.app.xml.bo.PlaylistObject;

public class PlaylistActionsImpl implements PlaylistActions {

	@Override
	public List<PlaylistObject> getAllPlayList()
			throws ParserConfigurationException, SAXException, IOException,
			PlaylistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> addPlayList(String playlistName)
			throws ParserConfigurationException, SAXException, IOException,
			PlaylistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> removePlayList(String playlistName,boolean removeContent)
			throws ParserConfigurationException, SAXException, IOException,
			PlaylistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> editPlayList(String playlistOldName,
			String playlistNewName) throws ParserConfigurationException,
			SAXException, IOException, PlaylistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllSongs(String playListName)
			throws ParserConfigurationException, SAXException, IOException,
			PlaylistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> addSong(String playListName, String songName)
			throws ParserConfigurationException, SAXException, IOException,
			PlaylistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> removeSong(String playListName, String songName)
			throws ParserConfigurationException, SAXException, IOException,
			PlaylistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> addSongs(String playListName, List<String> songNames)
			throws ParserConfigurationException, SAXException, IOException,
			PlaylistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> removeSongs(String playListName, List<String> songNames)
			throws ParserConfigurationException, SAXException, IOException,
			PlaylistException {
		// TODO Auto-generated method stub
		return null;
	}

}
