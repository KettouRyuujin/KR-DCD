 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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

import dcdmod.DCDmod;
import dcdmod.Vfx.Agito_flame;

 
 public class AgitoFlamePower extends AbstractPower
 {
	  public static final String POWER_ID = "AgitoFlamePower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	  int sx = 0;
	  int Lv = 1;
	  boolean remove;
	  
	  
	   public AgitoFlamePower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = -1;
	    this.img = ImageMaster.loadImage("img/powers/AgitoFlamePower.png");
	   updateDescription();
	   loadRegion("AgitoFlamePower");
	   }
	   
	   public void onRemove() {
		   if(this.owner.hasPower("FlameLevelPower")) {
			   AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "FlameLevelPower"));
		   }
	   }
	   
	   public void onUseCard(final AbstractCard card, final UseCardAction action) {
		   if(!this.owner.hasPower("AgitoStormPower") && card.cardID == "Agito_FlameSaber" && !DCDmod.AnimationTrigger) {
		   AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_flame(this.owner.drawX - 200.00f, this.owner.drawY + 250.00f), 0F));
		   } 
	   }
	   
	   public void onAfterCardPlayed(final AbstractCard usedCard) {
		   if(usedCard.type == CardType.SKILL) {
			   if(usedCard.cardID == "Decade_Defend") {sx+=2;}
			   else{sx++;}
			   updateDescription();
		   }
		   if(sx >= 5) {
			   sx -= 5;
			   AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new FlameLevelPower(this.owner,1),1));
			   updateDescription();
		   }
	   }
	   

	  
	   public void updateDescription() {
			 this.description = DESCRIPTIONS[0] + sx + DESCRIPTIONS[1];
	   }
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("AgitoFlamePower");
	       NAME = AgitoFlamePower.powerStrings.NAME;
	       DESCRIPTIONS = AgitoFlamePower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

