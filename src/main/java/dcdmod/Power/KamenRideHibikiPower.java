 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
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
import dcdmod.Helper.SpecialTaikoEffects;
import dcdmod.Patches.HibikiTaikoKeyEvent;
import dcdmod.Vfx.Allformbacktodcd;
import dcdmod.Vfx.Hibiki_backtodcd;

 
 public class KamenRideHibikiPower extends AbstractPower
 {
	  public static final String POWER_ID = "KamenRideHibikiPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;

	   
	   public KamenRideHibikiPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = -1;
	    this.img = ImageMaster.loadImage("img/powers/KamenRideHibikiPower.png");
	    
	   updateDescription();
	   loadRegion("KamenRideHibikiPower");
	 }
	   
	   
	   public void onRemove() {
		   if(this.owner.hasPower("HibikiKurenaiPower")) {
			   AbstractDungeon.actionManager.addToBottom(new VFXAction(new Allformbacktodcd(), 2F));
			   AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
			   AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "HibikiKurenaiSpecialPower"));
		   }
		   else if(this.owner.hasPower("KamenRideDecadePower")) {
			   AbstractDungeon.actionManager.addToBottom(new VFXAction(new Hibiki_backtodcd(), 2F));
		   }
		   if(HibikiTaikoKeyEvent.Fever){
			   HibikiTaikoKeyEvent.Fever = false;
			   AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "FeverPower"));
			   if(!DCDmod.AnimationTrigger) {
				   SpecialTaikoEffects.a = 4;
				   SpecialTaikoEffects.update();
			   }
		   }
	   }
	   
	   public void atStartOfTurn() {
		   HibikiTaikoKeyEvent.ActionPoint += 1;
		   HibikiTaikoKeyEvent.TaikoTrigger = true;
	   }

	   public void atEndOfTurn(boolean isPlayer){
		   HibikiTaikoKeyEvent.TaikoTrigger = false;
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
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideHibikiPower");
	       NAME = KamenRideHibikiPower.powerStrings.NAME;
	       DESCRIPTIONS = KamenRideHibikiPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

