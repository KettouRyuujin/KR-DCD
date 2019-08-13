 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dcdmod.Actions.KabutoDiscardAction;
import dcdmod.Actions.RemoveFormRideAction;
import dcdmod.Actions.ReturnRandomNumberAction;
import dcdmod.Actions.TurnTimer;
import dcdmod.Characters.Decade;
import dcdmod.Vfx.Allformbacktodcd;
import dcdmod.Vfx.Kabuto_backtodcd;

 
 public class KamenRideKabutoPower extends AbstractPower
 {
	  public static final String POWER_ID = "KamenRideKabutoPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;

	   
	   public KamenRideKabutoPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = -1;
	    this.img = ImageMaster.loadImage("img/powers/KamenRideKabutoPower.png");
	   updateDescription();
	   loadRegion("KamenRideKabutoPower");
	   }
	   
	   public void onRemove() {
		   if(this.owner.hasPower("KabutoMaskedPower")) {
			   AbstractDungeon.actionManager.addToBottom(new VFXAction(new Allformbacktodcd(), 2F));
			   AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
		   }
		   else if(this.owner.hasPower("KamenRideDecadePower")) {
			   AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kabuto_backtodcd(), 2F));
			   AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
		   }
	   }
	   
	   public void atEndOfRound() {
		   TurnTimer.atEndOfRound();
	   }
	   
	   public void onUseCard(final AbstractCard card, final UseCardAction action) {
		   if(!AbstractDungeon.player.hasPower("KabutoMaskedPower")) {
		    	AbstractDungeon.actionManager.addToTop(new KabutoDiscardAction(AbstractDungeon.player, AbstractDungeon.player, 2, false));
		    	AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, 2));
		   }
			if(this.owner.hasPower("KabutoStrengthPower")) {
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "KabutoStrengthPower")); 
			}
			if(this.owner.hasPower("KabutoDexterityPower")) {
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "KabutoDexterityPower")); 
			}
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
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideKabutoPower");
	       NAME = KamenRideKabutoPower.powerStrings.NAME;
	       DESCRIPTIONS = KamenRideKabutoPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

