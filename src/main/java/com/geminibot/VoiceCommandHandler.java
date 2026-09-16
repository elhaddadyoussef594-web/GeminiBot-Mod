package com.geminibot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class VoiceCommandHandler {

    public static void processVoiceCommand(PlayerEntity player, String recognizedText) {
        String input = recognizedText.toLowerCase();

        if (input.contains("mace") || input.contains("ميس") || input.contains("مطرقة")) {
            // تنفيذ تجهيز المعدات
            equipMaceKit(player);

            // الرد بلغة المستخدم
            if (input.matches(".*[a-zA-Z].*")) {
                sendRobotResponse(player, "Mace PvP Kit Equipped Successfully.");
            } else {
                sendRobotResponse(player, "تم تجهيز عتاد الميس المخصص للقتال بنجاح.");
            }
        }
    }

    private static void equipMaceKit(PlayerEntity player) {
        // منطق ترتيب السلاح في الـ Hotbar
    }

    private static void sendRobotResponse(PlayerEntity player, String response) {
        player.sendMessage(Text.literal("§8[§b🤖 GeminiBot-OS§8] §a> §f" + response), false);
    }
}
