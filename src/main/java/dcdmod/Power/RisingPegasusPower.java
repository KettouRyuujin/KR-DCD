 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import dcdmod.DCDmod;
import dcdmod.Actions.RemoveFormRideAction;
import dcdmod.Actions.RemoveKamenRideAction;
import dcdmod.Card.Special.PegasusAttack;
import dcdmod.Vfx.Allformbacktodcd;

 
 public class RisingPegasusPower extends AbstractPower
 {
	  public static final String POWER_ID = "RisingPegasusPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	  boolean trueremove = false;
	   
	   public RisingPegasusPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = amt;
	    this.img = ImageMaster.loadImage("img/powers/RisingPegasusPower.png");
	   updateDescription();
	   loadRegion("RisingPegasusPower");
	   }
	   
	   public void onRemove() {
		   if(trueremove) {
			   AbstractDungeon.actionManager.addToTop(new VFXAction(new Allformbacktodcd(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 2F));
			   AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
			   AbstractDungeon.actionManager.addToBottom(new RemoveKamenRideAction(this.owner, this.owner));
			   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new KamenRideDecadePower(this.owner,1),1));
			   CardCrawlGame.sound.playA("driversounds", 0.0f);
			   CardCrawlGame.sound.playA("test1", 0.0f); 
		   }
	   }
	   
	   public void onUseCard(final AbstractCard card, final UseCardAction action) {
		   if(card.hasTag(DCDmod.KamenRide)&& card.cardID != "Kuuga_Rising"){
			   trueremove = false;
		   }
	   }
	   
	   public void atStartOfTurn() {
		   this.updateDescription();
		   trueremove = true;
		   AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "RisingPegasusPower", 1));
		   if(this.amount != 1){
			   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PegasusAttack(), 1));
		   }
	   }
	   
	   public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
		   return 0;   
	   }

	  
	   public void updateDescription() {
			 this.description = DESCRIPTIONS[0];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("RisingPegasusPower");
	       NAME = RisingPegasusPower.powerStrings.NAME;
	       DESCRIPTIONS = RisingPegasusPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

