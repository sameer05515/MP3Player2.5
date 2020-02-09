package com.prem.mp3.app.xml;

public enum PlaylistXMLConstants implements XMLBasicMethodsRequired {

	ROOT("playlist-root", Type.NODE), PLAYLIST_GROUP("playlist-group", Type.NODE);

	private String name;
	private Type type;

	PlaylistXMLConstants(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return type;
	}

	// ----------- XML Element types -----------

	public enum Type {
		NODE("node"), ATTRIBUTE("attribute"), COMMENT("comment"), CDATA("cdata"), SUB_NODE(
				"subNode");

		Type(String name) {
			this.name = name;
		}

		private String name;

		public String getName() {
			return name;
		}
	}

	// ----------- Playlist -------------
	public enum Playlist implements XMLBasicMethodsRequired {
		node("playlist", Type.NODE), NAME("name", Type.ATTRIBUTE), CREATED_ON(
				"createdOn", Type.ATTRIBUTE);

		private String name;
		private Type type;

		Playlist(String name, Type type) {
			this.name = name;
			this.type = type;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public Type getType() {
			return type;
		}
	}

	// ----------- File -------------
	public enum File implements XMLBasicMethodsRequired {
		node("file", Type.NODE), URL("url", Type.ATTRIBUTE);

		private String name;
		private Type type;

		File(String name, Type type) {
			this.name = name;
			this.type = type;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public Type getType() {
			return type;
		}
	}

	// ----------- Folder -------------
	public enum Folder implements XMLBasicMethodsRequired {
		node("folder", Type.NODE), URL("url", Type.ATTRIBUTE), FILE_TYPE(
				"fileType", Type.ATTRIBUTE);

		private String name;
		private Type type;

		Folder(String name, Type type) {
			this.name = name;
			this.type = type;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public Type getType() {
			return type;
		}
	}

}
