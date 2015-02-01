/***************
 * CS585 Software Verification and validation  - Winter 2015
 * Programming Assignment 1
 * Auther: Alaa Hassarn Kassarah
 * Professor:Yu Sun
 */




package edu.csupomona.cs585.ibox;

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

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Delete;
import com.google.api.services.drive.Drive.Files.List;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;

//unit Test ...Don!!!

public class GoogleDriveFileSyncManager_deleteFile_Test {

	@Mock
	Drive service;
    java.io.File jFile;
	String fileId ;
	String fname;
	File file;
	GoogleDriveFileSyncManager GDFSM;
	
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		GDFSM = new GoogleDriveFileSyncManager(service);
		jFile = mock(java.io.File.class);
		file=new File();
		fileId = new String("1W345");
		fname = new String("alaa.txt");
	}
	
	
	
	@Test
  public void Test() throws IOException{
		
		
//***********Mocking and Initialization********
		Files files = mock(Files.class);
		Delete delete = mock(Delete.class);
		List list = mock(List.class);
		FileList f = new FileList();
		java.util.List<File> fileList = new ArrayList<File>();
		
	     file.setTitle("alaa.txt");
	     file.setId("1W345");
	     fileList.add(file);
	     f.setItems(fileList);
	     
		//************Stubbing****************
	     when (jFile.getName()).thenReturn(fname);
		 when(service.files()).thenReturn(files);
		 when(files.list()).thenReturn(list);
	     when (list.execute()).thenReturn(f);
		when(files.delete(fileId)).thenReturn(delete);
		when(delete.execute()).thenReturn(null);
		
		//********call method********
		
				GDFSM.deleteFile(jFile);
		  
		// *************verifying******************* 
		    Mockito.verify(jFile, atLeast(1)).getName();
		    Mockito.verify(service, atLeast(1)).files();
		    Mockito.verify(files, atLeast(1)).list();
		    Mockito.verify(list, atLeast(1)).execute();
		    Mockito.verify(files, atLeast(1)).delete(fileId);
		    Mockito.verify(delete, atLeast(1)).execute();
		    
  }
	
	
	
	@Test(expected = Exception.class) 
	public void throwExeption() throws IOException{
		
		
		String name = new String();
		//************Stubbing****************
		
		 when (jFile.getName()).thenReturn(name);
		 GDFSM.getFileId(name);
		
		
		//********call method********
		
		GDFSM.deleteFile(jFile);
		
	// *************verifying*******************

		Mockito.verify(jFile, atLeast(1)).getName();
	   
		    
		
	}

}
