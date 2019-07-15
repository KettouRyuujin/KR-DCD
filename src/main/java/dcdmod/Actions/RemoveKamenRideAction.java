/*    */ package dcdmod.Actions;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class RemoveKamenRideAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   public RemoveKamenRideAction(AbstractCreature target, AbstractCreature source)
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
       if (this.target.hasPower("KamenRideKuugaPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideKuugaPower"));
       }
       if (this.target.hasPower("KamenRideAgitoPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideAgitoPower"));
       }
       if (this.target.hasPower("KamenRideRyukiPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideRyukiPower"));
       }
       if (this.target.hasPower("KamenRideFaizPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideFaizPower"));
       }
       if (this.target.hasPower("KamenRideBladePower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideBladePower"));
       }
       if (this.target.hasPower("KamenRideHibikiPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideHibikiPower"));
       }
       if (this.target.hasPower("KamenRideKabutoPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideKabutoPower"));
       }
       if (this.target.hasPower("KamenRideDenOPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideDenOPower"));
       }
       if (this.target.hasPower("KamenRideKivaPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideKivaPower"));
       }
       else {
         this.isDone = true;
       }
     }
     
     tickDuration();
   }
 }


//在卡牌效果中完全移除状态
//引用代码：AbstractDungeon.actionManager.addToBottom(new RemoveKamenRideAction(p, p));