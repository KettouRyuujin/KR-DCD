 package dcdmod.Actions;
 
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 
 public class RollAction extends AbstractGameAction
 {
   private AbstractPlayer p;
   CardGroup group = new CardGroup(CardGroup.CardGroupType.CARD_POOL); 
   public RollAction() {
     this.p = AbstractDungeon.player;
     this.duration = Settings.ACTION_DUR_FAST;
     this.actionType = AbstractGameAction.ActionType.SPECIAL;
   }
   
   public void update() {
     if (this.duration == Settings.ACTION_DUR_FAST) {
		   group.group.addAll(p.hand.group);
		   group.group.addAll(p.drawPile.group);
		   group.group.addAll(p.discardPile.group);
		   AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张牌使其耗能降为0", false, false, true, false);
		   AbstractDungeon.overlayMenu.cancelButton.show("取消");
       tickDuration();
       return;
     }
     if(!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
    	 for (AbstractCard c1 : AbstractDungeon.gridSelectScreen.selectedCards) {
    		 if (c1.cost > 0) {
    			 c1.freeToPlayOnce = true;
    		 }
    	 }
    	 AbstractDungeon.gridSelectScreen.selectedCards.clear();
    	 group.clear();
	}   
     tickDuration();
   }
 }



