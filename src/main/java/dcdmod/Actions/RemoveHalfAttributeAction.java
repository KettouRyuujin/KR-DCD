/*    */ package dcdmod.Actions;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class RemoveHalfAttributeAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   public RemoveHalfAttributeAction(AbstractCreature target, AbstractCreature source)
/*    */   {
/* 13 */     this.target = target;
/* 14 */     this.source = source;
/* 15 */     this.actionType = ActionType.WAIT;
/* 16 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */   
/*    */   public void update()
/*    */   {
/* 21 */     if (this.duration == Settings.ACTION_DUR_FAST) {
       if (this.target.hasPower("Strength")&&this.target.getPower("Strength").amount>0)
       {
    	    int x;
    	    if(this.target.getPower("Strength").amount == 1) {x = 1;}
    	    else {x = (int) Math.ceil(this.target.getPower("Strength").amount/2);}
       		AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.target, this.target, "Strength", x));
       }
       if (this.target.hasPower("Dexterity")&&this.target.getPower("Dexterity").amount>0)
       {
    	   	int x;
    	   	if(this.target.getPower("Dexterity").amount == 1) {x = 1;}
    	   	else{x = (int) Math.ceil(this.target.getPower("Dexterity").amount/2);}
       		AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.target, this.target, "Dexterity", x));
       }
       else {
         this.isDone = true;
       }
     }
     
     tickDuration();
   }
 }


//在卡牌效果中完全移除状态
//引用代码：AbstractDungeon.actionManager.addToBottom(new RemoveHalfAttributeAction(p, p));