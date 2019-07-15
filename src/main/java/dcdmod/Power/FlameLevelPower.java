 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dcdmod.Card.Special.AgitoPower;
import dcdmod.Card.Special.FlameSpecialCard;
import dcdmod.Characters.Decade;

 
 public class FlameLevelPower extends AbstractPower
 {
	  public static final String POWER_ID = "FlameLevelPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	  boolean AgitoPower = true;
	  int ax = 0;
	  int Lv = 1;
	  
	   public FlameLevelPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = amt;
	    this.img = ImageMaster.loadImage("img/powers/AgitoLevelPower.png");
	   updateDescription();
	   loadRegion("FlameLevelPower");
	   }
	   
	   public void stackPower(int stackAmount) {
		   if (this.amount == 0) {
			   AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "FlameLevelPower"));
		   }
		   this.fontScale = 8.0F;
		   this.amount += stackAmount;
		   if (this.amount >= 10) {
			   this.amount = 10;
			   AgitoPower = false;
		   }
	   }
	   
	   public int onAttacked(final DamageInfo info, final int damageAmount) {
		   if(this.amount >= 7) {
			   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner,this.owner, new SpecialFlamePower(this.owner,1), 1));
		   }
		return damageAmount;
	   }
	   
	   public void onApplyPower(final AbstractPower power, final AbstractCreature target, final AbstractCreature source) {
		   if(power.ID == "FlameLevelPower") {
			   Lv = this.amount + 1;
			   if(Lv == 2) {
				   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 1), 1));
				   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new FlameSpecialCard(), 1));
			   }
			   if(Lv == 3) {
				   
			   }
			   if(Lv == 4) {
				   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 2), 2));
				   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, 1), 1));
			   }
			   if(Lv == 5) {
				   
			   }
			   if(Lv == 6) {
				   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 1), 1));
			   }
			   if(Lv == 7) {
				   
			   }
			   if(Lv == 8) {
				   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 2), 2));
				   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, 1), 1));
			   }
			   if(Lv == 9) {
				   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new AgitoPower(), 1));
			   }
			   if(Lv >= 10) {
				   if(AgitoPower) {
	    				
					   AgitoPower = false;
				   }
			   }
		   }
		   if(this.amount >= 5 && power.ID == "AgitoStormPower"){
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(17);
		   }
	   }
	  
	   public void updateDescription() {
		   if(this.amount == 1) {
			   this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[2];
		   }
		   if(this.amount == 2) {
			   this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[3];
		   }
		   if(this.amount == 3) {
			   this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[4];
		   }
		   if(this.amount == 4) {
			   this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[5];
		   }
		   if(this.amount == 5) {
			   this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[6];
		   }
		   if(this.amount == 6) {
			   this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[7];
		   }
		   if(this.amount == 7) {
			   this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[8];
		   }
		   if(this.amount == 8) {
			   this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[9];
		   }
		   if(this.amount == 9) {
			   this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[10];
		   }
		   if(this.amount >= 10) {
			   this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[11];
		   }

	   }
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("FlameLevelPower");
	       NAME = FlameLevelPower.powerStrings.NAME;
	       DESCRIPTIONS = FlameLevelPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

