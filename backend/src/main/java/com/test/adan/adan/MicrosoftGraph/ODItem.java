package com.test.adan.adan.MicrosoftGraph;

public class ODItem {


	String creatorName;
	String creatorId;
	String id;
	String createdDate;
	String lastModifiedDate;
	String lastModifiedByName;
	String lastModifiedById;
	String name;
	String parentDriveId;
	String parentFolderId;
	String parentFolderName;
	String parentPaht;
	int size;
	String webUrl;
	String type;
	

	public ODItem(String creatorName, String creatorId, String id, String createdDate, String lastModifiedDate,
			String lastModifiedByName, String lastModifiedById, String name, String parentDriveId,
			String parentFolderId, String parentFolderName, String parentPaht, int size, String webUrl, String type) {
		super();
		this.creatorName = creatorName;
		this.creatorId = creatorId;
		this.id = id;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedByName = lastModifiedByName;
		this.lastModifiedById = lastModifiedById;
		this.name = name;
		this.parentDriveId = parentDriveId;
		this.parentFolderId = parentFolderId;
		this.parentFolderName = parentFolderName;
		this.parentPaht = parentPaht;
		this.size = size;
		this.webUrl = webUrl;
		this.type = type;
	}
	
	public ODItem(){
		super();
	}
	
	public String getType() {
		return type;
	}

	private void setType(String type) {
		this.type = type;
	}

	public String getCreatorName() {
		return creatorName;
	}


	private void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}


	public String getCreatorId() {
		return creatorId;
	}


	private void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}


	public String getId() {
		return id;
	}


	private void setId(String id) {
		this.id = id;
	}


	public String getCreatedDate() {
		return createdDate;
	}


	private void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


	public String getLastModifiedDate() {
		return lastModifiedDate;
	}


	private void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	public String getLastModifiedByName() {
		return lastModifiedByName;
	}


	private void setLastModifiedByName(String lastModifiedByName) {
		this.lastModifiedByName = lastModifiedByName;
	}


	public String getLastModifiedById() {
		return lastModifiedById;
	}


	private void setLastModifiedById(String lastModifiedById) {
		this.lastModifiedById = lastModifiedById;
	}


	public String getName() {
		return name;
	}


	private void setName(String name) {
		this.name = name;
	}


	public String getParentDriveId() {
		return parentDriveId;
	}


	private void setParentDriveId(String parentDriveId) {
		this.parentDriveId = parentDriveId;
	}


	public String getParentFolderId() {
		return parentFolderId;
	}


	private void setParentFolderId(String parentFolderId) {
		this.parentFolderId = parentFolderId;
	}


	public String getParentFolderName() {
		return parentFolderName;
	}


	private void setParentFolderName(String parentFolderName) {
		this.parentFolderName = parentFolderName;
	}


	public String getParentPaht() {
		return parentPaht;
	}


	private void setParentPaht(String parentPaht) {
		this.parentPaht = parentPaht;
	}


	public int getSize() {
		return size;
	}


	private void setSize(int size) {
		this.size = size;
	}


	public String getWebUrl() {
		return webUrl;
	}


	private void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

}
