package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class GunFormPower extends AbstractPower
 {
  public static final String POWER_ID = "GunFormPower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  boolean BlastSpecialPower = false;
  int x = 0;
  
   public GunFormPower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    this.img = ImageMaster.loadImage("img/powers/RideBooker.png");
   updateDescription();
   loadRegion("GunFormPower");
 }

  
   public void updateDescription() {
	   	 if(this.amount==-1) {
	   		 this.description = DESCRIPTIONS[0] + 0 + DESCRIPTIONS[1];
		 }
	   	 else {
	   		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
	   	 }
   }
   
   public void onUseCard(final AbstractCard card, final UseCardAction action) {
	   if(card.cardID == "Decade_Blast") {
		   BlastSpecialPower = true;
	   }
   }
   
   public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {
	   if(BlastSpecialPower) {
		   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, this.owner, new WeakPower(target, 2, false), 2));
	   }
   }
   
   public void onAfterUseCard(final AbstractCard card, final UseCardAction action) {
	   if(card.cardID == "RideBooker_Shoot") {
		   x++;
		   this.amount = x;
		   updateDescription();
	   }
	   if(card.cardID == "Decade_Blast") {
		   x-=2;
		   this.amount = x;
		   BlastSpecialPower = false;
		   updateDescription();
	   }
	   if(x == 0){
		   this.amount = -1;
		   updateDescription();
	   }
   }
 
   public void onRemove() {
	   for(int i = 0;i < 3; i++) {
		   for(AbstractCard c : AbstractDungeon.player.hand.group) {
			   if(c.cardID == "RideBooker_Shoot") {
				   AbstractDungeon.player.hand.removeCard(c);
				   break;
			   }
		   }
		   for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
			   if(c.cardID == "RideBooker_Shoot") {
				   AbstractDungeon.player.discardPile.removeCard(c);
				   break;
			   }
		   }
		   for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
			   if(c.cardID == "RideBooker_Shoot") {
				   AbstractDungeon.player.drawPile.removeCard(c);
				   break;
			   }
		   }
		   for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
			   if(c.cardID == "RideBooker_Shoot") {
				   AbstractDungeon.player.exhaustPile.removeCard(c);
				   break;
			   }
		   }
		}
	   	for(AbstractCard c : AbstractDungeon.player.hand.group) {
	   		if(c.cardID == "Decade_Blast") {
	   			AbstractDungeon.player.hand.removeCard(c);
	   			break;
	   		}
	   	}
	   	for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
	   		if(c.cardID == "Decade_Blast") {
	   			AbstractDungeon.player.discardPile.removeCard(c);
	   			break;
	   		}
	   	}
	   	for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
	   		if(c.cardID == "Decade_Blast") {
	   			AbstractDungeon.player.drawPile.removeCard(c);
	   			break;
	   		}
	   	}
	   	for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
	   		if(c.cardID == "Decade_Blast") {
	   			AbstractDungeon.player.exhaustPile.removeCard(c);
	   			break;
	   		}
	   	}
	}
   
   public void atStartOfTurn() { 

   }
 
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("GunFormPower");
       NAME = GunFormPower.powerStrings.NAME;
       DESCRIPTIONS = GunFormPower.powerStrings.DESCRIPTIONS;
   }
   
 }