package osha

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger


@Mod(
    modid = Osha.modid,
    name = Osha.name,
    version = Osha.version,
)
class Osha {
    companion object {
        const val version = "0.1.0"
        const val modid = "osha"
        const val name = "Osha"
        private val logger = LogManager.getLogger(modid)

        fun log(): Logger = logger
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        log().info("Osha pre initializing")
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        log().info("Osha initializing")
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        log().info("Osha post initializing")
    }
}
