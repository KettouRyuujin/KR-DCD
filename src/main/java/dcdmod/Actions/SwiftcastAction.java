 package dcdmod.Actions;
 
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 
 public class SwiftcastAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
 {
   private AbstractPlayer p;
   
   public SwiftcastAction() {
     this.p = AbstractDungeon.player;
     this.duration = Settings.ACTION_DUR_FAST;
     this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.CARD_MANIPULATION;
   }
   
public void update() {
AbstractCard c;
     if (this.duration == Settings.ACTION_DUR_FAST) {
       if (this.p.hand.isEmpty()) {
         this.isDone = true;
         return; }
       if (this.p.hand.size() == 1) {
         c = this.p.hand.getTopCard();
         if (c.cost > 0) {
           c.freeToPlayOnce = true;
         }
         this.p.hand.addToTop(c);
         AbstractDungeon.player.hand.refreshHandLayout();
         this.isDone = true;
         return;
       }
       AbstractDungeon.handCardSelectScreen.open("让其耗能变为0", 1, true,true);
       tickDuration();
       return;
     }
     
 
 
 
     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
       for (AbstractCard c1 : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
         if (c1.cost > 0) {
           c1.freeToPlayOnce = true;
         }
         this.p.hand.addToTop(c1);
       }
       AbstractDungeon.player.hand.refreshHandLayout();
       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
     }
     
     tickDuration();
   }
 }



