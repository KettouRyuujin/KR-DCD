 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
 
 public class HibikiKurenaiSpecialPower extends AbstractPower
 {
	  public static final String POWER_ID = "HibikiKurenaiSpecialPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	   public HibikiKurenaiSpecialPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = amt;
	    this.img = ImageMaster.loadImage("img/powers/HibikiKurenaiSpecialPower.png");
	   updateDescription();
	   loadRegion("HibikiKurenaiSpecialPower");
	   }
	  
	   public void onRemove() {
		   if(this.owner.hasPower("HibikiKurenaiPower")) {
			   AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "HibikiKurenaiPower"));
		   }
	   }
	   
	   public void atStartOfTurn() {
		   AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "HibikiKurenaiSpecialPower", 1));
	   }
	   
	   public void onUseCard(final AbstractCard card, final UseCardAction action) {
		   if(card.type == CardType.ATTACK) {
			   AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "HibikiKurenaiSpecialPower", 1));
		   }
	   }
	   
	   
	   public void updateDescription() {
			 this.description = DESCRIPTIONS[0];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("HibikiKurenaiSpecialPower");
	       NAME = HibikiKurenaiSpecialPower.powerStrings.NAME;
	       DESCRIPTIONS = HibikiKurenaiSpecialPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

