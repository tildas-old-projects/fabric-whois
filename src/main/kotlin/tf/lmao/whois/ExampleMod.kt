package tf.lmao.whois

import com.mojang.brigadier.arguments.StringArgumentType.string
import net.fabricmc.fabric.api.registry.CommandRegistry
import net.minecraft.server.command.CommandManager
import tf.lmao.whois.commands.CommandWHOIS

@Suppress("unused")
fun init() {
    // This code runs as soon as Minecraft is in a mod-load-ready state.
    // However, some things (like resources) may still be uninitialized.
    // Proceed with mild caution.
    CommandRegistry.INSTANCE.register(false) { dispatcher ->
        val whoisNode = CommandManager.literal("whois")
            .then(CommandManager.argument("domain", string()).executes(CommandWHOIS()))
            .build()
        dispatcher.root.addChild(whoisNode)
    }
    println("- WHOIS mod by imtilda -")
}

