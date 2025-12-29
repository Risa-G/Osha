package osha;

import com.gtnewhorizon.gtnhlib.config.Config;

@Config(modid = "osha", filename = "osha")
public class ConfigureOsha {

    @Config.Name("Enable tongs")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean enableTongs;
}
