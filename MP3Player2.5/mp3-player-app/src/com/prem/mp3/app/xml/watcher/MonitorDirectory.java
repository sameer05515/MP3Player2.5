package com.prem.mp3.app.xml.watcher;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MonitorDirectory {

	/**
	 * @param directoryToWatch
	 */
	public MonitorDirectory(String directoryToWatch) {
		super();
		this.directoryToWatch = directoryToWatch;
	}

	private String directoryToWatch = "";

	boolean valid = false;

	public void startWatch() throws IOException, InterruptedException {
		Path faxFolder = Paths.get(directoryToWatch);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy hh:mm:ss aaa");
		WatchService watchService = FileSystems.getDefault().newWatchService();
		WatchEvent.Kind<?>[] events = { StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_MODIFY,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.OVERFLOW };
		faxFolder.register(watchService, events);

		valid = true;
		boolean furtherWatchingNeeded = valid;
		do {
			WatchKey watchKey = watchService.take();

			for (WatchEvent<?> event : watchKey.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();
				String fileName = event.context() != null ? event.context()
						.toString() : "file name not found";
				if (StandardWatchEventKinds.ENTRY_CREATE.equals(kind)) {
					// String fileName =
					// event.context()!=null?event.context().toString():"file name not found";
					System.out.print("File Created:" + fileName);
				} else if (StandardWatchEventKinds.ENTRY_MODIFY.equals(kind)) {
					// String fileName = event.context().toString();
					System.out.print("File Modified:" + fileName);
				} else if (StandardWatchEventKinds.ENTRY_DELETE.equals(kind)) {
					// String fileName = event.context().toString();
					System.out.print("File Deleted:" + fileName);
				} else if (StandardWatchEventKinds.OVERFLOW.equals(kind)) {
					// String fileName = event.context().toString();
					System.out.print("File Overflowed:" + fileName);
				}
				System.out.println("(" + sdf.format(new Date()) + " )");
			}
			// valid = watchKey.reset();
			furtherWatchingNeeded = valid && watchKey.reset();

		} while (furtherWatchingNeeded);
	}

	public void stopWatch() {
		valid = false;
	}

	public String getDirectoryToWatch() {
		return directoryToWatch;
	}

	public void setDirectoryToWatch(String directoryToWatch) {
		this.directoryToWatch = directoryToWatch;
	}
}