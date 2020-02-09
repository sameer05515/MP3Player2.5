package com.prem.mp3.app.xml.watcher;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException,
			InterruptedException {

		MonitorDirectory md = new MonitorDirectory(
				"C:/Users/VINU/Desktop/123/folderScans");
		System.out.println("starting watch");
		md.startWatch();
		System.out.println("watch started");

		Thread.sleep(10000);
		System.out.println("stopping watch");
		md.stopWatch();

		// Path faxFolder = Paths.get("C:/Users/VINU/Desktop/123/folderScans");
		// SimpleDateFormat sdf = new
		// SimpleDateFormat("dd-MMMM-yyyy hh:mm:ss aaa");
		// WatchService watchService =
		// FileSystems.getDefault().newWatchService();
		// WatchEvent.Kind<?>[] events = { StandardWatchEventKinds.ENTRY_CREATE,
		// StandardWatchEventKinds.ENTRY_MODIFY,
		// StandardWatchEventKinds.ENTRY_DELETE,
		// StandardWatchEventKinds.OVERFLOW };
		// faxFolder.register(watchService, events);
		//
		// boolean valid = true;
		// do {
		// WatchKey watchKey = watchService.take();
		//
		// for (WatchEvent<?> event : watchKey.pollEvents()) {
		// WatchEvent.Kind<?> kind = event.kind();
		// String fileName = event.context() != null ? event.context()
		// .toString() : "file name not found";
		// if (StandardWatchEventKinds.ENTRY_CREATE.equals(kind)) {
		// // String fileName =
		// //
		// event.context()!=null?event.context().toString():"file name not found";
		// System.out.print("File Created:" + fileName);
		// } else if (StandardWatchEventKinds.ENTRY_MODIFY.equals(kind)) {
		// // String fileName = event.context().toString();
		// System.out.print("File Modified:" + fileName);
		// } else if (StandardWatchEventKinds.ENTRY_DELETE.equals(kind)) {
		// // String fileName = event.context().toString();
		// System.out.print("File Deleted:" + fileName);
		// } else if (StandardWatchEventKinds.OVERFLOW.equals(kind)) {
		// // String fileName = event.context().toString();
		// System.out.print("File Overflowed:" + fileName);
		// }
		// System.out.println("(" + sdf.format(new Date()) + " )");
		// }
		// valid = watchKey.reset();
		//
		// } while (valid);

	}

}
