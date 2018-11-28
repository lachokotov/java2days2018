package com.java2days.file.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class JavaFileCopy {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		File inboxDirectory = new File("data/inbox");
		File outboxDirectory = new File("data/outbox");
		outboxDirectory.mkdir();
		File[] files = inboxDirectory.listFiles();
		for (File source : files) {
			if (source.isFile()) {
				File dest = new File(outboxDirectory.getPath() + File.separator + source.getName());
				copyFile(source, dest);
			}
		}
		 WatchService watchService
         = FileSystems.getDefault().newWatchService();

       Path path = Paths.get("data/inbox");

       path.register(
         watchService, 
           StandardWatchEventKinds.ENTRY_CREATE, 
             StandardWatchEventKinds.ENTRY_DELETE, 
               StandardWatchEventKinds.ENTRY_MODIFY);

       WatchKey key;
       while ((key = watchService.take()) != null) {
           for (WatchEvent<?> event : key.pollEvents()) {
               System.out.println(
                 "Event kind:" + event.kind() 
                   + ". File affected: " + event.context() + ".");
           }
           key.reset();
       }
	}

	private static void copyFile(File source, File dest) throws IOException {
		OutputStream out = new FileOutputStream(dest);
		byte[] buffer = new byte[(int) source.length()];
		FileInputStream in = new FileInputStream(source);
		in.read(buffer);
		try {
			out.write(buffer);
		} finally {
			out.close();
			in.close();
		}
	}
}
