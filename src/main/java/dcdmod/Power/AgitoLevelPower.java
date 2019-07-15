 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dcdmod.Card.Special.AgitoPower;

 
 public class AgitoLevelPower extends AbstractPower
 {
	  public static final String POWER_ID = "AgitoLevelPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	  boolean AgitoPower = true;
	  int Lv = 1;
	   
	   public AgitoLevelPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = amt;
	    this.img = ImageMaster.loadImage("img/powers/AgitoLevelPower.png");
	   updateDescription();
	   loadRegion("AgitoLevelPower");
	   }
	   
	   public void stackPower(int stackAmount) {
		   if (this.amount == 0) {
			   AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "AgitoLevelPower"));
		   }
		   this.fontScale = 8.0F;
		   this.amount += stackAmount;
		   if (this.amount >= 3) {
			   this.amount = 3;
			   AgitoPower = false;
		   }
	   }
	  
	   public void onApplyPower(final AbstractPower power, final AbstractCreature target, final AbstractCreature source) {
		   if(power.ID == "AgitoLevelPower") {
			   Lv = this.amount +1;
			   if(Lv == 2) {
				   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 1), 1));
				   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, 1), 1));
			   }
			   if(Lv >= 3) {
				   if(AgitoPower) {
					   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new AgitoPower(), 1));
					   AgitoPower = false;
				   }
			   }
		   }
	   }
	   
	   public void updateDescription() {
		   if(this.amount == 1) {
			   this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[2];
		   }
		   if(this.amount == 2) {
			   this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[3];
		   }
		   if(this.amount >= 3) {
			   this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[4];
		   }
	   }
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("AgitoLevelPower");
	       NAME = AgitoLevelPower.powerStrings.NAME;
	       DESCRIPTIONS = AgitoLevelPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

