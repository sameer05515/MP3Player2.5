package com.prem.mp3.app.xml;

@SuppressWarnings("serial")
public class PlaylistException extends Exception {

	public enum Response {
		PLAYLIST_NAME_NULL("01001", "Playlist name is null"), PLAYLIST_NAME_EMPTY(
				"01002", "Playlist name is empty"), PLAYLIST_NAME_EXISTS(
				"01003", "Playlist name already exists"), PLAYLIST_NAME_MUST_NOT_CONTAIN_SPECIAL_CHARACTERS(
				"01004", "Playlist name must not contain special character"), PLAYLIST_NOT_EXISTS(
				"01005", "Playlist not exists"), PLAYLIST_NOT_EMPTY("01006",
				"Playlist is not empty"), SONG_NAME_NULL("02001",
				"Song name is null"), SONG_NAME_EMPTY("02002",
				"Song name is empty"), SONG_NAME_EXISTS("02003",
				"Song name already exists"), SONG_FILE_NOT_EXISTS("02004",
				"Song file not exists"), SONG_NAME_LIST_NULL("02005",
				"Song name list is null"), SONG_NAME_LIST_EMPTY("02006",
				"Song name list is empty"), SONG_DIRECTORY_NAME_NULL("03001",
				"Song directory name is null"), SONG_DIRECTORY_NAME_EMPTY(
				"03002", "Song directory name is empty"), SONG_DIRECTORY_NAME_EXISTS(
				"03003", "Song directory name already exists"), SONG_DIRECTORY_NOT_EXISTS(
				"03004", "Song directory not exists"), SONG_DIRECTORY_NAME_LIST_NULL(
				"03005", "Song directory name list is null"), SONG_DIRECTORY_NAME_LIST_EMPTY(
				"03006", "Song directory name list is empty");
		Response(String errorCode, String errorCodeDescription) {
			this.errorCode = errorCode;
			this.errorCodeDescription = errorCodeDescription;
		}

		private String errorCode;
		private String errorCodeDescription;

		public String getErrorCode() {
			return errorCode;
		}

		public String getErrorCodeDescription() {
			return errorCodeDescription;
		}
	}

	private String errorMessage;
	private Response errorResponse;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @param errorMessage
	 * @param errorResponse
	 */
	public PlaylistException(String errorMessage, Response errorResponse) {
		super();
		this.errorMessage = errorMessage;
		this.errorResponse = errorResponse;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(super.toString() != null ? " ERROR DESCRIPTION FROM PARENT CLASS : \""
				+ super.toString() + "\""
				: "");
		sb.append(" ERROR CODE : \"" + errorResponse.getErrorCode() + "\"");
		sb.append(" ERROR CODE DESCRIPTION : \""
				+ errorResponse.getErrorCodeDescription() + "\"");
		sb.append(getErrorMessage() != null ? " ERROR MESSAGE : \""
				+ getErrorMessage() + "\"" : "");
		sb.append("]");
		return sb.toString();
	}

	public Response getErrorResponse() {
		return errorResponse;
	}

}
