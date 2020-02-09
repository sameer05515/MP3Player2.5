package com.prem.mp3.playlist;

import java.util.List;

public interface PlaylistActions {

	String playlistFileName = "D:/javaEclipseRoot/MP3Player/playlist-src/playlist.xml";

	public List<String> getAllPlayList();

	public List<String> addPlayList(String playlistName);

	public List<String> removePlayList(String playlistName);

	public List<String> editPlayList(String playlistOldName,
			String playlistNewName);

	// --------------------------------------------------------
	public List<String> getAllSongs(String playListName);

	public List<String> addSong(String playListName, String songName);

	public List<String> removeSong(String playListName, String songName);

	public List<String> addSongs(String playListName, List<String> songNames);

	public List<String> removeSongs(String playListName, List<String> songNames);

}
