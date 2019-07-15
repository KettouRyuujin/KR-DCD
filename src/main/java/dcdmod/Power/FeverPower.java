 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import dcdmod.Patches.HibikiTaikoKeyEvent;

 
 public class FeverPower extends AbstractPower
 {
	  public static final String POWER_ID = "FeverPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	   public FeverPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = -1;
	    this.img = ImageMaster.loadImage("img/powers/Fever.png");
	    
	   updateDescription();
	   loadRegion("FeverPower");
	   }
	  
	   public void onUseCard(final AbstractCard card, final UseCardAction action) {
		   if(card.cardID != "Hibiki_Ongekibou" && card.cardID != "Hibiki_Attack1" && card.cardID != "Hibiki_Attack2" && card.cardID != "Hibiki_Attack3") {
			   HibikiTaikoKeyEvent.FeverOut(false);
		   }
	   }
	   
	   public void updateDescription() {
			 this.description = DESCRIPTIONS[0];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("FeverPower");
	       NAME = FeverPower.powerStrings.NAME;
	       DESCRIPTIONS = FeverPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

