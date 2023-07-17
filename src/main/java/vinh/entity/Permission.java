package vinh.entity;

public enum Permission {
	
	ARTIST_READ("artist:read"),
    ARTIST_UPDATE("artist:update"),
    ARTIST_CREATE("artist:create"),
    ARTIST_DELETE("artist:delete"),
    USER_READ("user:read");
	
	
	private final String permission;

	
	private Permission(String permission) {
		this.permission = permission;
	}



	public String getPermission() {
		return permission;
	}
	
	
}
