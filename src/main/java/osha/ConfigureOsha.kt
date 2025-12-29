package osha

import com.gtnewhorizon.gtnhlib.config.Config

@Config(modid = "osha", filename = "osha")
object ConfigureOsha {
    @Config.Name("Tongs enabling")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    var enableTongs: Boolean = false
}
