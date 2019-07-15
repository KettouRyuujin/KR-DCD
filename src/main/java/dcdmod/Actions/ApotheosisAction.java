/*    */ package dcdmod.Actions;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class ApotheosisAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   public ApotheosisAction()
/*    */   {
/* 14 */     this.duration = Settings.ACTION_DUR_MED;
/* 15 */     this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.WAIT;
/*    */   }
/*    */   
/*    */   public void update()
/*    */   {
/* 20 */     if (this.duration == Settings.ACTION_DUR_MED) {
	       
	    	   
/* 21 */       AbstractPlayer p = com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
/*    */       

/* 23 */       upgradeAllCardsInGroup(p.hand);
/* 24 */       //upgradeAllCardsInGroup(p.drawPile);
/* 25 */       //upgradeAllCardsInGroup(p.discardPile);
/* 26 */       //upgradeAllCardsInGroup(p.exhaustPile);
/*    */       
               

/* 28 */       this.isDone = true;
/*    */      
	    	   
             
            }
/*    */   }
/*    */   
/*    */   private void upgradeAllCardsInGroup(CardGroup cardGroup) {
/* 33 */     for (AbstractCard c : cardGroup.group) {
/* 34 */       if (c.canUpgrade()) {
/* 35 */         if (cardGroup.type == CardGroup.CardGroupType.HAND) {
/* 36 */           c.superFlash();
/*    */         }
/* 38 */         c.upgrade();
/* 39 */         c.applyPowers();
/*    */       }
/*    */     }
/*    */   }
/*    */ }
