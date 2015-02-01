package edu.csupomona.cs585.ibox.sync;

import static org.mockito.Mockito.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Delete;
import com.google.api.services.drive.Drive.Files.Insert;
import com.google.api.services.drive.Drive.Files.List;
import com.google.api.services.drive.Drive.Files.Update;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;
import static org.junit.Assert.*;



public class Google_temp {

	
		GoogleDriveFileSyncManager MockGoogleDrive;
		Drive MockDriveService;
		java.io.File localFile;
		File body = new File();
		

		@Mock 
		Files files = mock(Files.class);
		Insert insert = mock(Insert.class);
		Update update = mock(Update.class);
		Delete delete = mock(Delete.class);
		List list = mock(List.class);
		
		
		@Before
		public void setUp() throws Exception {
			MockitoAnnotations.initMocks(this);
			MockDriveService = mock(Drive.class);
			MockGoogleDrive = new GoogleDriveFileSyncManager(MockDriveService);
			localFile = mock(java.io.File.class);
			
		}

		@Test
		public void testAddFile() throws IOException {
			//Stubbing
			when(MockDriveService.files()).thenReturn(files);
			when(files.insert(isA(File.class), isA(AbstractInputStreamContent.class))).thenReturn(insert);
			when(insert.execute()).thenReturn(body);
			
			//Method Call
			MockGoogleDrive.addFile(localFile);		
			
			//Verify
			Mockito.verify(MockDriveService, atLeast(0)).files();
		    Mockito.verify(files, atLeast(0)).insert(isA(File.class));
		    Mockito.verify(insert, atLeast(0)).execute();		
		}

		@Test
		public void testUpdateFile() throws IOException{
			FileList f = list.execute();
			String value = new String();
			File file = new File();
			java.util.List<File> fileList = mock(java.util.List.class);
			
			//Stubbing
			when(MockDriveService.files()).thenReturn(files);
			when(files.list()).thenReturn(list);
			when(files.update(isA(String.class),isA(File.class), isA(AbstractInputStreamContent.class))).thenReturn(update);
			when(update.execute()).thenReturn(isA(File.class));
			when(f.getItems()).thenReturn(fileList);
			when(file.getTitle().equals(isA(String.class))).thenReturn(isA(Boolean.class));
			
			//Method Call
			MockGoogleDrive.updateFile(localFile);		
			
			//Verify
			Mockito.verify(MockDriveService, atLeast(0)).files();
		    Mockito.verify(files, atLeast(0)).update(isA(String.class),isA(File.class));
		    Mockito.verify(update, atLeast(0)).execute();
			
		}

		@Test
		public void testDeleteFile() throws IOException {
			Delete delete = mock(Delete.class);
			String fileId = MockGoogleDrive.getFileId(localFile.getName());
			when(files.delete(isA(String.class))).thenReturn(delete);
		}

	

}
