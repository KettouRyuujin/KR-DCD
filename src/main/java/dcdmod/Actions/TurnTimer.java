package dcdmod.Actions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import dcdmod.DCDmod;
import dcdmod.Characters.Decade;
import dcdmod.Helper.SpecialAutoVajin;
import dcdmod.Helper.SpecialDragreder;
import dcdmod.Helper.SpecialHibikiTaikoKey;
import dcdmod.Helper.SpecialHibikiTaikoScore;
import dcdmod.Helper.SpecialRideBooker;
import dcdmod.Helper.SpecialTaikoEffects;
import dcdmod.Patches.AbstractAnimation;
import dcdmod.Patches.AbstractSummonedAnimation;
import dcdmod.Patches.ModBaseClassForSLExample;
import dcdmod.Patches.HibikiTaikoKeyEvent;
import dcdmod.Power.TimeVentPower;



public class TurnTimer {
	public static boolean BattleEnd = false;
	
	public static void atEndOfRound() {
		//RideBooker使用次数控制
		SpecialRideBooker.decadenextturn = true;
		SpecialRideBooker.ryukinextturn = true;
		SpecialRideBooker.currentpoint = 0;
		SpecialRideBooker.decadepoint = 0;
		SpecialRideBooker.ryukipoint = 0;
		SpecialRideBooker.bladepoint = 0;
		
		//藏匿控制
		int dx = AbstractDungeon.player.drawPile.group.size();
		if(dx!=1) {
			AbstractCard rc = null;
			for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
				if(c.cardID == "NMDAZYYGL") {
					AbstractDungeon.player.drawPile.group.remove(c);
					rc = c;
					break;
				}
			}
			if(rc != null) {
				AbstractDungeon.player.drawPile.group.add(0, rc);
			}
		}
		
	}
	public static void atNextBattle() {
		//战斗开关
		BattleEnd = true;
		
		//模型控制
		Decade.cf = 0;
		
		//太鼓控制
		HibikiTaikoKeyEvent.TaikoNumber = -1;
		HibikiTaikoKeyEvent.ComboPoint = 0;
		HibikiTaikoKeyEvent.ActionPoint = 1;
		SpecialHibikiTaikoKey.TimerTime = 0.0F;
		if(HibikiTaikoKeyEvent.Fever){
			HibikiTaikoKeyEvent.Fever = false;
			if(!DCDmod.AnimationTrigger) {
				SpecialTaikoEffects.a = 4;
				SpecialTaikoEffects.update();
			}
		}
		SpecialHibikiTaikoScore.img = ImageMaster.loadImage("img/char/DCD_Animational/hibiki/taikoscore/Score.png");
		SpecialHibikiTaikoScore.draw_height = 19.0f;
		SpecialHibikiTaikoScore.draw_width = 235.0f;
		SpecialHibikiTaikoScore.ishide = true;
		
		//BGM控制
		CardCrawlGame.sound.stop("faiz_BGM");
		CardCrawlGame.sound.stop("faiz_BGM2");
		CardCrawlGame.sound.stop("ryuki_BGM");
		CardCrawlGame.sound.stop("blade_BGM1");
		CardCrawlGame.sound.stop("blade_BGM2");
		CardCrawlGame.sound.stop("hibiki_BGM");
		CardCrawlGame.sound.stop("kabuto_BGM");
		CardCrawlGame.sound.stop("agito_BGM1");
		CardCrawlGame.sound.stop("agito_BGM2");
		
		//变身及RideBooker使用次数控制
		SpecialRideBooker.decadenextturn = true;
		SpecialRideBooker.ryukinextturn = true;
		SpecialRideBooker.bladenextturn = true;
		SpecialRideBooker.currentpoint = 0;
		SpecialRideBooker.decadepoint = 0;
		SpecialRideBooker.ryukipoint = 0;
		SpecialRideBooker.bladepoint = 0;
		SpecialRideBooker.nodecade = false;
		
		//镜世界遗物控制
		AbstractRelic r1 = null;
		if(AbstractDungeon.player.hasRelic("MirrorWorldRelic")) {
			for(AbstractRelic r: AbstractDungeon.player.relics) {
				if(r.relicId == "MirrorWorldRelic") {
					r1 = r;
				}
			}
			if(r1!=null) {
				AbstractDungeon.player.relics.remove(r1);
			}
		}
		
		//超速光子遗物控制
		/*AbstractRelic r2 = null;
		if(AbstractDungeon.player.hasRelic("PhotonAccelerationRelic")) {
			for(AbstractRelic r: AbstractDungeon.player.relics) {
				if(r.relicId == "PhotonAccelerationRelic") {
					r2 = r;
				}
			}
			if(r2!=null) {
				AbstractDungeon.player.relics.remove(r2);
			}
		}*/
		
		//Faiz加速模式控制
		if(EnterButtonAction.AxelForm) {
			EnterButtonAction.AxelForm = false;
			ModBaseClassForSLExample.FaizGear1 = false;
			ModBaseClassForSLExample.FaizGear2 = false;
			ModBaseClassForSLExample.FaizGear3 = false;
			ModBaseClassForSLExample.FaizGear4 = false;
			ModBaseClassForSLExample.FaizPoint = 0;
		}
		
		//Faiz Gear和光子血液控制
		ModBaseClassForSLExample.FaizGear1 = EnterButtonAction.FaizPhone;
		ModBaseClassForSLExample.FaizGear2 = EnterButtonAction.FaizPointer;
		ModBaseClassForSLExample.FaizGear3 = EnterButtonAction.FaizShot;
		ModBaseClassForSLExample.FaizGear4 = EnterButtonAction.FaizEdge;
		ModBaseClassForSLExample.FaizPoint = FaizGearAction.FaizPoint;
		
		//召唤兽动画控制
		AbstractSummonedAnimation.clearAll();
		
		//其他动画控制
		AbstractAnimation.clearAll();
	}
	public static void atBattleStart() {
		//战斗开关
		BattleEnd = false;
		
		//模型控制
		Decade.cf = 0;
		
		//太鼓控制
		HibikiTaikoKeyEvent.TaikoNumber = -1;
		HibikiTaikoKeyEvent.ComboPoint = 0;
		HibikiTaikoKeyEvent.ActionPoint = 1;
		SpecialHibikiTaikoKey.TimerTime = 0.0F;
		if(HibikiTaikoKeyEvent.Fever){
			HibikiTaikoKeyEvent.Fever = false;
			if(!DCDmod.AnimationTrigger) {
				SpecialTaikoEffects.a = 4;
				SpecialTaikoEffects.update();
			}
		}
		
		//按键读取
		HibikiTaikoKeyEvent.loadKeySettings();
		
		//时间降临遗物控制
		if(ModBaseClassForSLExample.timevent&&ModBaseClassForSLExample.testOutput>0) {
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new TimeVentPower(AbstractDungeon.player, ModBaseClassForSLExample.testOutput), ModBaseClassForSLExample.testOutput));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, ModBaseClassForSLExample.testOutput * 2), ModBaseClassForSLExample.testOutput * 2));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, ModBaseClassForSLExample.testOutput * 2), ModBaseClassForSLExample.testOutput * 2));
		}
		if(!ModBaseClassForSLExample.TimeVentUpgraded&&ModBaseClassForSLExample.testOutput>0) {
			boolean removetimevent = false;
			for(AbstractCard c : AbstractDungeon.player.hand.group) {
				if(c.cardID == "TimeVent" && c.upgraded != true) {
					AbstractDungeon.player.hand.group.remove(c);
					removetimevent = true;
					break;
				}
			}
			if(!removetimevent) {
				for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
					if(c.cardID == "TimeVent" && c.upgraded != true) {
						AbstractDungeon.player.drawPile.group.remove(c);
						break;
					} 
				}
			}
			for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
				if(c.cardID == "TimeVent" && c.upgraded != true) {
					AbstractDungeon.player.masterDeck.group.remove(c);
					break;
				} 
			}
		}
		
		//镜世界遗物控制
		AbstractRelic r1 = null;
		if(AbstractDungeon.player.hasRelic("MirrorWorldRelic")) {
			for(AbstractRelic r: AbstractDungeon.player.relics) {
				if(r.relicId == "MirrorWorldRelic") {
					r1 = r;
				}
			}
			if(r1!=null) {
				AbstractDungeon.player.relics.remove(r1);
			}
		}
		
		//超速光子遗物控制
		/*AbstractRelic r2 = null;
		if(AbstractDungeon.player.hasRelic("PhotonAccelerationRelic")) {
			for(AbstractRelic r: AbstractDungeon.player.relics) {
				if(r.relicId == "PhotonAccelerationRelic") {
					r2 = r;
				}
			}
			if(r2!=null) {
				AbstractDungeon.player.relics.remove(r2);
			}
		}*/
		
		//藏匿控制1
		int dx = AbstractDungeon.player.drawPile.group.size();
		if(dx!=1) {
			AbstractCard rc = null;
			for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
				if(c.cardID == "NMDAZYYGL") {
					AbstractDungeon.player.drawPile.group.remove(c);
					rc = c;
					break;
				}
			}
			if(rc != null) {
				AbstractDungeon.player.drawPile.group.add(0, rc);
			}
		}
		
		//藏匿控制2
		AbstractCard rc = null;
		for(AbstractCard c : AbstractDungeon.player.hand.group) {
			if(c.cardID == "NMDAZYYGL") {
				AbstractDungeon.player.hand.group.remove(c);
				rc = c;
				break;
			}
		}
		if(rc != null) {
			AbstractDungeon.player.drawPile.group.add(0, rc);
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
		}
		
		//召唤兽动画控制
		AbstractSummonedAnimation.clearAll();
		SpecialDragreder.DRAGREDERTrigger1 = true;
		SpecialAutoVajin.AutoVajinTrigger1 = true;
		SpecialTaikoEffects.TAIKOTrigger1 = true;
		
		//其他动画控制
		AbstractAnimation.clearAll();
		
	}
	
	public static void StopBGM() {
		//BGM控制
		CardCrawlGame.sound.stop("faiz_BGM");
		CardCrawlGame.sound.stop("faiz_BGM2");
		CardCrawlGame.sound.stop("ryuki_BGM");
		CardCrawlGame.sound.stop("blade_BGM1");
		CardCrawlGame.sound.stop("blade_BGM2");
		CardCrawlGame.sound.stop("hibiki_BGM");
		CardCrawlGame.sound.stop("kabuto_BGM");
		CardCrawlGame.sound.stop("agito_BGM1");
		CardCrawlGame.sound.stop("agito_BGM2");
	}
}

