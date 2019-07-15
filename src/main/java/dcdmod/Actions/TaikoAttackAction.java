 package dcdmod.Actions;
 
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcdmod.Card.Common.Hibiki_Attack1;
import dcdmod.Card.Common.Hibiki_Attack2;
import dcdmod.Card.Uncommon.Hibiki_Attack3;
 
 public class TaikoAttackAction extends AbstractGameAction
 {
   private AbstractPlayer p;
   CardGroup group = new CardGroup(CardGroup.CardGroupType.CARD_POOL); 
   public TaikoAttackAction() {
     this.p = AbstractDungeon.player;
     this.duration = Settings.ACTION_DUR_FAST;
     this.actionType = AbstractGameAction.ActionType.SPECIAL;
   }
   
   public void update() {
	boolean Done = false;
     if (this.duration == Settings.ACTION_DUR_FAST) {
		   group.addToBottom(new Hibiki_Attack3());
		   group.addToBottom(new Hibiki_Attack2());
		   group.addToBottom(new Hibiki_Attack1());
		   AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张牌", false, false, true, false);
       tickDuration();
       return;
     }
     if(!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
    	 for (AbstractCard c1 : AbstractDungeon.gridSelectScreen.selectedCards) {
    		 for(AbstractCard c2 : p.hand.group){
    			 if(c2.cardID == c1.cardID && !Done) {
    				 c2.freeToPlayOnce = true;
    				 Done = true;
    				 break;
    			 }
    		 }
    		 for(AbstractCard c2 : p.drawPile.group){
    			 if(c2.cardID == c1.cardID && !Done) {
    				 c2.freeToPlayOnce = true;
    				 p.drawPile.moveToHand(c2, p.drawPile);
    				 Done = true;
    				 break;
    			 }
    		 }
    		 for(AbstractCard c2 : p.discardPile.group){
    			 if(c2.cardID == c1.cardID && !Done) {
    				 c2.freeToPlayOnce = true;
    				 p.discardPile.moveToHand(c2, p.discardPile);
    				 Done = true;
    				 break;
    			 }
    		 }
    		 if(!Done) {
    			 AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c1, 1));
    		 }
    	 }
    	 AbstractDungeon.gridSelectScreen.selectedCards.clear();
    	 group.clear();
	}   
     tickDuration();
   }
 }



