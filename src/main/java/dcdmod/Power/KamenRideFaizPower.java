 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import dcdmod.Actions.EnterButtonAction;
import dcdmod.Actions.FaizGearAction;
import dcdmod.Actions.RemoveFormRideAction;
import dcdmod.Actions.ReturnRandomNumberAction;
import dcdmod.Actions.TurnTimer;
import dcdmod.Characters.Decade;
import dcdmod.Helper.SpecialFaizButton;
import dcdmod.Vfx.Allformbacktodcd;
import dcdmod.Vfx.Axel_attack;
import dcdmod.Vfx.Faiz_backtodcd;

 
 public class KamenRideFaizPower extends AbstractPower
 {
	  public static final String POWER_ID = "KamenRideFaizPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	  
	  
	   
	   public KamenRideFaizPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = -1;
	    this.img = ImageMaster.loadImage("img/powers/KamenRideFaizPower.png");
	    updateDescription();
	    loadRegion("KamenRideFaizPower");
	 }
	   
	   
	   public void onRemove() {
		   if(this.owner.hasPower("KamenRideDecadePower")) {
			   if(Decade.cf == 39||Decade.cf == 40||Decade.cf == 41) {
				   AbstractDungeon.actionManager.addToBottom(new VFXAction(new Allformbacktodcd(), 2F));
			   }
			   else {
				   AbstractDungeon.actionManager.addToBottom(new VFXAction(new Faiz_backtodcd(), 2F));
			   }		   
		   }
		   AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
		   EnterButtonAction.AxelForm = false;
			for (int i = 0; i < 4; ++i) {
				AbstractDungeon.player.decreaseMaxOrbSlots(1);
			}
	   }
	   
	   public void atStartOfTurn() {
		   SpecialFaizButton.haskamenpower = true;
	   }

	   public void onUseCard(final AbstractCard card, final UseCardAction action) {
		   if(EnterButtonAction.AxelForm) {
			   AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
			   if(card.type == CardType.ATTACK) {
				   AbstractDungeon.actionManager.addToBottom(new VFXAction(new Axel_attack(), 0F));
			   }
		   }
	   }
	   
	   public void atEndOfTurn(boolean isPlayer){
		   SpecialFaizButton.haskamenpower = false;
		   FaizGearAction.FaizPoint +=1;
		   updateDescription();
	   }
	   
	   public void atEndOfRound() {
		   TurnTimer.atEndOfRound();
	   }
	   
	   public void onVictory() {
		   TurnTimer.atNextBattle();
			for (int i = 0; i < 4; ++i) {
				AbstractDungeon.player.decreaseMaxOrbSlots(1);
			}
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
			 this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideFaizPower");
	       NAME = KamenRideFaizPower.powerStrings.NAME;
	       DESCRIPTIONS = KamenRideFaizPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

