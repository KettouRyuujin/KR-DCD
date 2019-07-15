 package dcdmod.Power;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

 
 public class BladeSlashPower extends AbstractPower
 {
	  public static final String POWER_ID = "BladeSlashPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	   public BladeSlashPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = amt;
	    this.img = ImageMaster.loadImage("img/powers/BladeSlashPower.png");
	    
	   updateDescription();
	   loadRegion("BladeSlashPower");
	   }
	   
	   public void updateDescription() {
			 this.description = DESCRIPTIONS[0] + this.amount*2 + DESCRIPTIONS[1];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("BladeSlashPower");
	       NAME = BladeSlashPower.powerStrings.NAME;
	       DESCRIPTIONS = BladeSlashPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

