package dcdmod.Actions;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import dcdmod.Card.Special.DragClaw_s;
import dcdmod.Card.Special.DragShield_s;
import dcdmod.Card.Uncommon.DragClaw;
import dcdmod.Card.Uncommon.DragShield;
import dcdmod.Card.Uncommon.Dragreder;
import dcdmod.Helper.SpecialRideBooker;

public class AdventCardAction {
	static CardGroup group = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
	static boolean AdventCard = true;
	static boolean selectcard = false;
	static AbstractCard c = null;
	public static void VentCard() {
		if(AdventCard) {
			c = new DragClaw();
			c.exhaust = true;
			group.addToBottom(c);
			c = new DragShield();
			c.exhaust = true;
			group.addToBottom(c);
			group.addToBottom(new Dragreder());
			AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张获得", false, false, true, false);
			AdventCard = false;
			selectcard = true;
		}
	}
	
	public static void update() {
		if(selectcard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
			switch(AbstractDungeon.gridSelectScreen.selectedCards.get(0).cardID) {
			case "DragClaw":
				c = new DragClaw_s();
				break;
			case "DragShield":
				c = new DragShield_s();
				break;
			case "Dragreder":
				c = new Dragreder();
				break;
			}
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
			c = null;
			AbstractDungeon.gridSelectScreen.selectedCards.clear();
			group.clear();
			SpecialRideBooker.updatecurrentpoint();
			SpecialRideBooker.ryukipoint = SpecialRideBooker.currentpoint;
			AdventCard = true;
			selectcard = false;
		}
	}
}

