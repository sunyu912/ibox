/***************
 * CS585 Software Verification and validation  - Winter 2015
 * Programming Assignment 1
 * Auther: Alaa Hassarn Kassarah
 * Professor:Yu Sun
 * 
 *Description:This Program Testing an addFile Method from GoggleDriveFileSyncManagerwhich using 
 *(junit and Mockito).
 */


package edu.csupomona.cs585.ibox;


import java.io.IOException;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Insert;
import com.google.api.services.drive.model.File;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;



//Unit Test ... Don, Don!!!!!
public class GoogleDriveFileSyncManager_addFile_Test {

	GoogleDriveFileSyncManager GDFSM;
	java.io.File jFile;
	File file = new File();
	
	
	@Mock	
	Drive service;
	
	@Before
	public void setup() throws IOException {
		
		MockitoAnnotations.initMocks(this);
		GDFSM = new GoogleDriveFileSyncManager(service);
		jFile = mock(java.io.File.class);
		
		
	}
	
	
	@Test
	public void whenThenReturn() throws IOException {
	
		Insert insert = mock(Insert.class);
		Files files = mock(Files.class);
		
	// **************Stubbing**********************
		
		 when(service.files()).thenReturn(files);
		 when(files.insert(isA(File.class), isA(AbstractInputStreamContent.class))).thenReturn(insert);  //insert(file)).thenReturn(insert);//insert(file, null)).thenReturn(insert);
	     when(insert.execute()).thenReturn(file);
	     
	     
 //******************CallMethod***************
	    GDFSM.addFile(jFile);
	    
	  //******************Verifying***************
	    
	    Mockito.verify(service, atLeast(1)).files();
	    Mockito.verify(files, atLeast(1)).insert(isA(File.class), isA(AbstractInputStreamContent.class));
	    Mockito.verify(insert, atLeast(1)).execute();
	    
	   
	}
	

}