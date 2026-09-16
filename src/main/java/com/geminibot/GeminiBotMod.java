package com.geminibot;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import static net.minecraft.server.command.CommandManager.literal;

public class GeminiBotMod implements ModInitializer {
    private static boolean maceAutoSmash = true;

    @Override
    public void onInitialize() {
        // تسجيل أمر التحكم في المود /geminibot
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("geminibot")
                .then(literal("toggle")
                    .executes(context -> {
                        maceAutoSmash = !maceAutoSmash;
                        context.getSource().sendFeedback(() -> 
                            Text.literal("§b[GeminiBot] §fMace Auto-Smash: " + (maceAutoSmash ? "§aENABLED" : "§cDISABLED")), false);
                        return 1;
                    }))
                .then(literal("status")
                    .executes(context -> {
                        context.getSource().sendFeedback(() -> 
                            Text.literal("§b[GeminiBot] §fBot Status: Active | Mace Logic: " + (maceAutoSmash ? "§aON" : "§cOFF")), false);
                        return 1;
                    }))
            );
        });

        // خوارزمية القتال بالميس (Mace Logic)
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!world.isClient() && maceAutoSmash) {
                // التحقق مما إذا كان اللاعب يمسك الميس ويسقط من ارتفاع (Fall Distance > 1.5)
                if (player.getStackInHand(hand).isOf(Items.MACE) && player.fallDistance > 1.5F) {
                    player.sendMessage(Text.literal("§c⚡ [GeminiBot] CRITICAL MACE SMASH! ⚡"), true);
                }
            }
            return ActionResult.PASS;
        });
    }
}        });
    }
}
