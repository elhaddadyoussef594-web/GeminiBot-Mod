package com.geminibot.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class GeminiBotClient implements ClientModInitializer {
    private static boolean isListening = false;

    @Override
    public void onInitializeClient() {
        // رصد الضغط على زر CTRL
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                long window = client.getWindow().getHandle();
                isListening = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS;
            }
        });

        // رسم أيقونة المايك أسفل اليسار أثناء الضغط
        HudRenderCallback.EVENT.register((drawContext, renderTickCounter) -> {
            if (isListening) {
                // طباعة مؤشر صوتي آلي أسفل الشاشة
                drawContext.drawText(
                    MinecraftClient.getInstance().textRenderer, 
                    Text.literal("§a[🎙️ GeminiBot OS: Listening...]"), 
                    10, 
                    drawContext.getScaledWindowHeight() - 20, 
                    0xFFFFFF, 
                    true
                );
            }
        });
    }
}
