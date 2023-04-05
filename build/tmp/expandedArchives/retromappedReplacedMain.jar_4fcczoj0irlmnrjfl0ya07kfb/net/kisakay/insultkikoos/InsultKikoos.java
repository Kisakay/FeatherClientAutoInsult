package net.kisakay.insultkikoos;

import net.kisakay.insultkikoos.test;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraft.network.play.client.C01PacketChatMessage;
import org.lwjgl.Sys;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.CombatTracker;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@SideOnly(Side.CLIENT)
@Mod(modid = InsultKikoos.MODID, version = InsultKikoos.VERSION, clientSideOnly = true)
public class InsultKikoos {
    private boolean deathMessageSent = false;

    public static final String MODID = "insultkikoos";
    public static final String VERSION = "1.4";
    private static final Minecraft mc = Minecraft.func_71410_x();

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }


    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {

        Minecraft minecraft = Minecraft.func_71410_x();
        ArrayList<String> playersOnline = new ArrayList<String>();
        EntityPlayerSP player = Minecraft.func_71410_x().field_71439_g;

        if (minecraft.field_71441_e == null) {
            return;
        }

        if (player == null) {
            return;
        }

        if (player.func_70089_S()) {
            deathMessageSent = false;
            return;
        }

        if (!deathMessageSent) {
            String[] messages = {
                    "FDPClient LOL TOO OP",
                    "%name% get it at fdpinfo.github.io",
                    "%name% has a trash client",
                    "%name% noob go get FDPClient",
                    "%name% NM$L for being legit",
                    "%name% was massacred by FDPClient" };

            Random random = new Random();

            Collection<NetworkPlayerInfo> playerList = mc.func_147114_u().func_175106_d();
            for (NetworkPlayerInfo playerInfo : playerList) {
                String playerName = playerInfo.func_178845_a().getName();

                if (!playerName.equals(Minecraft.func_71410_x().field_71439_g.func_70005_c_())) {
                    playersOnline.add(playerName);
                }
            }

            int index = random.nextInt(playersOnline.size());
            String element = playersOnline.get(index);

            String selectedMessage = messages[random.nextInt(messages.length)];
            String messageWithPlayerName = selectedMessage.replace("%name%", element);

            minecraft.field_71439_g.field_71174_a
                    .func_147297_a(new C01PacketChatMessage("[FDPCLIENT] " + messageWithPlayerName));
            deathMessageSent = true;
        }
    }


/* 
  @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) throws InterruptedException {
        if (mc.theWorld == null) {
            return;
        }
        test.Aim();
    }
*/
  

}
