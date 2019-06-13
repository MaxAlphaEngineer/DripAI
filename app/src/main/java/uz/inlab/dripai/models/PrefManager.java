package uz.inlab.dripai.models;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Max based on  Lincoln.
 */
public class PrefManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;

    // shared pref mode
    private int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "UserInfo";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String USER_NAME = "Username";
    private static final String USER_BIO = "Userbio";
    private static final String USER_EARNING = "earning";
    private static final String USER_WASTED = "wasted";
    private static final String ANSWERED = "isQuestionAnswered";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }


    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void updateUsername(String Username) {
        editor.putString(USER_NAME, Username);
        editor.commit();
    }

    public String getUsername() {
        return pref.getString(USER_NAME, "");
    }

    public void updateBio(String Bio) {
        editor.putString(USER_BIO, Bio);
        editor.commit();
    }

    public String getUserBio() {
        return pref.getString(USER_BIO, "I am math geek!");
    }

    public void updateEarning(int Earning) {
        int oldEarning = pref.getInt(USER_EARNING, 0);
        editor.putInt(USER_EARNING, oldEarning + Earning);
        editor.commit();
    }

    public int getEarning() {
        return pref.getInt(USER_EARNING, 0);
    }


    public void updateWasted(int Wasted) {
        int oldWasted = pref.getInt(USER_WASTED, 0);
        editor.putInt(USER_WASTED, oldWasted + Wasted);
        editor.commit();
    }

    public int getWasted() {
        return pref.getInt(USER_WASTED, 0);
    }


    public void addUuid(String uuid) {
        editor.putBoolean(uuid, true);
        editor.commit();

    }

    public boolean checkUuid(String uuid)
    {
        return pref.getBoolean(uuid, false);
    }




}
