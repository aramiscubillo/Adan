package com.test.adan.adan.MicrosoftGraph;

import java.util.List;

public class ODFolder extends ODItem{
	
	int childCount;
	List<ODFile> files;
	List<ODFolder> folders;
	
	public ODFolder(String creatorName, String creatorId, String id, String createdDate, String lastModifiedDate,
			String lastModifiedByName, String lastModifiedById, String name, String parentDriveId,
			String parentFolderId, String parentFolderName, String parentPaht, int size, String webUrl, String type, int childCount,
			List<ODFile> files, List<ODFolder> folders) {
		super(creatorName, creatorId, id, createdDate, lastModifiedDate, lastModifiedByName, lastModifiedById, name,
				parentDriveId, parentFolderId, parentFolderName, parentPaht, size, webUrl, type);
		this.childCount = childCount;
		this.files = files;
		this.folders = folders;
	}

	public ODFolder(){
		super();
	}

	public int getChildCount() {
		return childCount;
	}


	private void setChildCount(int childCount) {
		this.childCount = childCount;
	}


	public List<ODFile> getFiles() {
		return files;
	}


	private void setFiles(List<ODFile> files) {
		this.files = files;
	}


	public List<ODFolder> getFolders() {
		return folders;
	}


	private void setFolders(List<ODFolder> folders) {
		this.folders = folders;
	}

}
