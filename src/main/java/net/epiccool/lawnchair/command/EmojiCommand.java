package net.epiccool.lawnchair.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class EmojiCommand {
    public static final Map<String, String> EMOJIS_HAPPY = Map.<String, String>ofEntries(
            Map.entry("pointRight", "（☞ﾟヮﾟ）☞"),
            Map.entry("pointLeft", "☜（ﾟヮﾟ☜）"),
            Map.entry("amazed", "（ ﾟдﾟ）"),
            Map.entry("angel", "ʚ（^▽^）ɞ"),
            Map.entry("blush", "（,,> ᴗ < ,,）"),
            Map.entry("carryMoney", "（･∀･）つ⑩"),
            Map.entry("celebrate", "⊂二（＾ω＾）二⊃"),
            Map.entry("cheer", "\\（^o^）/"),
            Map.entry("content", "（・∀・）"),
            Map.entry("dance", "♪┏（・o･）┛♪"),
            Map.entry("dance1", "♪┗ （･o･） ┓♪"),
            Map.entry("danceCombo", "♪┏（・o･）┛♪┗ （ ･o･） ┓"),
            Map.entry("flowerGirl", "（◕‿◕✿）"),
            Map.entry("flowerGirlShy", "（* u ˬu）❀"),
            Map.entry("happy", "（ ﾟヮﾟ）"),
            Map.entry("joyful", "ヽ（´▽`）/"),
            Map.entry("cheery", "^ㅂ^"),
            Map.entry("smile", "> ᴗ <"),
            Map.entry("highFive", "（ ´･ω･）人（・ω・｀）"),
            Map.entry("headPat", "（ ´ ᵕ）ﾉ（´ ᵕ `,,）"),
            Map.entry("hug", "༼つ ◕_◕༽つ"),
            Map.entry("peaceful", "ヽ（´ー｀）ﾉ"),
            Map.entry("perky", "（`･ω･´）"),
            Map.entry("point", "m9（・∀・）"),
            Map.entry("poke", "（ ´∀｀）σ）∀`）"),
            Map.entry("pray", "（ㅅ´ ˘ `）"),
            Map.entry("relieved", "（´∀｀）"),
            Map.entry("salute", "（｀ᴗ´）>"),
            Map.entry("snubbed", "（´･ω･`）"),
            Map.entry("teasing", "（ ¬ˬ¬）"),
            Map.entry("thumbsUp", "d（^▽^）b"),
            Map.entry("toast", "（ ^_^）o自自o（^_^ ）"),
            Map.entry("winkLeft", "（^ ω <）"),
            Map.entry("winkRight", "（> ω ^）")
    );

    public static final Map<String, String> EMOJIS_SAD = Map.<String, String>ofEntries(
            Map.entry("lonely", "（'A`）"),
            Map.entry("cry", "（´；ω；`）"),
//            Map.entry("sad1", "（ ´,_ゝ`）"), stupid fucking minecraft
            Map.entry("upset", "（つ Д ｀）"),
            Map.entry("distressed", "（ ;´ - `;）"),
            Map.entry("sob", "（ ╥﹏╥ ）"),
            Map.entry("sobWhy", "（つ╥﹏╥）つ"),
            Map.entry("downcast", "（◞‸◟）"),
            Map.entry("uhOh", "（・Ａ・）")
    );

    public static final Map<String, String> EMOJIS_SHOCKED = Map.<String, String>ofEntries(
            Map.entry("alarmed", "（°ㅁ°）!!"),
            Map.entry("run", "ε=ε=ε=┌（;*´Д`）ﾉ"),
            Map.entry("scared", "（ó﹏ò｡）"),
            Map.entry("panic", "（°Д °;）"),
            Map.entry("scream", "（|'o'|）"),
            Map.entry("shock", "Σ（゜д゜）"),
            Map.entry("spook", "（（（ ；ﾟДﾟ）））"),
            Map.entry("stare", "（ Θ _Θ ）"),
            Map.entry("surprise", "Σ（ﾟДﾟ）"),
            Map.entry("astonished", "（　ﾟДﾟ）"),
            Map.entry("wideEyed", "（O_O）")
    );

    public static final Map<String, String> EMOJIS_ANGRY = Map.<String, String>ofEntries(
            Map.entry("angry", "（＃ﾟДﾟ）"),
            Map.entry("tantrum", "ヽ（o`皿′o）ﾉ"),
            Map.entry("determined", "（` ^ ´）"),
            Map.entry("comeOnLeft", "щ（ﾟДﾟщ）"),
            Map.entry("comeOnRight", "（屮ﾟДﾟ）屮"),
            Map.entry("disapprove", "ಠ_ಠ"),
            Map.entry("disapproveAngry", "ಠ益ಠ"),
            Map.entry("disgust", "（ಠ д ಠ）"),
            Map.entry("disgust1", "（ㆆ𒅒ㆆ\"）"),
            Map.entry("frustrated", "（╬ ಠ益ಠ）"),
            Map.entry("irritable", "ヽ（`Д´）ﾉ"),
            Map.entry("kick", "＼ ￣ヘ￣"),
            Map.entry("scream", "ヽ（°〇°）ﾉ"),
            Map.entry("shout", "（≧ロ≦）"),
            Map.entry("sigh", "（¬_¬ ）"),
            Map.entry("stern", "（ㆆ_ㆆ）"),
            Map.entry("tableFlip", "（╯°□°）╯（ ┻━┻"),
            Map.entry("tableFlip1", "┻━┻ （ ヽ（°□°ヽ）"),
            Map.entry("tableFlip2", "┻━┻ （ ＼（ °□° ）／ （ ┻━┻"),
            Map.entry("tableFlip4", "（ﾉಥ益ಥ）ﾉ ┻━┻"),
            Map.entry("tableFlip5", "┻━┻ （ヽ（`Д´）ﾉ（ ┻━┻"),
            Map.entry("tableFlip6", "（╯°Д°）╯（ /（.□ . \\）"),
            Map.entry("tsundere", "（¬`‸´¬）"),
            Map.entry("yell", "\"（ ´Д｀）\"）")
    );

    public static final Map<String, String> EMOJIS_MISC = Map.<String, String>ofEntries(
            Map.entry("bear", "ʕ •ᴥ• ʔ"),
            Map.entry("bellySlide", "⊂（ﾟДﾟ⊂⌒｀つ≡≡≡≡≡≡"),
            Map.entry("bored", "（-ε-;）"),
            Map.entry("blush", "（,,>﹏<,,）"),
            Map.entry("cat", "=＾● ⋏ ●＾="),
            Map.entry("catClassic", "（=^ェ^=）"),
            Map.entry("confused", "∘ ∘ ∘ （ °ヮ° ） ?"),
            Map.entry("dog", "（ᵔᴥᵔ）"),
            Map.entry("embarrassed", "（´∇｀''）"),
            Map.entry("indifferent", "（　´_ゝ`）"),
            Map.entry("kowtow", "m（_ _）m"),
            Map.entry("peek", "┬┴┤（･_├┬┴"),
            Map.entry("shrug", "¯\\_（ツ）_/¯"),
            Map.entry("shrugContent", "¯\\_（ᵕ—ᴗ—）_/¯"),
            Map.entry("snorlax", "（￣ー￣）"),
            Map.entry("smoking", "（´ー`）y-~~"),
            Map.entry("tableFlip", "┬──┬ ¯\\_（ツ）"),
            Map.entry("tableFlip1", "┻━┻ （ ¯\\（ツ）/¯ （ ┻━┻"),
            Map.entry("tableFlipBear", "ʕノ•ᴥ•ʔノ （ ┻━┻"),
            Map.entry("tableUnflip", "┬─┬ノ（ º _ ºノ）"),
            Map.entry("thinking", "(ಠ_ಠ)つ"),
            Map.entry("unconvinced", "エェェ（´д｀）ェェエ"),
            Map.entry("unsure", "┐（'～`；）┌"),
            Map.entry("whisper", "（ ´д）ﾋｿ（´Д｀）")
    );

    public static final Set<Text> EMOJI_MESSAGES = new HashSet<>();

    public static final Map<String, Map<String, String>> EMOJI_CATEGORIES = Map.of(
            "happy", EMOJIS_HAPPY,
            "sad", EMOJIS_SAD,
            "shocked", EMOJIS_SHOCKED,
            "angry", EMOJIS_ANGRY,
            "misc", EMOJIS_MISC
    );

    public static int sendEmoji(CommandContext<ServerCommandSource> ctx, String category, String name, String message) {
        ServerPlayerEntity player = ctx.getSource().getPlayer();
        if (player == null) return 0;

        Random random = new Random();

        String categoryLower = category.toLowerCase();
        String category1;

        if (categoryLower.equals("random")) {
            Object[] categories = EMOJI_CATEGORIES.keySet().toArray();
            category1 = (String) categories[random.nextInt(categories.length)];
        } else {
            category1 = EMOJI_CATEGORIES.keySet().stream()
                    .filter(k -> k.equalsIgnoreCase(category))
                    .findFirst()
                    .orElse(null);

            if (category1 == null) {
                player.sendMessage(Text.translatable(
                        "commands.lawnchair.emoji.invalid.category",
                        categoryLower
                ).formatted(Formatting.RED));
                return 0;
            }
        }

        Map<String, String> emojiMap = EMOJI_CATEGORIES.get(category1);
        String name1;

        if (name.equalsIgnoreCase("random")) {
            Object[] emojiKeys = emojiMap.keySet().toArray();
            name1 = (String) emojiKeys[random.nextInt(emojiKeys.length)];
        } else {
            String nameLower = name.toLowerCase();

            name1 = emojiMap.keySet().stream()
                    .filter(k -> k.toLowerCase().equals(nameLower))
                    .findFirst()
                    .orElse(null);

            if (name1 == null) {
                player.sendMessage(Text.translatable(
                        "commands.lawnchair.emoji.invalid.name",
                        nameLower,
                        category1
                ).formatted(Formatting.RED));
                return 0;
            }
        }

        String emoji = emojiMap.get(name1);
        Text output = Text.literal("<" + player.getName().getString() + "> "
                + (message.isEmpty() ? "" : message + " ")
                + emoji);

        EMOJI_MESSAGES.add(Text.literal(emoji));
        player.getEntityWorld().getServer().getPlayerManager().broadcast(output, false);

        return Command.SINGLE_SUCCESS;
    }
}
