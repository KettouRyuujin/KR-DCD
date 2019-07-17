package dcdmod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.ReflectionHacks;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.OnCardUseSubscriber;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.StartGameSubscriber;
import basemod.patches.com.megacrit.cardcrawl.helpers.CardLibrary.BlueCardsPatch;
import basemod.patches.com.megacrit.cardcrawl.helpers.CardLibrary.GreenCardsPatch;
import basemod.patches.com.megacrit.cardcrawl.helpers.CardLibrary.RedCardsPatch;
import dcdmod.Actions.TurnTimer;
import dcdmod.Card.Basic.*;
import dcdmod.Card.Common.*;
import dcdmod.Card.Rare.*;
import dcdmod.Card.Special.*;
import dcdmod.Card.Uncommon.*;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.CharacterEnum;
import dcdmod.Power.AutoVajinPower;
import dcdmod.Relic.*;
import static basemod.DevConsole.logger;

@SpireInitializer
public class DCDmod implements PostInitializeSubscriber,EditCharactersSubscriber,EditCardsSubscriber,EditRelicsSubscriber,EditStringsSubscriber,OnCardUseSubscriber,EditKeywordsSubscriber, OnStartBattleSubscriber , StartGameSubscriber{

    private static final String FRUITY_MOD_ASSETS_FOLDER = "img";
    public static final Color DCD = CardHelper.getColor(208, 45, 150);

    //卡面能量样式
    public static final String[] ENERGY_ORB = {"img/512/orb.png" , "img/512/orb-ice.png" , "img/512/orb-fire.png" , "img/512/orb-dark.png" };
    public static final String[] ENERGY_ORB_P = {"img/1024/orb.png" , "img/1024/orb-ice.png" , "img/1024/orb-fire.png" , "img/1024/orb-dark.png" };

    //选人按钮
    private static final String DCD_BUTTON = "charSelect/DecadeButton.png";
    //选人背景
    private static final String DCD_PORTRAIT = "charSelect/2.png";
    
    //篝火背景
    public static final String MAGES_SHOULDER_1 = "char/DCD/shoulder.png";
    public static final String MAGES_SHOULDER_2 = "char/DCD/shoulder2.png";
    //倒地图片
    public static final String MAGES_CORPSE = "char/DCD/corpse.png";
    
    //卡面背景
    public static final String[] ATTACK_BG = { "img/512/attack_decade.png", "img/512/attack_kuuga.png", "img/512/attack_agito.png", "img/512/attack_ryuki.png", "img/512/attack_faiz.png", "img/512/attack_blade.png", "img/512/attack_hibiki.png", "img/512/attack_kabuto.png", "img/512/attack_deno.png", "img/512/attack_kiva.png"};
    public static final String[] SKILL_BG = { "img/512/skill_decade.png", "img/512/skill_kuuga.png", "img/512/skill_agito.png", "img/512/skill_ryuki.png", "img/512/skill_faiz.png", "img/512/skill_blade.png", "img/512/skill_hibiki.png", "img/512/skill_kabuto.png", "img/512/skill_deno.png", "img/512/skill_kiva.png"};
    public static final String[] POWER_BG = { "img/512/power_decade.png", "img/512/power_kuuga.png", "img/512/power_agito.png", "img/512/power_ryuki.png", "img/512/power_faiz.png", "img/512/power_blade.png", "img/512/power_hibiki.png", "img/512/power_kabuto.png", "img/512/power_deno.png", "img/512/power_kiva.png" };
    public static final String[] ATTACK_BG_P = { "img/1024/attack_decade.png", "img/1024/attack_kuuga.png", "img/1024/attack_agito.png", "img/1024/attack_ryuki.png", "img/1024/attack_faiz.png", "img/1024/attack_blade.png", "img/1024/attack_hibiki.png", "img/1024/attack_kabuto.png", "img/1024/attack_deno.png", "img/1024/attack_kiva.png"};
    public static final String[] SKILL_BG_P = { "img/1024/skill_decade.png", "img/1024/skill_kuuga.png", "img/1024/skill_agito.png", "img/1024/skill_ryuki.png", "img/1024/skill_faiz.png", "img/1024/skill_blade.png", "img/1024/skill_hibiki.png", "img/1024/skill_kabuto.png", "img/1024/skill_deno.png", "img/1024/skill_kiva.png"};
    public static final String[] POWER_BG_P = { "img/1024/power_decade.png", "img/1024/power_kuuga.png", "img/1024/power_agito.png", "img/1024/power_ryuki.png", "img/1024/power_faiz.png", "img/1024/power_blade.png", "img/1024/power_hibiki.png", "img/1024/power_kabuto.png", "img/1024/power_deno.png", "img/1024/power_kiva.png" };
    
    //卡牌费用
    public static final String[] BASIC = {"img/512/banner_decade.png","img/512/banner_decade1.png","img/512/banner_decade2.png","img/512/banner_decade3.png","img/512/banner_decade4.png","img/512/banner_decade5.png","img/512/banner_decadex.png"};
    public static final String[] COMMON = {"img/512/banner_decade_c.png","img/512/banner_decade_c1.png","img/512/banner_decade_c2.png","img/512/banner_decade_c3.png","img/512/banner_decade_c4.png","img/512/banner_decade_c5.png","img/512/banner_decade_cx.png"};
    public static final String[] UNCOMMON = {"img/512/banner_decade_u.png","img/512/banner_decade_u1.png","img/512/banner_decade_u2.png","img/512/banner_decade_u3.png","img/512/banner_decade_u4.png","img/512/banner_decade_u5.png","img/512/banner_decade_ux.png"};
    public static final String[] RARE = {"img/512/banner_decade_r.png","img/512/banner_decade_r1.png","img/512/banner_decade_r2.png","img/512/banner_decade_r3.png","img/512/banner_decade_r4.png","img/512/banner_decade_r5.png","img/512/banner_decade_rx.png"};
    public static final String[] SPECIAL = {"img/512/banner_decade_s.png","img/512/banner_decade_s1.png","img/512/banner_decade_s2.png","img/512/banner_decade_s3.png","img/512/banner_decade_s4.png","img/512/banner_decade_s5.png","img/512/banner_decade_sx.png"};
    public static final String[] BASIC_P = {"img/1024/banner_decade.png","img/1024/banner_decade1.png","img/1024/banner_decade2.png","img/1024/banner_decade3.png","img/1024/banner_decade4.png","img/1024/banner_decade5.png","img/1024/banner_decadex.png"};
    public static final String[] COMMON_P = {"img/1024/banner_decade_c.png","img/1024/banner_decade_c1.png","img/1024/banner_decade_c2.png","img/1024/banner_decade_c3.png","img/1024/banner_decade_c4.png","img/1024/banner_decade_c5.png","img/1024/banner_decade_cx.png"};
    public static final String[] UNCOMMON_P = {"img/1024/banner_decade_u.png","img/1024/banner_decade_u1.png","img/1024/banner_decade_u2.png","img/1024/banner_decade_u3.png","img/1024/banner_decade_u4.png","img/1024/banner_decade_u5.png","img/1024/banner_decade_ux.png"};
    public static final String[] RARE_P = {"img/1024/banner_decade_r.png","img/1024/banner_decade_r1.png","img/1024/banner_decade_r2.png","img/1024/banner_decade_r3.png","img/1024/banner_decade_r4.png","img/1024/banner_decade_r5.png","img/1024/banner_decade_rx.png"};
    public static final String[] SPECIAL_P = {"img/1024/banner_decade_s.png","img/1024/banner_decade_s1.png","img/1024/banner_decade_s2.png","img/1024/banner_decade_s3.png","img/1024/banner_decade_s4.png","img/1024/banner_decade_s5.png","img/1024/banner_decade_sx.png"};
    public static final String[] FAR =  {"img/512/banner_FAR.png","img/512/banner_FAR1.png","img/512/banner_FAR2.png","img/512/banner_FAR3.png","img/512/banner_FAR4.png","img/512/banner_FAR5.png","img/512/banner_FARx.png"};
    public static final String[] FAR_P =  {"img/1024/banner_FAR.png","img/1024/banner_FAR1.png","img/1024/banner_FAR2.png","img/1024/banner_FAR3.png","img/1024/banner_FAR4.png","img/1024/banner_FAR5.png","img/1024/banner_FARx.png"};
    
    //按键设置
    public static int[] TaikoKeys = new int[]{Input.Keys.U, Input.Keys.I, Input.Keys.O, Input.Keys.P, Input.Keys.SPACE};

    //动画开关
    public static boolean AnimationTrigger = false;
    
    //config存储
    public static Properties DCDDefaults = new Properties();
    
    //mod列表小图标
    public static final String DCD_BADGE = "img/powers/KamenRideDecadePower.png";
    
    
    @SpireEnum
    public static AbstractCard.CardTags RiderCard;
    @SpireEnum
    public static AbstractCard.CardTags KamenRide;
    @SpireEnum
    public static AbstractCard.CardTags FormRide;
    @SpireEnum
    public static AbstractCard.CardTags SelectCard;
    
    public DCDmod(){
        
    	logger.info("============================ 监听初始化事件 ============================");
        
        BaseMod.subscribe(this);
        
        logger.info("========================================================================");
    	    	
        logger.info("========================= 正在加载追加游戏内容 =========================");
        //BaseMod.subscribeToPostCreateStartingRelics(new CreatePlayerAddRelic());
        logger.info("==========================其实没有新的游戏内容=============================");

        logger.info("======================== 正在注入新卡片相关信息 ========================");
        BaseMod.addColor(AbstractCardEnum.DCD,
                DCD, DCD, DCD, DCD, DCD, DCD, DCD,
                ATTACK_BG[0], SKILL_BG[0], POWER_BG[0], ENERGY_ORB[0],
                ATTACK_BG_P[0], SKILL_BG_P[0], POWER_BG_P[0], ENERGY_ORB_P[0]);
        logger.info("===========================注入新卡片相关信息成功========================");
        loadConfig();
    }
    
	public static void loadConfig() {
		logger.info("==========================读取设置=============================");
		try {
			SpireConfig config = new SpireConfig("DCDmod", "DCDSaveData", DCDDefaults);
			config.load();
			AnimationTrigger = config.getBool("AnimationTrigger");
		} catch (Exception e) {
			e.printStackTrace();
			clearConfig();
		}
		logger.info("==========================设置读取完毕=============================");
	}
	
	public static void saveConfig() {
		 logger.info("==========================存档设置=============================");
		try {
			SpireConfig config = new SpireConfig("DCDmod", "DCDSaveData", DCDDefaults);
			config.setBool("AnimationTrigger", AnimationTrigger);
			config.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("==========================设置存档完毕=============================");
	}
	
	public static void clearConfig() {
		saveConfig();
	}

    public static void initialize() {
        logger.info("========================= 初始化DCDMod所有数据 =========================");
        new DCDmod();
        logger.info("=========================== 初始化DCDMod成功 ===========================");
    }

    public static final String makePath(String resource) {
        return FRUITY_MOD_ASSETS_FOLDER + "/" + resource;
    }



	
    private static String loadJson(final String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }

    @Override
    public void receiveEditKeywords() {
    	logger.info("========================== 正在注入新的关键字 ==========================");
        String keywordsPath = null;
        switch (Settings.language) {
            case ZHT:
            case ZHS: {
                keywordsPath = "localization/zhs/DCDKeywords.json";
                break;
            }
            default: {
                keywordsPath = "localization/eng/DCDKeywords.json";
                break;
            }
        }
        final Gson gson = new Gson();
        final Keywords keywords = (Keywords)gson.fromJson(loadJson(keywordsPath), Keywords.class);
        for (final Keyword key : keywords.keywords) {
            logger.info("读取关键字：" + key.NAMES[0]);
            BaseMod.addKeyword(key.NAMES, key.DESCRIPTION);
        }
        logger.info("===========================注入新的关键字成功=============================");
    }
    
    class Keywords
    {
        Keyword[] keywords;
    }
    
    @Override
    public void receiveEditCharacters() {
        logger.info("======================== 正在注入DCD的信息 ========================");
        logger.info("add " + CharacterEnum.Decade.toString());
        BaseMod.addCharacter(
        		(AbstractPlayer)new Decade("Decade"),
                makePath(DCD_BUTTON), makePath(DCD_PORTRAIT),
                CharacterEnum.Decade);

        logger.info("============================注入DCD成功================================");
    }

    @Override
    public void receivePostInitialize() {
    	Texture badgeTexture = new Texture(DCD_BADGE);
    	
		ModPanel settingsPanel = new ModPanel();

		ModLabeledToggleButton flipButton = new ModLabeledToggleButton("优化开关（开启后可去除所有不必要动画，极高提升流畅度，重启游戏以达最优效果）",
				400.0f, 720.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
				AnimationTrigger, settingsPanel, (label) -> {
		}, (button) -> {
			AnimationTrigger = button.enabled;
			saveConfig();
		});
    	
		settingsPanel.addUIElement(flipButton);
		
		BaseMod.registerModBadge(badgeTexture, "Decade", "树", "增加了以假面骑士Decade中的门矢士为原型的人物，测试版", settingsPanel);
		
    	final HashMap<String, Sfx> reflectedMap = this.getSoundsMap();
    	reflectedMap.put("decade_hensin", new Sfx("sounds/decade_hensin.ogg"));
    	reflectedMap.put("people_hensin", new Sfx("sounds/people_hensin.ogg"));
    	reflectedMap.put("victory_normal", new Sfx("sounds/victory_normal.ogg"));
    	reflectedMap.put("victory1", new Sfx("sounds/victory1.ogg"));
    	reflectedMap.put("victory2", new Sfx("sounds/victory2.ogg"));
    	reflectedMap.put("kamenride", new Sfx("sounds/kamenride.wav"));
    	reflectedMap.put("kuuga_hensin", new Sfx("sounds/kuuga_hensin.wav"));
    	reflectedMap.put("agito_hensin", new Sfx("sounds/agito_hensin.wav"));
    	reflectedMap.put("driversounds", new Sfx("sounds/driversounds.wav"));
    	reflectedMap.put("BGM", new Sfx("sounds/BGM.wav"));
    	reflectedMap.put("test1", new Sfx("sounds/test.ogg"));
    	reflectedMap.put("formride", new Sfx("sounds/form_ride.mp3"));
    	reflectedMap.put("pegasus", new Sfx("sounds/kuuga_pegasus.mp3"));
    	reflectedMap.put("titan", new Sfx("sounds/kuuga_titan.mp3"));
    	reflectedMap.put("dragon", new Sfx("sounds/kuuga_dragon.mp3"));
    	reflectedMap.put("flame", new Sfx("sounds/agito_flame.mp3"));
    	reflectedMap.put("storm", new Sfx("sounds/agito_storm.mp3"));
    	reflectedMap.put("FAR", new Sfx("sounds/final_attack_ride.mp3"));
    	reflectedMap.put("FAR_DCD", new Sfx("sounds/decade_final.mp3"));
    	reflectedMap.put("FAR_KUUGA", new Sfx("sounds/kuuga_final.mp3"));
    	reflectedMap.put("FAR_AGITO", new Sfx("sounds/agito_final.mp3"));
    	reflectedMap.put("FAR_RYUKI", new Sfx("sounds/ryuki_final.mp3"));
    	reflectedMap.put("FAR_FAIZ", new Sfx("sounds/faiz_final.mp3"));
    	reflectedMap.put("FAR_BLADE", new Sfx("sounds/blade_final.mp3"));
    	reflectedMap.put("FAR_HIBIKI", new Sfx("sounds/hibiki_final.mp3"));
    	reflectedMap.put("FAR_KABUTO", new Sfx("sounds/kabuto_final.mp3"));
    	reflectedMap.put("attackride", new Sfx("sounds/attack_ride.mp3"));
    	reflectedMap.put("autovajin", new Sfx("sounds/faiz_auto_vajin.mp3"));
    	reflectedMap.put("autovajinattack", new Sfx("sounds/autovajinattack.wav"));
    	reflectedMap.put("autovajindisappear", new Sfx("sounds/autovajindisappear.wav"));
    	reflectedMap.put("ryuki_hensin", new Sfx("sounds/ryuki_hensin.wav"));
    	reflectedMap.put("dragreder_advent", new Sfx("sounds/advent.wav"));
    	reflectedMap.put("dragreder_appear", new Sfx("sounds/dragreder_appear.wav"));
    	reflectedMap.put("dragreder_attack", new Sfx("sounds/dragreder_attack.ogg"));
    	reflectedMap.put("ryuki_guard", new Sfx("sounds/ryuki_guard.wav"));
    	reflectedMap.put("ryuki_strike", new Sfx("sounds/ryuki_strike_vein.mp3"));
    	reflectedMap.put("ryuki_BGM", new Sfx("sounds/ryuki_bgm.wav"));
    	reflectedMap.put("faiz_hensin", new Sfx("sounds/faiz_hensin.wav"));
    	reflectedMap.put("faiz_button", new Sfx("sounds/ExceedCharge.wav"));
    	reflectedMap.put("faiz_BGM", new Sfx("sounds/faiz_BGM.wav"));
    	reflectedMap.put("faiz_sword", new Sfx("sounds/faiz_sword.wav"));
    	reflectedMap.put("axel_1", new Sfx("sounds/faiz_Axel_1.wav"));
    	reflectedMap.put("axel_2", new Sfx("sounds/faiz_Axel_2.wav"));
    	reflectedMap.put("axel_3", new Sfx("sounds/faiz_Axel_3.wav"));
    	reflectedMap.put("axel_timer", new Sfx("sounds/faiz_Axel_timer.wav"));
    	reflectedMap.put("axel_timeout", new Sfx("sounds/faiz_Axel_timeout.wav"));
    	reflectedMap.put("axel_sound", new Sfx("sounds/faiz_axel.mp3"));
    	reflectedMap.put("blade_henshin", new Sfx("sounds/blade_henshin.wav"));
    	reflectedMap.put("blade_BGM1", new Sfx("sounds/Blade_OP1.mp3"));
    	reflectedMap.put("blade_BGM2", new Sfx("sounds/Blade_OP2.mp3"));
    	reflectedMap.put("blade_jack", new Sfx("sounds/blade_jack.mp3"));
    	reflectedMap.put("blade_beat", new Sfx("sounds/Blade_beat.ogg"));
    	reflectedMap.put("blade_kick", new Sfx("sounds/Blade_kick.ogg"));
    	reflectedMap.put("blade_mach", new Sfx("sounds/Blade_mach.ogg"));
    	reflectedMap.put("blade_metal", new Sfx("sounds/Blade_metal.ogg"));
    	reflectedMap.put("blade_slash", new Sfx("sounds/Blade_slash.ogg"));
    	reflectedMap.put("blade_thunder", new Sfx("sounds/Blade_thunder.ogg"));
    	reflectedMap.put("blade_LightningBlast", new Sfx("sounds/Blade_LightningBlast.ogg"));
    	reflectedMap.put("blade_LightningSlash", new Sfx("sounds/Blade_LightningSlash.ogg"));
    	reflectedMap.put("blade_LightningSonic", new Sfx("sounds/Blade_LightningSonic.ogg"));
    	reflectedMap.put("PON", new Sfx("sounds/PON.mp3"));
    	reflectedMap.put("PATA", new Sfx("sounds/PATA.mp3"));
    	reflectedMap.put("CHAKA", new Sfx("sounds/CHAKA.mp3"));
    	reflectedMap.put("DON", new Sfx("sounds/DON.mp3"));
    	reflectedMap.put("Forward", new Sfx("sounds/PATA-PATA-PATA-PON.mp3"));
    	reflectedMap.put("Back", new Sfx("sounds/PON-PATA-PON-PATA.mp3"));
    	reflectedMap.put("Attack", new Sfx("sounds/PON-PON-PATA-PON.mp3"));
    	reflectedMap.put("Defend", new Sfx("sounds/CHAKA-CHAKA-PATA-PON.mp3"));
    	reflectedMap.put("Accumulate", new Sfx("sounds/PON-PON-CHAKA-CHAKA.mp3"));
    	reflectedMap.put("Jump", new Sfx("sounds/DON-DON-CHAKA-CHAKA.mp3"));
    	reflectedMap.put("Relieve", new Sfx("sounds/PATA-PON-DON-CHAKA.mp3"));
    	reflectedMap.put("Buff", new Sfx("sounds/DON-DONDON-DONDON.mp3"));
    	reflectedMap.put("Fever", new Sfx("sounds/FEVER.mp3"));
    	reflectedMap.put("hibiki_henshin", new Sfx("sounds/hibiki_henshin.wav"));
    	reflectedMap.put("hibiki_BGM", new Sfx("sounds/hibiki_BGM.mp3"));
    	reflectedMap.put("hibiki_kurenai_sound", new Sfx("sounds/hibiki_kurenai_sound.ogg"));
    	reflectedMap.put("hibiki_kurenai", new Sfx("sounds/hibiki_kurenai.ogg"));
    	reflectedMap.put("hibiki_attack1", new Sfx("sounds/hibiki_onibi.mp3"));
    	reflectedMap.put("hibiki_attack2", new Sfx("sounds/hibiki_ongekibou_rekka.mp3"));
    	reflectedMap.put("kabuto_henshin", new Sfx("sounds/kabuto_henshin.wav"));
    	reflectedMap.put("kabuto_clockup", new Sfx("sounds/kabuto_clock_up.mp3"));
    	reflectedMap.put("kabuto_BGM", new Sfx("sounds/kabuto_BGM.mp3"));
    	reflectedMap.put("agito_BGM1", new Sfx("sounds/agito_BGM1.mp3"));
    	reflectedMap.put("agito_BGM2", new Sfx("sounds/agito_BGM2.mp3"));
    	reflectedMap.put("faiz_BGM2", new Sfx("sounds/agito_BGM2.mp3"));
        reflectedMap.put("deno_henshin", new Sfx("sounds/deno_henshin.wav"));
        reflectedMap.put("deno_kotaewa_kiite_nai", new Sfx("deno_kotaewa_kiite_nai.mp3"));

	}
    
	@SuppressWarnings("unchecked")
	private HashMap<String, Sfx> getSoundsMap() {
        return (HashMap<String, Sfx>)ReflectionHacks.getPrivate((Object)CardCrawlGame.sound, (Class<?>)SoundMaster.class, "map");
    }

	@Override
    public void receiveEditCards() {
        logger.info("========================= 正在加载新的卡牌内容 =========================");
        RedCardsPatch.Postfix();
        GreenCardsPatch.Postfix();
        BlueCardsPatch.Postfix();
        //基础卡牌
        BaseMod.addCard(new Decade_Attack());
        BaseMod.addCard(new Decade_Defend());
        BaseMod.addCard(new FinalAttackRide());
        BaseMod.addCard(new KamenRide());
     
        //普通卡牌（白卡）
        BaseMod.addCard(new KamenRideDecade());
        BaseMod.addCard(new RideBooker());
        BaseMod.addCard(new FormRideDragon());
        BaseMod.addCard(new FormRideTitan());
        BaseMod.addCard(new FormRidePegasus());
        BaseMod.addCard(new Decade_Dash1());
        BaseMod.addCard(new AutoVajin());
        BaseMod.addCard(new Decade_Defend2());
        BaseMod.addCard(new FormRideStorm());
        BaseMod.addCard(new FormRideFlame());
        BaseMod.addCard(new UnarmedAttack1());
        BaseMod.addCard(new UnarmedAttack3());
        BaseMod.addCard(new UnarmedAttack5());
        BaseMod.addCard(new UnarmedAttack7());
        BaseMod.addCard(new Blade_Slash());
        BaseMod.addCard(new Blade_Beat());
        BaseMod.addCard(new Hibiki_Attack1());
        BaseMod.addCard(new Hibiki_Attack2());
        BaseMod.addCard(new Kuuga_TitanSword());
        BaseMod.addCard(new Kuuga_DragonRod());
        BaseMod.addCard(new Kuuga_PegasusBowgun());
        BaseMod.addCard(new Agito_FlameSaber());
        BaseMod.addCard(new Agito_StormHalberd());
        BaseMod.addCard(new Ryuki_DragSaber());
        BaseMod.addCard(new Faiz_Edge());
        BaseMod.addCard(new Faiz_Phone());
        BaseMod.addCard(new Faiz_Shot());
        BaseMod.addCard(new Kabuto_KunaiGun());
        BaseMod.addCard(new Hibiki_Ongekibou());
        
        //稀有卡牌（金卡）
        BaseMod.addCard(new Kuuga_Rising());
        BaseMod.addCard(new TimeVent());
        BaseMod.addCard(new NMDAZYYGL());
        BaseMod.addCard(new UnarmedAttack9());
        BaseMod.addCard(new FormRideAxel());
        BaseMod.addCard(new Hibiki_FEVER());
        BaseMod.addCard(new Kabuto_ClockUp());
        
        //罕见卡牌（蓝卡）
        BaseMod.addCard(new Decade_Invisible());
        BaseMod.addCard(new Decade_Illusion());
        BaseMod.addCard(new KamenRideKuuga());
        BaseMod.addCard(new Decade_Dash2());
        BaseMod.addCard(new Decade_Defend3());
        BaseMod.addCard(new KamenRideAgito());
        BaseMod.addCard(new UnarmedAttack6());
        BaseMod.addCard(new UnarmedAttack4());
        BaseMod.addCard(new UnarmedAttack8());
        BaseMod.addCard(new PrometheusPower());
        BaseMod.addCard(new FormRide());
        BaseMod.addCard(new KamenRideRyuki());
        BaseMod.addCard(new MirrorWorld());
        BaseMod.addCard(new KamenRideFaiz());
        BaseMod.addCard(new KamenRideBlade());
        BaseMod.addCard(new Blade_Kick());
        BaseMod.addCard(new Blade_Mach());
        BaseMod.addCard(new Blade_Metal());
        BaseMod.addCard(new Hibiki_Attack3());
        BaseMod.addCard(new KamenRideKabuto());
        BaseMod.addCard(new PhotonAcceleration());
        BaseMod.addCard(new DragClaw());
        BaseMod.addCard(new DragShield());
        BaseMod.addCard(new Dragreder());
        BaseMod.addCard(new AttackRide());
        BaseMod.addCard(new Blade_BlayRouzer());
        
        //特殊卡牌（灰卡）
        BaseMod.addCard(new Decade_Blast());
        BaseMod.addCard(new Decade_Slash());
        BaseMod.addCard(new RideBooker_Attack());
        BaseMod.addCard(new RideBooker_Shoot());
        BaseMod.addCard(new PegasusDefend());
        BaseMod.addCard(new PegasusAttack());
        BaseMod.addCard(new AgitoPower());
        BaseMod.addCard(new StormSpecialCard());
        BaseMod.addCard(new FlameSpecialCard());
        BaseMod.addCard(new CrimsonSmash());
        BaseMod.addCard(new SparkleCut());
        BaseMod.addCard(new PunchingUnit());
        BaseMod.addCard(new Blade_Thunder());
        BaseMod.addCard(new Kabuto_PutOn());
        BaseMod.addCard(new Kabuto_CastOff());
        
        //基础卡牌解锁
        UnlockTracker.unlockCard("Decade_Attack");
        UnlockTracker.unlockCard("Decade_Defend");
        UnlockTracker.unlockCard("FinalAttackRide");
        UnlockTracker.unlockCard("KamenRide");
        
        //普通卡牌解锁（白卡）
        UnlockTracker.unlockCard("KamenRideDecade");
        UnlockTracker.unlockCard("RideBooker");
        UnlockTracker.unlockCard("FormRideDragon");
        UnlockTracker.unlockCard("FormRideTitan");
        UnlockTracker.unlockCard("FormRidePegasus");
        UnlockTracker.unlockCard("Decade_Dash1");
        UnlockTracker.unlockCard("AutoVajin");
        UnlockTracker.unlockCard("Decade_Defend2");
        UnlockTracker.unlockCard("FormRideStorm");
        UnlockTracker.unlockCard("FormRideFlame");
        UnlockTracker.unlockCard("UnarmedAttack1");
        UnlockTracker.unlockCard("UnarmedAttack3");
        UnlockTracker.unlockCard("UnarmedAttack5");
        UnlockTracker.unlockCard("UnarmedAttack7");
        UnlockTracker.unlockCard("Blade_Slash");
        UnlockTracker.unlockCard("Blade_Beat");
        UnlockTracker.unlockCard("Hibiki_Attack1");
        UnlockTracker.unlockCard("Hibiki_Attack2");
        UnlockTracker.unlockCard("Kuuga_TitanSword");
        UnlockTracker.unlockCard("Kuuga_DragonRod");
        UnlockTracker.unlockCard("Kuuga_PegasusBowgun");
        UnlockTracker.unlockCard("Agito_FlameSaber");
        UnlockTracker.unlockCard("Agito_StormHalberd");
        UnlockTracker.unlockCard("Ryuki_DragSaber");
        UnlockTracker.unlockCard("Faiz_Edge");
        UnlockTracker.unlockCard("Faiz_Phone");
        UnlockTracker.unlockCard("Faiz_Shot");
        UnlockTracker.unlockCard("Kabuto_KunaiGun");
        UnlockTracker.unlockCard("Hibiki_Ongekibou");
        
        //稀有卡牌解锁（金卡）
        UnlockTracker.unlockCard("Kuuga_Rising");
        UnlockTracker.unlockCard("TimeVent");
        UnlockTracker.unlockCard("NMDAZYYGL");
        UnlockTracker.unlockCard("UnarmedAttack9");
        UnlockTracker.unlockCard("FormRideAxel");
        UnlockTracker.unlockCard("Hibiki_FEVER");
        UnlockTracker.unlockCard("Kabuto_ClockUp");
        
        //罕见卡牌解锁（蓝卡）
        UnlockTracker.unlockCard("Decade_Invisible");
        UnlockTracker.unlockCard("Decade_Illusion");
        UnlockTracker.unlockCard("KamenRideKuuga");
        UnlockTracker.unlockCard("Decade_Dash2");
        UnlockTracker.unlockCard("Decade_Defend3");
        UnlockTracker.unlockCard("KamenRideAgito");
        UnlockTracker.unlockCard("UnarmedAttack6");
        UnlockTracker.unlockCard("UnarmedAttack4");
        UnlockTracker.unlockCard("UnarmedAttack8");
        UnlockTracker.unlockCard("PrometheusPower");
        UnlockTracker.unlockCard("FormRide");
        UnlockTracker.unlockCard("KamenRideRyuki");
        UnlockTracker.unlockCard("MirrorWorld");
        UnlockTracker.unlockCard("KamenRideFaiz");
        UnlockTracker.unlockCard("KamenRideBlade");
        UnlockTracker.unlockCard("Blade_Kick");
        UnlockTracker.unlockCard("Blade_Mach");
        UnlockTracker.unlockCard("Blade_Metal");
        UnlockTracker.unlockCard("Hibiki_Attack3");
        UnlockTracker.unlockCard("KamenRideKabuto");
        UnlockTracker.unlockCard("PhotonAcceleration");
        UnlockTracker.unlockCard("DragClaw");
        UnlockTracker.unlockCard("DragShield");
        UnlockTracker.unlockCard("Dragreder");
        UnlockTracker.unlockCard("AttackRide");
        UnlockTracker.unlockCard("Blade_BlayRouzer");
        
        //特殊卡牌解锁（灰卡）
        UnlockTracker.unlockCard("Decade_Blast");
        UnlockTracker.unlockCard("Decade_Slash");
        UnlockTracker.unlockCard("RideBooker_Attack");
        UnlockTracker.unlockCard("RideBooker_Shoot");
        UnlockTracker.unlockCard("PegasusDefend");
        UnlockTracker.unlockCard("PegasusAttack");
        UnlockTracker.unlockCard("AgitoPower");
        UnlockTracker.unlockCard("StormSpecialCard");
        UnlockTracker.unlockCard("FlameSpecialCard");
        UnlockTracker.unlockCard("CrimsonSmash");
        UnlockTracker.unlockCard("SparkleCut");
        UnlockTracker.unlockCard("PunchingUnit");
        UnlockTracker.unlockCard("Blade_Thunder");
        UnlockTracker.unlockCard("Kabuto_PutOn");
        UnlockTracker.unlockCard("Kabuto_CastOff");
        
        logger.info("=========================加载新的卡牌内容成功===============================");
    }

    @Override
    public void receiveEditRelics() {
        logger.info("========================= 正在加载新的遗物内容 =========================");
        BaseMod.addRelicToCustomPool((AbstractRelic)new Decaderiver(), AbstractCardEnum.DCD);
        logger.info("==========================加载新的遗物内容成功===========================");
    }

    @Override
    public void receiveEditStrings() {
    	 logger.info("正在加载对应语言文本信息");
         String card;
         String relic;
         String power;
         String orb;
         if (Settings.language == Settings.GameLanguage.ZHS) {
             logger.info("简体中文");
             card = "localization/zhs/DCDCards.json";
             relic = "localization/zhs/DCDRelics.json";
             power = "localization/zhs/DCDPower.json";
             orb = "localization/zhs/DCDOrbs.json";
         }
         else if (Settings.language == Settings.GameLanguage.ZHT) {
             logger.info("繁体中文");
             card = "localization/zhs/DCDCards.json";
             relic = "localization/zhs/DCDRelics.json";
             power = "localization/zhs/DCDPower.json";
             orb = "localization/zhs/DCDOrbs.json";
         }
         else {
             logger.info("英文");
             card = "localization/eng/DCDCards.json";
             relic = "localization/eng/DCDRelics.json";
             power = "localization/eng/DCDPower.json";
             orb = "localization/eng/DCDOrbs.json";
         }
         final String relicStrings = Gdx.files.internal(relic).readString(String.valueOf(StandardCharsets.UTF_8));
         BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
         final String cardStrings = Gdx.files.internal(card).readString(String.valueOf(StandardCharsets.UTF_8));
         BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
         final String powerStrings = Gdx.files.internal(power).readString(String.valueOf(StandardCharsets.UTF_8));
         BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
         final String orbStrings = Gdx.files.internal(orb).readString(String.valueOf(StandardCharsets.UTF_8));
         BaseMod.loadCustomStrings(OrbStrings.class, orbStrings);
         logger.info("语言文本加载成功");
    }

	@Override
	public void receiveCardUsed(AbstractCard c) {
		if(c.type == CardType.ATTACK && AbstractDungeon.player.hasPower("AutoVajinPower")) {
			AutoVajinPower.AutoVajinAttack = true;
		}
	}

	
	@Override
	public void receiveOnBattleStart(AbstractRoom arg0) {
		TurnTimer.atNextBattle();
		TurnTimer.atBattleStart();
	}

	@Override
	public void receiveStartGame() {
		//ban遗物
		if(AbstractDungeon.player instanceof Decade) {
			AbstractDungeon.uncommonRelicPool.remove("MummifiedHand");
			AbstractDungeon.uncommonRelicPool.remove("Mummified Hand");
			AbstractDungeon.uncommonRelicPool.remove("SingingBowl");
			AbstractDungeon.uncommonRelicPool.remove("Singing Bowl");
		}
	}
}

