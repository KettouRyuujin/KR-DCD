 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

 
 public class BladeJackPower extends AbstractPower
 {
	  public static final String POWER_ID = "BladeJackPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	  int power;
	  int x = 0;
	   
	   public BladeJackPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = -1;
	    this.img = ImageMaster.loadImage("img/powers/BladeJackPower.png");
	    
	   updateDescription();
	   loadRegion("BladeJackPower");
	 }

	   public void atEndOfTurn(boolean isPlayer){
		   if(this.owner.currentBlock<=0) {
			   AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, 8));
		   }
	   }

	  
	   public void updateDescription() {
			 this.description = DESCRIPTIONS[0];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("BladeJackPower");
	       NAME = BladeJackPower.powerStrings.NAME;
	       DESCRIPTIONS = BladeJackPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

