
/*
 * CS585 Software Verification and validation  - Winter 2015
 * Programming Assignment 1
 * Auther: Alaa Hassarn Kassarah
 * Professor:Yu Sun
 *Description:This Program Build an Integration Test for GoggleDriveFileSyncManagerwhich using 
 */

//Integration Test ....Don!!!

package edu.csupomona.cs585.ibox;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.api.services.drive.Drive;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;
import edu.csupomona.cs585.ibox.sync.GoogleDriveServiceProvider;

public class GoogleDriveFileSyncManagerInegrationTest {
	
	Drive service; 
	GoogleDriveFileSyncManager GDFSM;
	File localFile;
	
	
	@Before
	public void setup(){
		service =GoogleDriveServiceProvider.get().getGoogleDriveClient() ; 
		
		 GDFSM =new GoogleDriveFileSyncManager(service);
		 localFile = new File("/Users/alaakassarah/Alaa_Test3.rtf");
		 System.out.println(localFile.exists());
	}
	
	

	@Test
	public void addFileIntegrationTest() throws IOException, Exception {
	
		GDFSM.addFile(localFile);
	System.out.println(GDFSM.getFileId("Alaa_Test3.rtf"));
		 
	}
	
	
	@Test
	public void deleteFileIntegrationTest() throws IOException{
		
				System.out.println("Now delete");
				GDFSM.deleteFile(localFile);
				String id = GDFSM.getFileId(localFile.getName());
				Assert.assertNull(id);
			
		}
	

	@Test
	public void updateFileIntegrationTest() throws IOException{
		
		GDFSM.updateFile(localFile);
		GDFSM.getFileId("Alaa_Test3.rtf");
	
	}
	
	

}
