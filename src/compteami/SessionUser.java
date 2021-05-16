package compteami;

public class SessionUser {
    private boolean statutSession; //Utilisateur connecte ou deconnecte
    private Utilisateur user;

    public SessionUser(Utilisateur current_user) {
    	if (user == null) {
    		this.statutSession = Config.USER_NOT_CONNECTED;
    	} else {
    		this.statutSession = Config.USER_CONNECTED;
    		this.user = current_user;
    	}
    }

	public boolean isStatutSession() {
		return statutSession;
	}

	public void setStatutSession(boolean statutSession) {
		this.statutSession = statutSession;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	
    
}
