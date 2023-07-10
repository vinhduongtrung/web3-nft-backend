package vinh.entity;

public enum Permission {
	
	USER_READ("user:read"),
	
	ARTIST_READ("artist:read");
	
	private final String permission;

	private Permission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
}
