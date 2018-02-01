package com.test.adan.adan.MicrosoftGraph;

public class ODFile extends ODItem{
	
	String mimeType;
	
	public ODFile(String creatorName, String creatorId, String id, String createdDate, String lastModifiedDate,
			String lastModifiedByName, String lastModifiedById, String name, String parentDriveId,
			String parentFolderId, String parentFolderName, String parentPaht, int size, String webUrl, String type,
			String mimeType) {
		super(creatorName, creatorId, id, createdDate, lastModifiedDate, lastModifiedByName, lastModifiedById, name,
				parentDriveId, parentFolderId, parentFolderName, parentPaht, size, webUrl, type);
		this.mimeType = mimeType;
	}
	
	public ODFile(){
		super();
	}

	public String getMimeType() {
		return mimeType;
	}

	private void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}
