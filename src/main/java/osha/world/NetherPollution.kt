package osha.world

import com.gtnewhorizon.gtnhlib.eventbus.EventBusSubscriber
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import gregtech.common.pollution.Pollution
import net.minecraftforge.event.terraingen.PopulateChunkEvent

@EventBusSubscriber
object NetherPollution {
    @JvmStatic
    @SubscribeEvent
    fun polluteNetherChunk(event: PopulateChunkEvent.Populate) {
        val isHell = event.world.provider.isHellWorld

        if (isHell && !event.world.isRemote) {
            Pollution.addPollution(event.world, event.chunkX, event.chunkZ, Int.MAX_VALUE)
        }
    }
}
