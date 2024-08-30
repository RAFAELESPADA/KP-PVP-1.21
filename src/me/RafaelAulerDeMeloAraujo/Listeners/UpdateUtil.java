package me.RafaelAulerDeMeloAraujo.Listeners;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UpdateUtil {

    public enum UpdateState { DISABLED, OUT_OF_DATE, UP_TO_DATE }

    private JavaPlugin plugin;
    private boolean enabled;
    private double newVersion = 0;

    public UpdateUtil(JavaPlugin plugin, boolean enabled) {
        this.plugin = plugin;
        this.enabled = enabled;
    }

    public UpdateState check() {

        if (!this.enabled) {
            return UpdateState.DISABLED;
        }
        char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        final String apiURL = "https://api.spigotmc.org/legacy/update.php?resource=50969/";
        try {
            URL api = new URL(apiURL);
            URLConnection yc = api.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc
                    .getInputStream()));
            String inputLine = in.readLine();
            this.newVersion = Double.parseDouble(inputLine.replace("BUILD-", "").replace(alphabet.toString(), ""));
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String descriptionVersion = this.plugin.getDescription().getVersion();
        double version = Double.parseDouble(descriptionVersion.replace("BUILD-", "").replace(alphabet.toString(), ""));
        if (version < this.newVersion) return UpdateState.OUT_OF_DATE;
        return UpdateState.UP_TO_DATE;
    }

    public double getNewVersion() {
        return this.newVersion;
    }
}
