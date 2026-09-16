package com.geminibot;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.sound.SoundEvents;
import net.minecraft.sound.SoundCategory;

public class GeminiBotMod implements ModInitializer {
    @Override
    public void onInitialize() {
        System.out.println("GeminiBot Mod Loaded Successfully!");

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("GeminiBot")
                .then(CommandManager.literal("spawn")
                    .executes(context -> {
                        var source = context.getSource();
                        var player = source.getPlayer();
                        
                        if (player != null) {
                            source.sendFeedback(() -> Text.literal("مرحباً بك مع GeminiBot! البوت المخصص لقتال الميس."), false);
                            player.getWorld().playSound(
                                null, 
                                player.getBlockPos(), 
                                SoundEvents.BLOCK_CONDUIT_ACTIVATE, 
                                SoundCategory.MASTER, 
                                1.0f, 
                                1.5f
                            );
                        }
                        return 1;
                    })
                )
            );
        });
    }
}
