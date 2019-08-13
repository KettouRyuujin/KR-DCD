package dcdmod.Patches;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import dcdmod.DCDmod;
import dcdmod.Actions.RelieveAction;
import dcdmod.Actions.ReturnRandomNumberAction;
import dcdmod.Actions.TaikoAttackAction;
import dcdmod.Characters.Decade;
import dcdmod.Helper.SpecialHibikiTaikoKey;
import dcdmod.Helper.SpecialHibikiTaikoScore;
import dcdmod.Helper.SpecialTaikoEffects;
import dcdmod.Power.FeverPower;
import dcdmod.Power.HibikiKurenaiPower;
import dcdmod.Power.HibikiKurenaiSpecialPower;
import dcdmod.Vfx.Hibiki_Kurenai_henshin_SoundsAndAnimation;
import dcdmod.Vfx.Hibiki_taikoL1;
import dcdmod.Vfx.Hibiki_taikoL2;
import dcdmod.Vfx.Hibiki_taikoR1;
import dcdmod.Vfx.Hibiki_taikoR2;
import dcdmod.Vfx.Hibiki_taikoaction;
import dcdmod.Vfx.Hibiki_taikoprepare;





public class HibikiTaikoKeyEvent {


	private static InputAction[] TaikoButtonActions = new InputAction[5];
	public static String[] TaikoArray = new String[]{"","","","",""};
	public static int TaikoNumber = -1;
	public static int ComboPoint = 0;
	public static int ActionPoint = 1;
	public static boolean Fever = false;
	public static boolean TaikoTrigger = false;
	//static AbstractCard c = null;
	
	public HibikiTaikoKeyEvent() {
		loadKeySettings();
	}
	
	
	public static void loadKeySettings() {
		for (int i = 0; i < TaikoButtonActions.length; i++) {
			TaikoButtonActions[i] = new InputAction(DCDmod.TaikoKeys[i]);
		}
	}
	
	public static void update() {
		for (int i = 0; i < TaikoButtonActions.length; i++) {
			if (TaikoButtonActions[i].isJustPressed()) {
				if(ActionPoint>0) {
    				switch(i) {
    				case 0://U △ CHAKA
    					CardCrawlGame.sound.playA("CHAKA", 0.0f);
    					AbstractDungeon.actionManager.addToBottom(new VFXAction(new Hibiki_taikoL1(100.0F * Settings.scale, AbstractDungeon.floorY + 400.0F * Settings.scale), 0F));
    					KeyCount();
    					TaikoArray[TaikoNumber] = "CHAKA";
    					break;
    				case 1://I ○ PON
    					CardCrawlGame.sound.playA("PON", 0.0f);
    					AbstractDungeon.actionManager.addToBottom(new VFXAction(new Hibiki_taikoL2(100.0F * Settings.scale, AbstractDungeon.floorY + 400.0F * Settings.scale), 0F));
    					KeyCount();
    					TaikoArray[TaikoNumber] = "PON";
    					break;
    				case 2://O × DON
    					CardCrawlGame.sound.playA("DON", 0.0f);
    					AbstractDungeon.actionManager.addToBottom(new VFXAction(new Hibiki_taikoR2(100.0F * Settings.scale, AbstractDungeon.floorY + 400.0F * Settings.scale), 0F));
    					KeyCount();
    					TaikoArray[TaikoNumber] = "DON";
    					break;
    				case 3://P □ PATA
    					CardCrawlGame.sound.playA("PATA", 0.0f);
    					AbstractDungeon.actionManager.addToBottom(new VFXAction(new Hibiki_taikoR1(100.0F * Settings.scale, AbstractDungeon.floorY + 400.0F * Settings.scale), 0F));
    					KeyCount();
    					TaikoArray[TaikoNumber] = "PATA";
    					break;
    				case 4://SPACE 确认
    					TaikoSpace();
    					break;
    				}
				}
    			else{
    				AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "行动点数不足，无法敲击音色", true));
    			}
			}
		}
		if(ComboPoint >= 10 && !Fever) {
			CardCrawlGame.sound.playA("Fever", 0.0f);
			Fever = true;
			SpecialHibikiTaikoScore.img = ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taikoscore/FeverScore.png");
			SpecialHibikiTaikoScore.draw_height = 284.0f;
			SpecialHibikiTaikoScore.draw_width = 235.0f;
			SpecialHibikiTaikoScore.ishide = false;
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new Hibiki_taikoprepare(), 1.0F));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FeverPower(AbstractDungeon.player, 1), 1));
		}
	}
	
	public static void FeverOut(boolean iskey) {
		if(iskey) {
			if(Fever) {
				ComboPoint -= ComboPoint/2;
			}
			else {
				ComboPoint -= 1;
				if(ComboPoint<0) {
					ComboPoint = 0;
				}
			}
		}
		else {
			ComboPoint -= 1;
			if(ComboPoint<0) {
				ComboPoint = 0;
			}
		}
		if(ComboPoint<10 && Fever) {
			Fever = false;
			SpecialHibikiTaikoScore.img = ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taikoscore/UnfeverScore.png");
			SpecialHibikiTaikoScore.draw_height = 284.0f;
			SpecialHibikiTaikoScore.draw_width = 235.0f;
			SpecialHibikiTaikoScore.ishide = false;
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "FeverPower"));
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(49);
			if(!DCDmod.AnimationTrigger) {
				SpecialTaikoEffects.a = 4;
				SpecialTaikoEffects.update();
			}
		}
	}
	
	private static void KeyCount() {
		TaikoNumber +=1;
		if(TaikoNumber>4) {
			TaikoNumber = 0;
		}
		SpecialHibikiTaikoKey.TimerTime = 1.5F;
		if(Fever) {
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new Hibiki_taikoaction(), 0.0F));
		}
	}
	
	private static void TaikoCount() {
		ComboPoint += 2;
		if(Fever) {
			ComboPoint += 1;
		}
		if(ActionPoint>0) {
			ActionPoint -=1;
		}
	}
	
	private static void TaikoSpace() {
			switch(TaikoArray[0]+TaikoArray[1]+TaikoArray[2]+TaikoArray[3]+TaikoArray[4]) {
			case "PATAPATAPATAPON"/*前进*/:
				if(Fever && ActionPoint > 0) {
					CardCrawlGame.sound.playA("Forward", 0.0f);
					TaikoAction("Forward");
					TaikoCount();
				}
				else {
					FeverOut(true);
				}
				break;
			case "PONPONPATAPON"/*进攻*/:
				CardCrawlGame.sound.playA("Attack", 0.0f);
				TaikoAction("Attack");
				TaikoCount();
				break;
			case "CHAKACHAKAPATAPON"/*防御*/:
				CardCrawlGame.sound.playA("Defend", 0.0f);
				TaikoAction("Defend");
				TaikoCount();
				break;
			case "PONPATAPONPATA"/*后退*/:
				if(Fever && ActionPoint > 0) {
					CardCrawlGame.sound.playA("Back", 0.0f);
					TaikoAction("Back");
					TaikoCount();
				}
				else {
					FeverOut(true);
				}
				break;
			case "DONDONCHAKACHAKA"/*跳跃*/:
				if(Fever && ActionPoint > 0) {
					CardCrawlGame.sound.playA("Jump", 0.0f);
					TaikoAction("Jump");
					TaikoCount();
				}
				else {
					FeverOut(true);
				}
				break;
			case "PATAPONDONCHAKA"/*解除异常状态*/:
				CardCrawlGame.sound.playA("Relieve", 0.0f);
				TaikoAction("Relieve");
				TaikoCount();
				break;
			case "PONPONCHAKACHAKA"/*蓄力*/:
				if (EnergyPanel.totalCount > 0) {
					CardCrawlGame.sound.playA("Accumulate", 0.0f);
					TaikoAction("Accumulate");
					TaikoCount();
				}
				else {
					AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "能量不足，无法触发效果", true));
					FeverOut(true);
				}
				break;
			case "PATAPONPATAPON"/*暂停*/:
				if(ActionPoint>=6) {
						TaikoAction("Suspend");
					TaikoCount();
				}
				else {
					FeverOut(true);
				}
				break;
			case "DONDONDONDONDON"/*奇迹*/:
				if(Fever) {
						CardCrawlGame.sound.playA("Buff", 0.0f);
						TaikoAction("Buff");
						TaikoCount();
				}
				else {
					FeverOut(true);
				}
				break;
			default:
				FeverOut(true);
				break;
			}
			TaikoArray[0] = "";
			TaikoArray[1] = "";
			TaikoArray[2] = "";
			TaikoArray[3] = "";
			TaikoArray[4] = "";
			SpecialHibikiTaikoKey.img0=0;
			SpecialHibikiTaikoKey.img1=0;
			SpecialHibikiTaikoKey.img2=0;
			SpecialHibikiTaikoKey.img3=0;
			SpecialHibikiTaikoKey.img4=0;
			SpecialHibikiTaikoKey.img00 = ImageMaster.loadImage(SpecialHibikiTaikoKey.img[SpecialHibikiTaikoKey.img0]);
			SpecialHibikiTaikoKey.img11 = ImageMaster.loadImage(SpecialHibikiTaikoKey.img[SpecialHibikiTaikoKey.img1]);
			SpecialHibikiTaikoKey.img22 = ImageMaster.loadImage(SpecialHibikiTaikoKey.img[SpecialHibikiTaikoKey.img2]);
			SpecialHibikiTaikoKey.img33 = ImageMaster.loadImage(SpecialHibikiTaikoKey.img[SpecialHibikiTaikoKey.img3]);
			SpecialHibikiTaikoKey.img44 = ImageMaster.loadImage(SpecialHibikiTaikoKey.img[SpecialHibikiTaikoKey.img4]);
			TaikoNumber = -1;
			SpecialHibikiTaikoKey.TimerTime = 0.0F;
	}
	
	public static void TaikoAction(String name) {
		AbstractPlayer p = AbstractDungeon.player;
		switch(name){
			case "Attack"/*攻击*/:
				int d = 1;
				if(Fever) {
					d += ComboPoint/5;
				}
				for(int i=0;i<4;i++) {
					for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
						 if ((!monster.isDead) && (!monster.isDying)) {
							 AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(p, d, DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
						 }
					}
				}
				//50%几率触发，选择将1张烈火弹/鬼火/烈火剑加入手牌，已有时搜寻加入手牌并让其在使用前消耗降为0
				if(ReturnRandomNumberAction.ReturnRandomNumber()>5.0D) {
					AbstractDungeon.actionManager.addToBottom(new TaikoAttackAction());
				}
				break;
			case "Defend"/*防御*/:
				int b = 4;
				if(Fever) {
					b += ComboPoint/5;
				}
				AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, b));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FlameBarrierPower(p, b/2), b/2));
				break;
			case "Forward"/*前进*/:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, ActionPoint), ActionPoint));
				ActionPoint = 0;
				break;
			case "Back"/*后退*/:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, ActionPoint), ActionPoint));
				ActionPoint = 0;
				break;
			case "Jump"/*跳跃*/:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePower(p, ActionPoint), ActionPoint));
				ActionPoint = 0;
				break;
			case "Relieve"/*解除异常状态*/:
				String powerID = null;
				for(AbstractPower power : p.powers) {
					if(power.type == PowerType.DEBUFF) {
						powerID = power.ID;
						break;
					}
				}
				if(powerID!=null) {
					AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, powerID));
				}
				if(Fever) {
					AbstractDungeon.actionManager.addToBottom(new RelieveAction());
				}
				else if(powerID==null){
					AbstractDungeon.actionManager.addToBottom(new RelieveAction());
				}
				break;
			case "Accumulate"/*蓄力*/:
				ActionPoint += EnergyPanel.totalCount * 2;
				EnergyPanel.totalCount = 0;
				if(Fever) {
					ActionPoint += 1;
				}
				break;
			case "Suspend"/*暂停*/:
				ComboPoint += ActionPoint*2;
				ActionPoint = 0;
				break;
			case "Buff"/*奇迹*/:
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HibikiKurenaiSpecialPower(p, ComboPoint), ComboPoint));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HibikiKurenaiPower(p, 1), 1));
				ComboPoint = 0;
				Fever = false;
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "FeverPower"));
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Hibiki_Kurenai_henshin_SoundsAndAnimation(AbstractDungeon.player.drawX, AbstractDungeon.player.drawY), 2.8F));
				if(!DCDmod.AnimationTrigger) {
					SpecialTaikoEffects.a = 4;
					SpecialTaikoEffects.update();
				}
				break;
			default:
		}
	}
}
