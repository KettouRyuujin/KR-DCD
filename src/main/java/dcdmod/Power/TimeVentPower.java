 package dcdmod.Power;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dcdmod.Patches.ModBaseClassForSLExample;

 
 public class TimeVentPower extends AbstractPower
 {
	  public static final String POWER_ID = "TimeVentPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	   public TimeVentPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = amt;
	    this.img = ImageMaster.loadImage("img/powers/TimeVentPower.png");
	    
	   updateDescription();
	   loadRegion("TimeVentPower");
	   }
	   
	   
	   public void onVictory() {
		   ModBaseClassForSLExample.timevent = false;
		   ModBaseClassForSLExample.TimeVentUpgraded = true;
	   }

	  
	   public void updateDescription() {
			 this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("TimeVentPower");
	       NAME = TimeVentPower.powerStrings.NAME;
	       DESCRIPTIONS = TimeVentPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

