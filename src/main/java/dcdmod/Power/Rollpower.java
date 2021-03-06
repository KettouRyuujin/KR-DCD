package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import dcdmod.Actions.RollAction;

public class Rollpower extends AbstractPower
 {
  public static final String POWER_ID = "Rollpower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;

  
   public Rollpower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amt;
    this.img = ImageMaster.loadImage("img/powers/Track.png");
   updateDescription();
   loadRegion("Rollpower");
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];
   }
   
   public void onAfterUseCard(final AbstractCard card, final UseCardAction action) {
	   	
	   if(this.amount>=3) {
		   AbstractDungeon.actionManager.addToBottom(new RollAction());
		   AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Rollpower", this.amount));
	   }
	   
   }
 
   public void atStartOfTurn() { 
		   if (this.amount == 0) {
			   AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Rollpower"));
		   } 
		   else {
			   AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Rollpower", this.amount));
		   }
   }
 
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Rollpower");
       NAME = Rollpower.powerStrings.NAME;
       DESCRIPTIONS = Rollpower.powerStrings.DESCRIPTIONS;
   }
   
 }