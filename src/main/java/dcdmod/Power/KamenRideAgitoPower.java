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
import dcdmod.Actions.RemoveFormRideAction;
import dcdmod.Actions.ReturnRandomNumberAction;
import dcdmod.Actions.TurnTimer;
import dcdmod.Characters.Decade;
import dcdmod.Vfx.Agito_backtodcd;
import dcdmod.Vfx.Agito_trinity;
import dcdmod.Vfx.Allformbacktodcd;

 
 public class KamenRideAgitoPower extends AbstractPower
 {
	  public static final String POWER_ID = "KamenRideAgitoPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	  int ax = 0;
	  int sx = 0;
	  boolean Storm;
	  boolean Flame;
	   
	   public KamenRideAgitoPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = -1;
	    this.img = ImageMaster.loadImage("img/powers/KamenRideAgitoPower.png");
	   updateDescription();
	   loadRegion("KamenRideAgitoPower");
	   }
	   
	   public void onRemove() {
		   if(this.owner.hasPower("KamenRideDecadePower")) {
			   if(Decade.cf == 17||Decade.cf == 18||Decade.cf == 19||Decade.cf == 21) {
				   AbstractDungeon.actionManager.addToBottom(new VFXAction(new Allformbacktodcd(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 2F));
			   }
			   else {
				   AbstractDungeon.actionManager.addToBottom(new VFXAction(new Agito_backtodcd(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 2F));
			   }		   
		   }
		   AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
		   AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "AgitoLevelPower"));
	   }
	   
	   public void onUseCard(final AbstractCard card, final UseCardAction action) {
		   if(this.owner.hasPower("AgitoStormPower") && this.owner.hasPower("AgitoFlamePower") && card.cardID == "Agito_StormHalberd" && !DCDmod.AnimationTrigger) {
			   AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_trinity(this.owner.drawX - 200.00f, this.owner.drawY + 250.00f), 0F));
		   } 
	   }
	   
	   public void onAfterCardPlayed(final AbstractCard usedCard) {
		   if(usedCard.type == CardType.ATTACK) {
			   if(usedCard.cardID != "Agito_StormHalberd") {
				   ax++;
			   }
			   updateDescription();
		   }
		   if(usedCard.type == CardType.SKILL) {
			   if(usedCard.cardID != "Agito_FlameSaber") {
				   sx++;
			   }   
			   updateDescription();
		   }
		   if(ax >= 5) {
			   ax -= 5;
			   updateDescription();
			   Storm = true;
			   for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
				   if(c.cardID == "FormRideStorm" && Storm!=false) {
					   AbstractDungeon.player.discardPile.removeCard(c);
					   AbstractDungeon.player.hand.addToTop(c);
					   AbstractDungeon.player.hand.refreshHandLayout();
					   AbstractDungeon.player.hand.applyPowers();
					   Storm = false;
					   break;
				   }
			   }
			   for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
				   if(c.cardID == "FormRideStorm" && Storm!=false) {
					   AbstractDungeon.player.drawPile.removeCard(c);
					   AbstractDungeon.player.hand.addToTop(c);
					   AbstractDungeon.player.hand.refreshHandLayout();
					   AbstractDungeon.player.hand.applyPowers();
					   Storm = false;
					   break;
				   }
			   }
			   for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
				   if(c.cardID == "FormRideStorm" && Storm!=false) {
					   AbstractDungeon.player.exhaustPile.removeCard(c);
					   AbstractDungeon.player.hand.addToTop(c.makeCopy());
					   AbstractDungeon.player.hand.refreshHandLayout();
					   AbstractDungeon.player.hand.applyPowers();
					   Storm = false;
					   break;
				   }
			   }
			   AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new AgitoLevelPower(this.owner,1),1));
		   }
		   if(sx >= 5) {
			   sx -= 5;
			   updateDescription();
			   Flame = true;
			   //AbstractCard fc = new FormRideFlame();
			   for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
				   if(c.cardID == "FormRideFlame" && Flame!=false) {
					   AbstractDungeon.player.discardPile.removeCard(c);
					   AbstractDungeon.player.hand.addToTop(c);
					   AbstractDungeon.player.hand.refreshHandLayout();
					   AbstractDungeon.player.hand.applyPowers();
					   Flame = false;
					   break;
				   }
			   }
			   for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
				   if(c.cardID == "FormRideFlame" && Flame!=false) {
					   AbstractDungeon.player.drawPile.removeCard(c);
					   AbstractDungeon.player.hand.addToTop(c);
					   AbstractDungeon.player.hand.refreshHandLayout();
					   AbstractDungeon.player.hand.applyPowers();
					   Flame = false;
					   break;
				   }
			   }
			   for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
				   if(c.cardID == "FormRideFlame" && Flame!=false) {
					   AbstractDungeon.player.exhaustPile.removeCard(c);
					   AbstractDungeon.player.hand.addToTop(c.makeCopy());
					   AbstractDungeon.player.hand.refreshHandLayout();
					   AbstractDungeon.player.hand.applyPowers();
					   Flame = false;
					   break;
				   }
			   }
			   AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new AgitoLevelPower(this.owner,1),1));
		   }
	   }
	   
	   public void atEndOfRound() {
		   TurnTimer.atEndOfRound();
	   }
	   
	   public void onVictory() {
		   TurnTimer.atNextBattle();
		   CardCrawlGame.sound.playA("victory_normal", 0.0f);
		   if(ReturnRandomNumberAction.ReturnRandomNumber()>5.0D) {
			   CardCrawlGame.sound.playA("victory1", 0.0f);
		   }
		   else{
			   CardCrawlGame.sound.playA("victory2", 0.0f);
		   }
		   final Decade Decade = (Decade)AbstractDungeon.player;
		   Decade.Trickster(3);
	   }

	  
	   public void updateDescription() {
			 this.description = DESCRIPTIONS[0] + ax + DESCRIPTIONS[1] + sx + DESCRIPTIONS[2];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideAgitoPower");
	       NAME = KamenRideAgitoPower.powerStrings.NAME;
	       DESCRIPTIONS = KamenRideAgitoPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

