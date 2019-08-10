 package dcdmod.Actions;
 
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

 public class RisingMightyAction extends AbstractGameAction
 {
   private AbstractPlayer p;
   private CardGroup group = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
   public RisingMightyAction() {
     this.p = AbstractDungeon.player;
     this.duration = Settings.ACTION_DUR_FAST;
     this.actionType = ActionType.SPECIAL;
   }
   
   public void update() {
     if (this.duration == Settings.ACTION_DUR_FAST) {
         for (AbstractCard c1 : p.hand.group) {
             if(c1.type == AbstractCard.CardType.ATTACK){
                 group.group.add(c1);
             }
         }
         for (AbstractCard c1 : p.drawPile.group) {
             if(c1.type == AbstractCard.CardType.ATTACK){
                 group.group.add(c1);
             }
         }
         for (AbstractCard c1 : p.discardPile.group) {
             if(c1.type == AbstractCard.CardType.ATTACK){
                 group.group.add(c1);
             }
         }
         AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张牌使其耗能降为0并加入手牌", false, false, true, false);
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
    	 boolean isDone = false;
    	 for(AbstractCard c2 : p.drawPile.group){
    	     if(c2 == AbstractDungeon.gridSelectScreen.selectedCards.get(0)){
                 p.drawPile.group.remove(c2);
                 p.hand.group.add(c2);
                 isDone = true;
                 break;
             }
         }
    	 if(!isDone){
             for(AbstractCard c2 : p.discardPile.group){
                 if(c2 == AbstractDungeon.gridSelectScreen.selectedCards.get(0)){
                     p.discardPile.group.remove(c2);
                     p.hand.group.add(c2);
                     break;
                 }
             }
         }
    	 AbstractDungeon.gridSelectScreen.selectedCards.clear();
    	 group.clear();
	}   
     tickDuration();
   }
 }



