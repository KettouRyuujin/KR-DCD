/*    */ package dcdmod.Power;
/*    */ import com.megacrit.cardcrawl.actions.common.SetMoveAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
		 import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
		 import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class XuanyunPower extends com.megacrit.cardcrawl.powers.AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "XuanyunPower";
           public static final String NAME = "眩晕";
           public static final String DESCRIPTIONS = "敌人将无法行动";
/*    */   
/*    */   private byte moveByte;
/*    */   private AbstractMonster.Intent moveIntent;
/*    */   
/*    */   public XuanyunPower(AbstractCreature owner)
/*    */   {
/* 29 */     this.name = NAME;
/* 30 */     this.ID = POWER_ID;
/* 31 */     this.owner = owner;
/* 32 */     this.amount = -1;
/* 33 */     updateDescription();
/*    */     
/* 35 */     this.type = AbstractPower.PowerType.DEBUFF;
/* 36 */     this.img = ImageMaster.loadImage("img/powers/stun.png");
/*    */     
/* 38 */     this.moveByte = 1;
/* 39 */     this.moveIntent = AbstractMonster.Intent.UNKNOWN;
/*    */     
/* 41 */     if ((owner instanceof AbstractMonster)) {
/* 42 */       AbstractMonster m = (AbstractMonster)owner;
/*    */       
/* 44 */       this.moveByte = Byte.valueOf(m.nextMove).byteValue();
/* 45 */       this.moveIntent = AbstractMonster.Intent.valueOf(m.intent.name());
/*    */       
/* 47 */       m.setMove((byte)Byte.MAX_VALUE, AbstractMonster.Intent.STUN);
/* 48 */       m.createIntent();
/* 49 */       AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, (byte)Byte.MAX_VALUE, AbstractMonster.Intent.STUN));
/*    */     }
/*    */   }
/*    */   
/*    */   public void updateDescription()
/*    */   {
/* 55 */     this.description = DESCRIPTIONS;
/*    */   }
/*    */   
/*    */   public void atEndOfRound()
/*    */   {
/* 60 */     AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
/*    */     
/* 62 */     if ((this.owner instanceof AbstractMonster)) {
/* 63 */       AbstractMonster m = (AbstractMonster)this.owner;
/*    */       
/* 65 */       m.setMove(this.moveByte, this.moveIntent);
/* 66 */       m.createIntent();
/* 67 */       AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, this.moveByte, this.moveIntent));
/*    */     }
/*    */   }
/*    */   

/*    */ }


/* Location:              C:\Users\V5\Desktop\Thunder.jar!\thunder\Power\mabiPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */