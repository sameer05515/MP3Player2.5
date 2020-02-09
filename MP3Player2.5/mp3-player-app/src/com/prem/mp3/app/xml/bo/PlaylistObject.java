package com.prem.mp3.app.xml.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PlaylistObject {

	private String name;
	private String createdOnStr = "";
	private Date createdOn;

	public static final SimpleDateFormat sdf = new SimpleDateFormat(
			"dd-MMMM-yyyy hh:mm:ss");

	private List<SongElement> listElement;
	private List<Song> listSong;
	private List<SongDirectory> listSongDirectory;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedOnStr() {
		return createdOnStr;
	}

	public void setCreatedOnStr(String createdOnStr) {
		Long dateLong = Long.parseLong(createdOnStr);
		Date dateObj = new Date(dateLong);
		setCreatedOn(dateObj);
		this.createdOnStr = sdf.format(dateObj);
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	private void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public List<SongElement> getListElement() {
		return listElement;
	}

	public void setListElement(List<SongElement> listElement) {
		this.listElement = listElement;
	}

	public List<Song> getListSong() {
		return listSong;
	}

	public void setListSong(List<Song> listSong) {
		this.listSong = listSong;
	}

	public List<SongDirectory> getListSongDirectory() {
		return listSongDirectory;
	}

	public void setListSongDirectory(List<SongDirectory> listSongDirectory) {
		this.listSongDirectory = listSongDirectory;
	}

	public static class Song implements SongElement {
		private String url;

		public Song(String url) {
			this.url = url;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append(" Song url == " + url);
			sb.append("}");
			return sb.toString();
		}

	}

	public static class SongDirectory implements SongElement {
		private String url;
		private String fileType;

		public SongDirectory(String url, String fileType) {
			this.url = url;
			this.fileType = fileType;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getFileType() {
			return fileType;
		}

		public void setFileType(String fileType) {
			this.fileType = fileType;
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append(" SongDirectory url == " + url);
			sb.append(" fileType == " + fileType);
			sb.append("}");
			return sb.toString();
		}
	}

	public static interface SongElement {

		public String getUrl();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("\n{");
		sb.append("\nPlaylist name == " + name);
		sb.append(" createdOn " + createdOnStr);
		sb.append("\nsongs : -- ");
		for (Song song : listSong) {
			sb.append("\n\t\t" + song);
		}
		sb.append("\nsongDirectories : --  ");
		for (SongDirectory songDirectory : listSongDirectory) {
			sb.append("\n\t\t" + songDirectory);
		}
		sb.append("\n}");

		return sb.toString();
	}

}
