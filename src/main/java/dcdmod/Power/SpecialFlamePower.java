package dcdmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import dcdmod.Vfx.Agito_flame;
import dcdmod.Vfx.Agito_trinity;

public class SpecialFlamePower extends AbstractPower
 {
  public static final String POWER_ID = "SpecialFlamePower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;

  
   public SpecialFlamePower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amt;
    this.img = ImageMaster.loadImage("img/powers/Mark.png");
   updateDescription();
   loadRegion("SpecialFlamePower");
 }

  
   public void stackPower(int stackAmount) {
	   if (this.amount == 0) {
		   AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "SpecialFlamePower"));
	   }
	   this.fontScale = 8.0F;
	   this.amount += stackAmount;
   }
   
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }
   
   public int onAttacked(final DamageInfo info, final int damageAmount) {
	   int x = 0;
	   if(this.owner.hasPower("Strength") && info.owner != this.owner && info.owner != null) {
		   x = this.owner.getPower("Strength").amount;
		   if(x>0) {
			   if(this.owner.hasPower("AgitoFlamePower")&&!this.owner.hasPower("AgitoStormPower")) {
				   AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_flame(this.owner.drawX - 200.00f, this.owner.drawY + 250.00f), 0F));
			   }
			   else if(this.owner.hasPower("AgitoFlamePower")&&this.owner.hasPower("AgitoStormPower")) {
				   AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_trinity(this.owner.drawX - 200.00f, this.owner.drawY + 250.00f), 0F));
			   }
			   AbstractDungeon.actionManager.addToBottom(new DamageAction(info.owner,new DamageInfo(this.owner, x * this.amount,  DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		   } 
	   }
	   return damageAmount;
   }
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("SpecialFlamePower");
       NAME = SpecialFlamePower.powerStrings.NAME;
       DESCRIPTIONS = SpecialFlamePower.powerStrings.DESCRIPTIONS;
   }
   
 }