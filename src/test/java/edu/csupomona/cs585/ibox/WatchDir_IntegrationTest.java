/***************
 * CS585 Software Verification and validation  - Winter 2015
 * Programming Assignment 1
 * Auther: Alaa Hassarn Kassarah
 * Professor:Yu Sun
 * 
 *Description:This Program build an Integration Test for WatchDie class.
 */





/**
 *  public WatchDir(Path dir, FileSyncManager fileSyncManager) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.fileSyncManager = fileSyncManager;
        this.keys = new HashMap<WatchKey,Path>();

        register(dir);

        // enable trace after initial registration
        this.trace = true;
    }
 */


package edu.csupomona.cs585.ibox;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Before;
import org.junit.Test;

import com.google.api.services.drive.Drive;

import edu.csupomona.cs585.ibox.sync.FileSyncManager;
import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;
import edu.csupomona.cs585.ibox.sync.GoogleDriveServiceProvider;

public class WatchDir_IntegrationTest {
	
	
	
	//********** Declaration *******
	 
	   Drive service;
	   WatchService localservice;
	   WatchDir directory;
	   Path fpath;
	   FileSyncManager fileSyncManager;
	   WatchKey key ;
	   FileSystem fileSystem;
       java.io.File file;
       Toolkit toolkit;
       Timer timer;
       TimerTask time;

	@Before
	public void setup() throws IOException, InterruptedException{
		fileSystem = FileSystems.getDefault();

		service =GoogleDriveServiceProvider.get().getGoogleDriveClient() ;
		fileSyncManager = new GoogleDriveFileSyncManager(service);
		fpath =Paths.get("/Users/alaakassarah");  
		localservice =fpath.getFileSystem().newWatchService();//fileSystem.newWatchService();
		 
		directory = new WatchDir(fpath,fileSyncManager );
		 key =  fpath.register(localservice, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);//localservice.take();
		file =new File("Alaa.txt");
		toolkit = Toolkit.getDefaultToolkit();
	     timer = new Timer();
	    
		 
	 }
		

		

	@Test
	public void processEventIntegrationTest() throws IOException{
		
		directory.processEvents();
		timer.schedule(time, 5 * 1000);
		 
	}
		
	
		
	}
	
