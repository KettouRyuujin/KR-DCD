 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import dcdmod.Actions.RemoveFormRideAction;
import dcdmod.Actions.ReturnRandomNumberAction;
import dcdmod.Actions.TurnTimer;
import dcdmod.Characters.Decade;
import dcdmod.Helper.SpecialRideBooker;
import dcdmod.Vfx.Allformbacktodcd;
import dcdmod.Vfx.Blade_backtodcd;

 
 public class KamenRideBladePower extends AbstractPower
 {
	  public static final String POWER_ID = "KamenRideBladePower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	  int power;
	  int x = 0;
	   
	   public KamenRideBladePower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = -1;
	    this.img = ImageMaster.loadImage("img/powers/KamenRideBladePower.png");
	    
	   updateDescription();
	   loadRegion("KamenRideBladePower");
	 }
	   
	   public void onRemove() {
		   if(this.owner.hasPower("KamenRideDecadePower")&&(this.owner.hasPower("BladeJackPower"))) {
			   AbstractDungeon.actionManager.addToBottom(new VFXAction(new Allformbacktodcd(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 2F));
			   AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
			   AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "JackFlightPower"));
		   }
		   else if(this.owner.hasPower("KamenRideDecadePower")) {
			   AbstractDungeon.actionManager.addToBottom(new VFXAction(new Blade_backtodcd(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 2F));
		   }
	   }
	   
	   public void atStartOfTurn() {
		   SpecialRideBooker.haskamenpower = true;
	   }

	   public void atEndOfTurn(boolean isPlayer){
		   if(this.owner.currentBlock<=0 && !this.owner.hasPower("BladeJackPower")) {
			   AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, 5));
		   }
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
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideBladePower");
	       NAME = KamenRideBladePower.powerStrings.NAME;
	       DESCRIPTIONS = KamenRideBladePower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

