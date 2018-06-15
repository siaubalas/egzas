package model;

public class Task {
    int id;
    String user;
    String title;
    String description;
    String status;
    
	public Task(int id, String user, String title, String description, String status) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.description = description;
		this.status = status;
	}
	
	public Task(String user, String title, String description, String status) {
		super();
		this.user = user;
		this.title = title;
		this.description = description;
		this.status = status;
	}

	public Task(int id, String status) {
		this.id = id;
		this.status = status;
	}

	public Task() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
