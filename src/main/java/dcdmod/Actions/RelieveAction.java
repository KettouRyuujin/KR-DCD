 package dcdmod.Actions;
 
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 
 public class RelieveAction extends AbstractGameAction
 {
   private AbstractPlayer p;
   CardGroup group = new CardGroup(CardGroup.CardGroupType.CARD_POOL); 
   public RelieveAction() {
     this.p = AbstractDungeon.player;
     this.duration = Settings.ACTION_DUR_FAST;
     this.actionType = AbstractGameAction.ActionType.SPECIAL;
   }
   
   public void update() {
     if (this.duration == Settings.ACTION_DUR_FAST) {
		   group.group.addAll(p.hand.group);
		   group.group.addAll(p.drawPile.group);
		   group.group.addAll(p.discardPile.group);
		   AbstractDungeon.gridSelectScreen.open(group, 3, "选择3张牌使其消耗", false, false, true, false);
		   AbstractDungeon.overlayMenu.cancelButton.show("确认");
       tickDuration();
       return;
     }

     if(!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
    	 for (AbstractCard c1 : AbstractDungeon.gridSelectScreen.selectedCards) {
    		 for(AbstractCard c2 : p.hand.group){
    			 if(c2 == c1) {
    				 p.hand.moveToExhaustPile(c2);
    				 break;
    			 }
    		 }
    		 for(AbstractCard c2 : p.drawPile.group){
    			 if(c2 == c1) {
    				 p.drawPile.moveToExhaustPile(c2);
    				 break;
    			 }
    		 }
    		 for(AbstractCard c2 : p.discardPile.group){
    			 if(c2 == c1) {
    				 p.discardPile.moveToExhaustPile(c2);
    				 break;
    			 }
    		 }
    	 }
    	 AbstractDungeon.gridSelectScreen.selectedCards.clear();
    	 group.clear();
    	 AbstractDungeon.overlayMenu.cancelButton.hide();
	}   
     tickDuration();
   }
 }



