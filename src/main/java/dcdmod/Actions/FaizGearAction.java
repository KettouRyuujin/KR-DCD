package dcdmod.Actions;

import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import dcdmod.Card.SelectCard.FaizEdge_s;
import dcdmod.Card.SelectCard.FaizPhone_s;
import dcdmod.Card.SelectCard.FaizPointer_s;
import dcdmod.Card.SelectCard.FaizShot_s;
import dcdmod.Helper.SpecialFaizButton;

public class FaizGearAction {
	static CardGroup group = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
	static boolean FaizGear = true;
	static boolean selectcard = false;
	static boolean GearError = false;
	public static int FaizPoint = 0;
	public static void FaizGear() {
		if(FaizGear) {
			group.addToBottom(new FaizPhone_s());
			group.addToBottom(new FaizPointer_s());
			group.addToBottom(new FaizShot_s());
			group.addToBottom(new FaizEdge_s());
			AbstractDungeon.gridSelectScreen.open(group, 1, "选择1种插件恢复", false, false, true, false);
			FaizGear = false;
			selectcard = true;
		}
	}
	
	public static void update() {
		if(selectcard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
			switch(AbstractDungeon.gridSelectScreen.selectedCards.get(0).cardID) {
			case "FaizPhone_s":
				if(FaizPoint>=1&&!EnterButtonAction.FaizPhone) {
					FaizPoint-=1;
					EnterButtonAction.FaizPhone = true;
				}
				else {GearError = true;}
				break;
			case "FaizPointer_s":
				if(FaizPoint>=3&&!EnterButtonAction.FaizPointer) {
					FaizPoint-=3;
					EnterButtonAction.FaizPointer = true;
				}
				else {GearError = true;}
				break;
			case "FaizShot_s":
				if(FaizPoint>=3&&!EnterButtonAction.FaizShot) {
					FaizPoint-=3;
					EnterButtonAction.FaizShot = true;
				}
				else {GearError = true;}
				break;
			case "FaizEdge_s":
				if(FaizPoint>=3&&!EnterButtonAction.FaizEdge) {
					FaizPoint-=3;
					EnterButtonAction.FaizEdge = true;
				}
				else {GearError = true;}
				break;
			}
			if(GearError) {
					AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, " #rError! ", true));
			}
			AbstractDungeon.gridSelectScreen.selectedCards.clear();
			group.clear();
			FaizGear = true;
			selectcard = false;
			GearError = false;
			SpecialFaizButton.haskamenpower = true;
		}
	}
	
}

