 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

 
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
	   
	   public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {
		   if(target != this.owner) {
			   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,this.owner, new KuugaSpecialPower(target,1), 1));
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

