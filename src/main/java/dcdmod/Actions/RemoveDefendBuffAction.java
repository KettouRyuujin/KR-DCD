/*    */ package dcdmod.Actions;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class RemoveDefendBuffAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   public RemoveDefendBuffAction(AbstractCreature target, AbstractCreature source)
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
       if (this.target.hasPower("DecadeBlockPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "DecadeBlockPower"));
       }
       if (this.target.hasPower("DashPower1"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "DashPower1"));
       }
       if (this.target.hasPower("DashPower2"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "DashPower2"));
       }
       if (this.target.hasPower("Buffer"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "Buffer"));
       }
       if (this.target.hasPower("PegasusDefendPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "PegasusDefendPower"));
       }
       if (this.target.hasPower("IntangiblePlayerPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "IntangiblePlayerPower"));
       }
       else {
         this.isDone = true;
       }
     }
     
     tickDuration();
   }
 }


//在卡牌效果中完全移除状态
//引用代码：AbstractDungeon.actionManager.addToBottom(new RemoveDefendBuffAction(p, p));