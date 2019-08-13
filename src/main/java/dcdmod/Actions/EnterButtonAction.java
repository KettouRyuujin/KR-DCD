package dcdmod.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import dcdmod.Card.SelectCard.FaizEdge_s;
import dcdmod.Card.SelectCard.FaizPhone_s;
import dcdmod.Card.SelectCard.FaizPointer_s;
import dcdmod.Card.SelectCard.FaizShot_s;
import dcdmod.Card.Special.CrimsonSmash;
import dcdmod.Card.Special.PunchingUnit;
import dcdmod.Card.Special.SparkleCut;
import dcdmod.Helper.SpecialFaizButton;
import dcdmod.Vfx.Axel_attack;
import dcdmod.Vfx.Faiz_gunattack;

public class EnterButtonAction {
	private static CardGroup group = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
	private static boolean FaizGear = true;
	private static boolean selectcard = false;
	private static boolean HasGear = false;
	public static boolean FaizPhone = true;
	public static boolean FaizPointer = true;
	public static boolean FaizShot = true;
	public static boolean FaizEdge = true;
	public static boolean AxelForm = false;
	public static int PointerPoint = 0;
	public static int ShotPoint = 0;
	public static int EdgePoint = 0;
	private static AbstractCard c = null;
	public static void EnterButton() {
		if(FaizGear) {
			group.addToBottom(new FaizPhone_s());
			group.addToBottom(new FaizPointer_s());
			group.addToBottom(new FaizShot_s());
			group.addToBottom(new FaizEdge_s());
			AbstractDungeon.gridSelectScreen.open(group, 1, "选择1种插件激发", false, false, true, false);
			FaizGear = false;
			selectcard = true;
		}
	}
	
	public static void update() {
		if(selectcard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
			switch(AbstractDungeon.gridSelectScreen.selectedCards.get(0).cardID) {
			case "FaizPhone_s":
				if(FaizPhone&&!AxelForm) {
					HasGear = true;
					FaizPhone = false;
					for(int i=0;i<3;i++) {
							AbstractDungeon.actionManager.addToTop(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.0F));
							AbstractDungeon.actionManager.addToTop(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true),new DamageInfo(AbstractDungeon.player, 5, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));	
					}
					AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_gunattack(), 0F));
				}
				break;
			case "FaizPointer_s":
				if(FaizPointer&&!AxelForm){
					HasGear = true;
					FaizPointer = false;
					c = new CrimsonSmash();
					CardCrawlGame.sound.playA("faiz_button", 0.0f);
				}
				if(AxelForm) {
					HasGear = true;
					c = new CrimsonSmash();
					PointerPoint += 1;
					if(PointerPoint > 1) {
						AbstractDungeon.actionManager.addToBottom(new VFXAction(new Axel_attack(), 0F));
						AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
					}
					else {
						c.freeToPlayOnce = true;
						c.purgeOnUse = true;
						AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, AbstractDungeon.getMonsters().getRandomMonster(true), c.energyOnUse));
					}
				}
				break;
			case "FaizShot_s":
				if(FaizShot&&!AxelForm) {
					HasGear = true;
					FaizShot = false;
					c = new PunchingUnit();
					CardCrawlGame.sound.playA("faiz_button", 0.0f);
				}
				if(AxelForm) {
					HasGear = true;
					c = new PunchingUnit();
					ShotPoint += 1;
					if(ShotPoint > 1) {
						AbstractDungeon.actionManager.addToBottom(new VFXAction(new Axel_attack(), 0F));
						AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
					}
					else {
						c.freeToPlayOnce = true;
						c.purgeOnUse = true;
						AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, AbstractDungeon.getMonsters().getRandomMonster(true), c.energyOnUse));
					}
				}
				break;
			case "FaizEdge_s":
				if(FaizEdge&&!AxelForm) {
					HasGear = true;
					FaizEdge = false;
					c = new SparkleCut();
					CardCrawlGame.sound.playA("faiz_button", 0.0f);
				}
				if(AxelForm) {
					HasGear = true;
					c = new SparkleCut();
					EdgePoint += 1;
					if(EdgePoint > 1) {
						AbstractDungeon.actionManager.addToBottom(new VFXAction(new Axel_attack(), 0F));
						AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
					}
					else {
						c.freeToPlayOnce = true;
						c.purgeOnUse = true;
						AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, AbstractDungeon.getMonsters().getRandomMonster(true), c.energyOnUse));
					}
				}
				break;
			}
			if(!HasGear) {
				AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "你无法激发该插件", true));
			}
			if(c != null&&!AxelForm) {
				AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
			}
			AbstractDungeon.gridSelectScreen.selectedCards.clear();
			group.clear();
			c = null;
			FaizGear = true;
			selectcard = false;
			HasGear = false;
			SpecialFaizButton.haskamenpower = true;
		}
	}
}

