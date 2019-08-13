package dcdmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import dcdmod.Vfx.Agito_flame;
import dcdmod.Vfx.Agito_trinity;

public class FlameSpecialPower extends AbstractPower
 {
  public static final String POWER_ID = "FlameSpecialPower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  private boolean Block;

  
   public FlameSpecialPower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amt;
    this.img = ImageMaster.loadImage("img/powers/Mark.png");
   updateDescription();
   loadRegion("FlameSpecialPower");
 }

  
   public void stackPower(int stackAmount) {
	   if (this.amount == 0) {
		   AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "FlameSpecialPower"));
	   }
	   this.fontScale = 8.0F;
	   this.amount += stackAmount;
   }
   
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];
   }
   
   public float atDamageReceive(final float damage, final DamageInfo.DamageType damageType) {	
	   if(damageType != DamageType.HP_LOSS && damageType != DamageType.THORNS) {
		   Block = true;
		   return 0;
	   }
	   return damage;
   }
   
   public void onUseCard(final AbstractCard card, final UseCardAction action) {
	   if(card.type != CardType.CURSE){
		   AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "FlameSpecialPower", this.amount));
	   }
   }
   
   	public int onAttacked(final DamageInfo info, final int damageAmount) {
   		int x;
   		if(this.owner.hasPower("Dexterity") && info.owner != this.owner && Block) {
   			x = this.owner.getPower("Dexterity").amount;
   			if(x>0) {
 			   if(this.owner.hasPower("AgitoFlamePower")&&!this.owner.hasPower("AgitoStormPower")) {
				   AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_flame(), 0F));
			   }
			   else if(this.owner.hasPower("AgitoFlamePower")&&this.owner.hasPower("AgitoStormPower")) {
				   AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_trinity(), 0F));
			   }
 			   AbstractDungeon.actionManager.addToBottom(new DamageAction(info.owner,new DamageInfo(this.owner, x * 3,  DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
   			}
   			Block = false;
	  	}
   		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "FlameSpecialPower", 1));
   		return damageAmount;
   	}
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("FlameSpecialPower");
       NAME = FlameSpecialPower.powerStrings.NAME;
       DESCRIPTIONS = FlameSpecialPower.powerStrings.DESCRIPTIONS;
   }
   
 }