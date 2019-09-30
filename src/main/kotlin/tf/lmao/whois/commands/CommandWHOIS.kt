package tf.lmao.whois.commands

import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.StringArgumentType.getString
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText
import tf.lmao.whois.utils.WHOIS


class CommandWHOIS: Command<ServerCommandSource> {
    override fun run(context: CommandContext<ServerCommandSource>):Int {
        context.source.sendFeedback(LiteralText(WHOIS.query(getString(context, "domain"))), false)
        return 1
    }
}


