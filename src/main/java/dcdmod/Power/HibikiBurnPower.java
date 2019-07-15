package dcdmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class HibikiBurnPower extends AbstractPower
 {
  public static final String POWER_ID = "HibikiBurnPower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  private AbstractCreature source;
   
   public HibikiBurnPower(AbstractCreature owner, int amt, AbstractCreature source) {
	   this.name = NAME;
	   this.ID = POWER_ID;
	   this.owner = owner;
	   this.amount = amt;
	   this.img = ImageMaster.loadImage("img/powers/fire.png");
	   this.source = source;
	   this.type = AbstractPower.PowerType.DEBUFF;
	   updateDescription();
	   loadRegion("HibikiBurnPower");
   }

   public void updateDescription() {
	   if (this.owner.isPlayer) {
		      this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
		    } else {
		     this.description = (DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3]);
		   }
   }

   public void atStartOfTurn() {
	   if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
		   if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
			   flashWithoutSound();
			   AbstractDungeon.actionManager.addToBottom(new PoisonLoseHpAction(this.owner, this.source, this.amount, AttackEffect.FIRE));
		   }   
	   }
   }
   
   public void atEndOfRound() {
	   AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "HibikiBurnPower", 1));
   }
   
   public float atDamageGive(float damage, DamageType type) {
	   if (type == DamageType.NORMAL) {
		  return damage * 0.5F;
	   }
		return damage;
   }
   
   static {
	     powerStrings = CardCrawlGame.languagePack.getPowerStrings("HibikiBurnPower");
	     NAME = HibikiBurnPower.powerStrings.NAME;
	     DESCRIPTIONS = HibikiBurnPower.powerStrings.DESCRIPTIONS;
	 }

  }
 
 