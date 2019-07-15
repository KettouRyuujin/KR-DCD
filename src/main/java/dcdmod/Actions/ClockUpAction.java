 package dcdmod.Actions;
 
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 
 public class ClockUpAction extends AbstractGameAction
 {
   private AbstractPlayer p;
   private AbstractCard c; 
   CardGroup group = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
   public ClockUpAction(AbstractCard c) {
	 this.c = c;
     this.p = AbstractDungeon.player;
     this.duration = Settings.ACTION_DUR_FAST;
     this.actionType = AbstractGameAction.ActionType.SPECIAL;
     
   }
   
   public void update() {   	
	     if (this.duration == Settings.ACTION_DUR_FAST) {
	  		group.group.addAll(p.drawPile.group);
	  		for(AbstractCard c1 : p.drawPile.group) {
	  			if(c1==c) {
	  				group.removeCard(c1);
	  				break;
	  			}
	  		}
	  		AbstractDungeon.gridSelectScreen.open(group, 3, "选择3张牌使其耗能降为0并加入手牌", false, false, true, false);
	  		AbstractDungeon.overlayMenu.cancelButton.show("取消");
	  		tickDuration();
	  		return;
	      }
	     
	      if(!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
	     	 for (AbstractCard c1 : AbstractDungeon.gridSelectScreen.selectedCards) {
	     		 if (c1.cost > 0) {
	     			 c1.freeToPlayOnce = true;
	     		 }
     			 p.hand.addToHand(c1);
     			 p.drawPile.removeCard(c1);
	     	 }
	     	 AbstractDungeon.gridSelectScreen.selectedCards.clear();
	     	 group.clear();
	 	}   
	      tickDuration();
	}
 }



