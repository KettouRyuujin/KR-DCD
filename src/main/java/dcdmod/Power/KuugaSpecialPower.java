 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

 
 public class KuugaSpecialPower extends AbstractPower
 {
	  public static final String POWER_ID = "KuugaSpecialPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	  
	   public KuugaSpecialPower(AbstractCreature owner, int amt) {   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = amt;
	    this.img = ImageMaster.loadImage("img/powers/KuugaSpecialPower.png");
	    updateDescription();
	    loadRegion("KuugaSpecialPower");
	    type = AbstractPower.PowerType.DEBUFF;
	   }
	   
	   public void stackPower(int stackAmount) {
		    updateDescription();
		    if (this.amount == 0) {
		    	AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "KuugaSpecialPower"));
		    }
		    this.fontScale = 8.0F;
		    this.amount += stackAmount;
		    if(AbstractDungeon.player.hasPower("RisingDragonPower")) {

			}
		    else if(AbstractDungeon.player.hasPower("KuugaDragonPower")) {
		    	if(this.amount > 15) {
		    		this.amount = 15;
		    	}
		    }
		    else { 
		    	if (this.amount >= 3) {
		    		this.amount = 3;
		    	}
		    }

	   }
	   
	   public float atDamageReceive(float damage, DamageInfo.DamageType type) {
		    updateDescription();
		   if (type == DamageInfo.DamageType.NORMAL) {
			   if(this.amount == 1) {
				   return damage * 1.1F;
			   }
			   else if(this.amount == 2) {
				   return damage * 1.2F; 
			   }
			   else {
				   return damage * 1.3F; 
			   }
		   }
		   return damage;   
	   }
	   
	   public void onApplyPower(final AbstractPower power, final AbstractCreature target, final AbstractCreature source) {
		   updateDescription();
	   }
	  
	   public void updateDescription() {
		   
		   if(AbstractDungeon.player.hasPower("RisingDragonPower")) {
			   if(this.amount == 1) {
				   this.description = DESCRIPTIONS[0]+ " #b10% "; 
			   }
			   else if(this.amount == 2) {
				   this.description = DESCRIPTIONS[0]+ " #b20% "; 
			   }
			   else {
				   this.description = DESCRIPTIONS[0]+ " #b30% "; 
			   }
		   }
		   else if(AbstractDungeon.player.hasPower("KuugaDragonPower")) {
			   if(this.amount == 1) {
				   this.description = DESCRIPTIONS[0]+ " #b10% " + DESCRIPTIONS[1]; 
			   }
			   else if(this.amount == 2) {
				   this.description = DESCRIPTIONS[0]+ " #b20% " + DESCRIPTIONS[1]; 
			   }
			   else {
				   this.description = DESCRIPTIONS[0]+ " #b30% " + DESCRIPTIONS[1]; 
			   }
		   }
		   else {
			   if(this.amount == 1) {
				   this.description = DESCRIPTIONS[0]+ " #b10% " + DESCRIPTIONS[2]; 
			   }
			   else if(this.amount == 2) {
				   this.description = DESCRIPTIONS[0]+ " #b20% " + DESCRIPTIONS[2]; 
			   }
			   else {
				   this.description = DESCRIPTIONS[0]+ " #b30% " + DESCRIPTIONS[2]; 
			   }
		   }
			 
	   }
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("KuugaSpecialPower");
	       NAME = KuugaSpecialPower.powerStrings.NAME;
	       DESCRIPTIONS = KuugaSpecialPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

