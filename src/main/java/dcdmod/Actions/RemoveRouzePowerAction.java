/*    */ package dcdmod.Actions;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class RemoveRouzePowerAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   public RemoveRouzePowerAction(AbstractCreature target, AbstractCreature source)
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
       if (this.target.hasPower("BladeSlashPower"))
       {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "BladeSlashPower"));
       }
       if (this.target.hasPower("BladeBeatPower"))
       {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "BladeBeatPower"));
       }
       if (this.target.hasPower("BladeKickPower"))
       {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "BladeKickPower"));
       }
       else {
         this.isDone = true;
       }
     }
     
     tickDuration();
   }
 }


//在卡牌效果中完全移除状态
//引用代码：AbstractDungeon.actionManager.addToBottom(new RemoveRouzePowerAction(p, p));