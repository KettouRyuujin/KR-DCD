 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

 
 public class BladeThunderPower extends AbstractPower
 {
	  public static final String POWER_ID = "BladeThunderPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	   public BladeThunderPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = amt;
	    this.img = ImageMaster.loadImage("img/powers/BladeThunderPower.png");
	    
	   updateDescription();
	   loadRegion("BladeThunderPower");
	   }
	   
	   public void onAfterUseCard(final AbstractCard card, final UseCardAction action) {
		   if(card.cardID != "Blade_Thunder") {
			   AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "BladeThunderPower"));
		   }
	   }
	   
	   public void updateDescription() {
			 this.description = DESCRIPTIONS[0];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("BladeThunderPower");
	       NAME = BladeThunderPower.powerStrings.NAME;
	       DESCRIPTIONS = BladeThunderPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

