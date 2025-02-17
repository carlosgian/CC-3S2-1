package ejemplos;


public class UserGreeting {
    private final UserProfiles profiles;
    public UserGreeting(UserProfiles profiles) {
        this.profiles = profiles;
    }

    /**
     *
     * @param id
     * @return
     */
    public String formatGreeting(UserId id) {
        return String.format("Hola y bienvenido, %s", profiles.fetchNicknameFor(id));
    }
}