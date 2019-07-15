 package dcdmod.Actions;
 
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import dcdmod.Card.Uncommon.Hibiki_Attack3;
 
 public class GetHibikiAttackCardAciton extends AbstractGameAction
 {
   private AbstractPlayer p;
   
   public GetHibikiAttackCardAciton() {
     this.p = AbstractDungeon.player;
     this.duration = Settings.ACTION_DUR_FAST;
     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
   }
   
public void update() {
	boolean Done = false;
     if (this.duration == Settings.ACTION_DUR_FAST) {
    	 for(AbstractCard c : p.hand.group) {
    		 if(c.cardID == "Hibiki_Attack3" && !Done) {
    			 Done = true;
    			 break;
    		 }
    	 }
    	 for (AbstractCard c : p.drawPile.group) {
    		 if(c.cardID == "Hibiki_Attack3" && !Done) {
    			 Done = true;
    			 c.freeToPlayOnce = true;
    			 p.drawPile.moveToHand(c, p.drawPile);
    			 break;
    		 }
    	 }
    	 for (AbstractCard c : p.discardPile.group) {
    		 if(c.cardID == "Hibiki_Attack3" && !Done) {
    			 Done = true;
    			 c.freeToPlayOnce = true;
    			 p.discardPile.moveToHand(c, p.discardPile);
    			 break;
    		 }
    	 }
    	 if(!Done) {
    		 AbstractCard c = new Hibiki_Attack3();
    		 AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
    	 }
    	 this.isDone = true;
     }
     
   }
 }



