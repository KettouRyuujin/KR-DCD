 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import dcdmod.Actions.ReturnRandomNumberAction2;
import dcdmod.Vfx.Kuuga_dash;


 public class RisingDragonPower extends AbstractPower
 {
	  public static final String POWER_ID = "RisingDragonPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;

	 public RisingDragonPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = amt;
	    this.img = ImageMaster.loadImage("img/powers/RisingDragonPower.png");
	   updateDescription();
	   loadRegion("RisingDragonPower");
	   }

	 public void onRemove() {
		 for(AbstractMonster monster:AbstractDungeon.getMonsters().monsters){
			 if( !monster.isDead && !monster.isDying && monster.hasPower("KuugaSpecialPower") && monster.getPower("KuugaSpecialPower").amount>3){
				 int x = monster.getPower("KuugaSpecialPower").amount - 3;
				 AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(monster, monster, "KuugaSpecialPower", x));
			 }

		 }
	 }

	    public int onAttacked(final DamageInfo info, final int damageAmount) {
	        if(info.type != DamageType.HP_LOSS && info.owner != this.owner  && info.owner != null) {
	        	if(this.owner.hasPower("Dexterity")) {
					int power = this.owner.getPower("Dexterity").amount;
	        		power = power *3;
	        		if(ReturnRandomNumberAction2.ReturnRandomNumber()< power) {
	        			flash();
						AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_dash(), 0F));
	        			return 0;
	        		}
	        	}
	        }
	    	return damageAmount;
	    }
	   
	   public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {
		   int x = this.amount;
		   if(x <6) {
			   x++;}
		   if(x == 6){
			   x = -1;
			   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DragonDashPower(this.owner, 1), 1));
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
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("RisingDragonPower");
	       NAME = RisingDragonPower.powerStrings.NAME;
	       DESCRIPTIONS = RisingDragonPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

