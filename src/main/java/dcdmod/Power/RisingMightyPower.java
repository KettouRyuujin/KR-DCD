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
import dcdmod.DCDmod;


 public class RisingMightyPower extends AbstractPower
 {
	  public static final String POWER_ID = "RisingMightyPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	   
	   public RisingMightyPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = -1;
	    this.img = ImageMaster.loadImage("img/powers/RisingMightyPower.png");
	   updateDescription();
	   loadRegion("RisingMightyPower");
	   }

	 public void onUseCard(final AbstractCard card, final UseCardAction action) {
	   	if(!this.owner.hasPower("RisingDragonPower")&&!this.owner.hasPower("RisingPegasusPower")&&
				!this.owner.hasPower("RisingTitanPower") && card.hasTag(DCDmod.UnarmedCard)){
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner,this.owner,new RisingMightySpecialPower(this.owner,1),1));
		}
	 }

	  
	   public void updateDescription() {
			 this.description = DESCRIPTIONS[0];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("RisingMightyPower");
	       NAME = RisingMightyPower.powerStrings.NAME;
	       DESCRIPTIONS = RisingMightyPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

