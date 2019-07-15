 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import dcdmod.Card.Special.Kabuto_PutOn;

 
 public class KabutoMaskedPower extends AbstractPower
 {
	  public static final String POWER_ID = "KabutoMaskedPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	  public static boolean DragrederAttack = false;
	  public static int PhotonPoint = 0;
	  boolean noformme;
	   
	   public KabutoMaskedPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = amt;
	    this.img = ImageMaster.loadImage("img/powers/KabutoMaskedPower.png");
	   updateDescription();
	   loadRegion("KabutoMaskedPower");
	   }
	   
	    public float atDamageReceive(final float damage, final DamageInfo.DamageType damageType) {
	        if(damageType != DamageType.HP_LOSS && damageType != DamageType.THORNS && damage <= 5) {
	        	return 0;
	        }
	        else if(damageType != DamageType.HP_LOSS && damageType != DamageType.THORNS && damage > 5) {
	        	return damage - 3;
	        }
	    	return damage;
	    }
	    
	    
	    
		public void onDrawOrDiscard() {
			   if(PhotonPoint >= 5) {
				   this.amount = PhotonPoint-5;
				   PhotonPoint -= 5;
				   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Kabuto_PutOn(), 1));
				   flash();
			   }
			   else {
				   this.amount = PhotonPoint;
			   }
		}
	  
	   public void updateDescription() {
		   this.description = DESCRIPTIONS[0];
	   }
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("KabutoMaskedPower");
	       NAME = KabutoMaskedPower.powerStrings.NAME;
	       DESCRIPTIONS = KabutoMaskedPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

