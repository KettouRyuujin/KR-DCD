 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import dcdmod.Actions.RemoveDefendBuffAction;

 
 public class KuugaPegasusPower extends AbstractPower
 {
	  public static final String POWER_ID = "KuugaPegasusPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;

	   
	   public KuugaPegasusPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = -1;
	    this.img = ImageMaster.loadImage("img/powers/KuugaPegasusPower.png");
	   updateDescription();
	   loadRegion("KuugaPegasusPower");
	   }
	   
	   public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {
		   AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(this.owner, this.owner));
		   AbstractDungeon.actionManager.addToBottom(new RemoveDefendBuffAction(this.owner, this.owner));
	   }
	   
		public int onAttacked(DamageInfo info, int damageAmount) {
			return (int) (damageAmount*1.5);
		}

	  
	   public void updateDescription() {
			 this.description = DESCRIPTIONS[0];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("KuugaPegasusPower");
	       NAME = KuugaPegasusPower.powerStrings.NAME;
	       DESCRIPTIONS = KuugaPegasusPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

