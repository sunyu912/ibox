/***************
 * CS585 Software Verification and validation  - Winter 2015
 * Programming Assignment 1
 * Auther: Alaa Hassarn Kassarah
 * Professor:Yu Sun
 */

package edu.csupomona.cs585.ibox;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.List;
import com.google.api.services.drive.Drive.Files.Update;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;

//Unit Test...Don!!!!
public class GoogleDriveFileSyncManager_updateFile_Test {

	
	
	@Mock
	Drive service;
	
	GoogleDriveFileSyncManager GDFSM;
	File file;
	String fname;
	java.io.File jFile;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		service = mock(Drive.class);
		file = new File();
		GDFSM = new GoogleDriveFileSyncManager(service);
		jFile = mock(java.io.File.class);
		fname = new String("alaa.txt");
			}

	
	@Test
  public void Test() throws IOException{
		
		
		 //************initializing*********
			Update update = mock(Update.class);
			Files files = mock(Files.class);
			List list = mock(List.class);
			FileList f = new FileList();
			File sfile= new File();
			java.util.List<File> fileList = new ArrayList<File>();
			
		// Initialization
		file.setTitle("alaa.txt");
		file.setId("1W345");
		fileList.add(file); 
		f.setItems(fileList);
		
			
			//************Stubbing**************** 
		
		 when (jFile.getName()).thenReturn(fname);
		 when(service.files()).thenReturn(files);
		 when(files.list()).thenReturn(list);
	     when (list.execute()).thenReturn(f);
	     when(files.update(isA(String.class), isA(File.class),
	    		 isA(AbstractInputStreamContent.class))).thenReturn(update); 
	     when(update.execute()).thenReturn(sfile);
		
			
		   //********call method********
		     
			   GDFSM.updateFile(jFile);
		     
		  // *************verifying*******************
	      Mockito.verify(jFile, atLeast(1)).getName();   
		  Mockito.verify(service, atLeast(1)).files();
		  Mockito.verify(files, atLeast(1)).list();//update(file.getId(), file,null);
		  Mockito.verify(list, atLeast(1)).execute();
		  Mockito.verify(files, atLeast(1)).update(isA(String.class), isA(File.class),
		    		 isA(AbstractInputStreamContent.class)); 
		  Mockito.verify(update, atLeast(1)).execute();
		    
		   
		} 
	
	@Test(expected = Exception.class)  
	public void update_throwExeption() throws IOException{
		
		//******Mocking**and**Initialization**************
		
		String name = new String("NewFile.txt");
	    java.io.File NewFile =new java.io.File(name);
	     String fileId = GDFSM.getFileId(name);
	     
		//************Stubbing****************
	     
		when (jFile.getName()).thenReturn(name);
		when (fileId.equals(null)).thenReturn(true);
		GDFSM.addFile(NewFile);
		
		//********call method********
	     
	   GDFSM.updateFile(jFile);
		
		// *************verifying*******************
	   Mockito.verify(jFile, atLeast(1)).getName();
		
		    
		
	}
	   
  }


