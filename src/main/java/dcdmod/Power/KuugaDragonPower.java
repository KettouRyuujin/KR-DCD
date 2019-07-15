 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;

import dcdmod.Actions.ReturnRandomNumberAction2;

 
 public class KuugaDragonPower extends AbstractPower
 {
	  public static final String POWER_ID = "KuugaDragonPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	  int power;
	  int x = 0;
	   
	   public KuugaDragonPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = -1;
	    this.img = ImageMaster.loadImage("img/powers/KuugaDragonPower.png");
	   updateDescription();
	   loadRegion("KuugaDragonPower");
	   }
	   
	    public int onAttacked(final DamageInfo info, final int damageAmount) {
	        if(info.type != DamageType.HP_LOSS && info.owner != this.owner  && info.owner != null) {
	        	if(this.owner.hasPower("Dexterity")) {
	        		power = this.owner.getPower("Dexterity").amount;
	        		power = power*2;
	        		if(ReturnRandomNumberAction2.ReturnRandomNumber()<power) {
	        			flash();
	        			return 0;
	        		}
	        	}
	        }
	    	return damageAmount;
	    }
	   
	   public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {
		   if(x<8) {x++;}
		   if(x == 8){
			   x = -1;
			   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new BufferPower(this.owner, 1), 1));
		   }
		   this.amount = x;
		   updateDescription();
	   }
	   
		public float modifyBlock(float blockAmount) {
			return 0;
		}

	  
	   public void updateDescription() {
			 this.description = DESCRIPTIONS[0];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("KuugaDragonPower");
	       NAME = KuugaDragonPower.powerStrings.NAME;
	       DESCRIPTIONS = KuugaDragonPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

