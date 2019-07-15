package dcdmod.Actions;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcdmod.Card.SelectCard.BladeBeat_s;
import dcdmod.Card.SelectCard.BladeKick_s;
import dcdmod.Card.SelectCard.BladeMach_s;
import dcdmod.Card.SelectCard.BladeMetal_s;
import dcdmod.Card.SelectCard.BladeSlash_s;
import dcdmod.Card.SelectCard.BladeThunder_s;
import dcdmod.Card.Special.Blade_Beat_s;
import dcdmod.Card.Special.Blade_Kick_s;
import dcdmod.Card.Special.Blade_Mach_s;
import dcdmod.Card.Special.Blade_Metal_s;
import dcdmod.Card.Special.Blade_Slash_s;
import dcdmod.Card.Special.Blade_Thunder;
import dcdmod.Helper.SpecialRideBooker;

public class RouzeCardAction {
	static CardGroup group = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
	static boolean RouzeCard = true;
	static boolean selectcard = false;
	static AbstractCard c = null;
	public static void RouzeCard() {
		if(RouzeCard) {
			group.addToBottom(new BladeMach_s());
			group.addToBottom(new BladeMetal_s());
			group.addToBottom(new BladeThunder_s());
			group.addToBottom(new BladeKick_s());
			group.addToBottom(new BladeBeat_s());
			group.addToBottom(new BladeSlash_s());
			AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张获得", false, false, true, false);
			RouzeCard = false;
			selectcard = true;
		}
	}
	
	public static void update() {
		if(selectcard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
			switch(AbstractDungeon.gridSelectScreen.selectedCards.get(0).cardID) {
			case "BladeBeat_s":
				c = new Blade_Beat_s();
				break;
			case "BladeKick_s":
				c = new Blade_Kick_s();
				break;
			case "BladeMach_s":
				c = new Blade_Mach_s();
				break;
			case "BladeMetal_s":
				c = new Blade_Metal_s();
				break;
			case "BladeSlash_s":
				c = new Blade_Slash_s();
				break;
			case "BladeThunder_s":
				c = new Blade_Thunder();
				break;
			}
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
			c = null;
			AbstractDungeon.gridSelectScreen.selectedCards.clear();
			group.clear();
			SpecialRideBooker.updatecurrentpoint();
			SpecialRideBooker.bladepoint = SpecialRideBooker.currentpoint;
			RouzeCard = true;
			selectcard = false;
		}
	}
}

