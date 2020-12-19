package frogger.constant.settings;

/**
 * This enum class contains the game data for current game. Currently
 * only username is stored.
 */
public enum GameData {
    /** singleton instance of this class */
    INSTANCE;

    /** current player's name */
    private static String username;

    /**
     * This method sets the player username
     * @param str  player username
     */
    public void setUsername(String str){
        username=str;
    }

    /**
     * This method gets the player username
     * @return  {@code String} username
     */
    public String getUsername(){
        return username;
    }


}
