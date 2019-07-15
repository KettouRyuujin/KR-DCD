/*    */ package dcdmod.Actions;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class RemoveFormRideAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   public RemoveFormRideAction(AbstractCreature target, AbstractCreature source)
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
       if (this.target.hasPower("KuugaDragonPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KuugaDragonPower"));
       }
       if (this.target.hasPower("KuugaTitanPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KuugaTitanPower"));
       }
       if (this.target.hasPower("KuugaPegasusPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KuugaPegasusPower"));
       }
       if (this.target.hasPower("RisingDragonPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "RisingDragonPower"));
       }
       if (this.target.hasPower("RisingTitanPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "RisingTitanPower"));
       }
       if (this.target.hasPower("RisingPegasusPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "RisingPegasusPower"));
       }
       if (this.target.hasPower("RisingMightyPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "RisingMightyPower"));
       }
       if (this.target.hasPower("AgitoStormPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "AgitoStormPower"));
       }
       if (this.target.hasPower("AgitoFlamePower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "AgitoFlamePower"));
       }
       if (this.target.hasPower("FaizAxelPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "FaizAxelPower"));
       }
       if (this.target.hasPower("BladeJackPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "BladeJackPower"));
       }
       if (this.target.hasPower("HibikiKurenaiPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "HibikiKurenaiPower"));
       }
       if (this.target.hasPower("KabutoMaskedPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KabutoMaskedPower"));
       }
       if (this.target.hasPower("DenORodPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "DenORodPower"));
       }
       if (this.target.hasPower("DenOAxPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "DenOAxPower"));
       }
       if (this.target.hasPower("DenOGunPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "DenOGunPower"));
       }
       if (this.target.hasPower("DenOWingPower"))
       {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "DenOWingPower"));
       }
       else {
         this.isDone = true;
       }
     }
     
     tickDuration();
   }
 }


//在卡牌效果中完全移除状态
//引用代码：AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(p, p));