 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
import dcdmod.Helper.SpecialRideBooker;
import dcdmod.Vfx.Ryuki_attack;
import dcdmod.Vfx.Ryuki_backtodcd;
import dcdmod.Vfx.Ryuki_guardattack;
import dcdmod.Vfx.Ryuki_strikeattack;

 
 public class KamenRideRyukiPower extends AbstractPower
 {
	  public static final String POWER_ID = "KamenRideRyukiPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;

	   
	   public KamenRideRyukiPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = -1;
	    this.img = ImageMaster.loadImage("img/powers/KamenRideRyukiPower.png");
	    
	   updateDescription();
	   loadRegion("KamenRideRyukiPower");
	 }
	   
	   public void onRemove() {
		   if(this.owner.hasPower("KamenRideDecadePower")) {
			   AbstractDungeon.actionManager.addToBottom(new VFXAction(new Ryuki_backtodcd(), 2F));
			   SpecialRideBooker.kamenpowerpoint = 1;
		   }
		   AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
	   }
	   
	   public void atStartOfTurn() {
		   SpecialRideBooker.haskamenpower = true;
	   }
	   
	   public void onUseCard(final AbstractCard card, final UseCardAction action) {
		   if(!DCDmod.AnimationTrigger) {
			   if(this.owner.hasPower("DragClawPower") && !this.owner.hasPower("DragShieldPower") && card.cardID.equals("Ryuki_DragSaber")) {
				   AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_strikeattack(), 1F));
			   }
			   else if(this.owner.hasPower("DragShieldPower") && card.cardID.equals("Ryuki_DragSaber")) {
				   AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_guardattack(), 0.8F));
			   }
			   else if(card.cardID.equals("Ryuki_DragSaber")) {
				   AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_attack(), 0F));
			   }
		   }
	   }

	   public void atEndOfTurn(boolean isPlayer){
		   SpecialRideBooker.haskamenpower = false;
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
			 this.description = DESCRIPTIONS[0];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideRyukiPower");
	       NAME = KamenRideRyukiPower.powerStrings.NAME;
	       DESCRIPTIONS = KamenRideRyukiPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

