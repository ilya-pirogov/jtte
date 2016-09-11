package info.devels.csmdashboard.commands

import info.devels.jtte.curse.CurseMap
import net.minecraft.command.ICommand
import net.minecraft.command.ICommandSender
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.server.MinecraftServer
import net.minecraft.util.ChatComponentText

import java.util.ArrayList
import java.util.Arrays


class JtteCommand : ICommand {
    override fun compareTo(other: Any?): Int {
        return 0
    }

    private val aliases: ArrayList<String>

    init {
        aliases = ArrayList<String>()
        aliases.add("jtte")
    }

    override fun getCommandName(): String {
        return "jtte"
    }

    override fun getCommandUsage(sender: ICommandSender): String {
        return "/jtte <process>"
    }

    override fun getCommandAliases(): List<*> {
        return aliases
    }

    override fun processCommand(sender: ICommandSender, args: Array<String>) {
        if (args.size == 0) {
            sender.addChatMessage(ChatComponentText("Invalid argument. Usage: " + getCommandUsage(sender)))
            return
        }

        val command = args[0]
        if (command == "process") {
            CurseMap.handleCursedBlocks(sender.entityWorld) { dx, dz -> sender.addChatMessage(ChatComponentText("Changed block: %d %d".format(dx, dz) )) }
        }
    }

    override fun canCommandSenderUseCommand(sender: ICommandSender): Boolean {
        return sender is EntityPlayerMP && MinecraftServer.getServer().configurationManager.func_152596_g(sender.gameProfile)
    }

    override fun addTabCompletionOptions(sender: ICommandSender, args: Array<String>): List<*> {
        return Arrays.asList("process")
    }

    override fun isUsernameIndex(p_82358_1_: Array<String>, p_82358_2_: Int): Boolean {
        return false
    }
}