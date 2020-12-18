package frogger.constant.settings;

public enum GameData {
    INSTANCE;
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
