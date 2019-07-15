 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import dcdmod.Characters.Decade;
import dcdmod.Vfx.Dragreder_attack;
import dcdmod.Vfx.Dragreder_defend;
import dcdmod.Vfx.Dragreder_disappear;
import dcdmod.Vfx.Ryuki_defend;

 
 public class DragrederPower extends AbstractPower
 {
	  public static final String POWER_ID = "DragrederPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	  public static boolean DragrederAttack = false;
	  boolean noformme;
	   
	   public DragrederPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = amt;
	    this.img = ImageMaster.loadImage("img/powers/DragrederPower.png");
	   updateDescription();
	   loadRegion("DragrederPower");
	   }
	   
	   public void onRemove() {
		   AbstractDungeon.actionManager.addToTop(new VFXAction(new Dragreder_disappear(this.owner.drawX - 200.00f, this.owner.drawY + 250.00f), 0F));
		   CardCrawlGame.sound.playA("autovajindisappea", 0.0f); 
	   }
	   
	    public void atStartOfTurn() {
	    	this.updateDescription();
	    	if (this.amount == 0) {
	    		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "DragrederPower"));
	    	}
	    	else {
	    		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "DragrederPower", 1));
	    	}
	    }
	   
	    public int onAttacked(final DamageInfo info, final int damageAmount) {
	        if(this.owner.hasPower("DragShieldPower")&&info.owner!=this.owner) {
	        	AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "DragShieldPower", 1));
	        	if(Decade.cf != 24) {
	        		AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_defend(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0F));
	        		AbstractDungeon.actionManager.addToTop(new VFXAction(new Dragreder_defend(this.owner.drawX - 200.00f, this.owner.drawY + 250.00f), 0F));
	        	}
	        	if(this.owner.hasPower("MirrorWorldPower")) {
	        		AbstractDungeon.actionManager.addToBottom(new DamageAction(info.owner,new DamageInfo(this.owner, ((this.owner.maxHealth * 20 ) / 100), DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
	        	}
	        	else {
	        		AbstractDungeon.actionManager.addToBottom(new DamageAction(info.owner,new DamageInfo(this.owner, ((this.owner.maxHealth * 10 ) / 100), DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
	        	}
	        }
	    	return damageAmount;
	    }
	    
	    public void atEndOfTurn(final boolean isPlayer) {
	    	int x = 0;
	    	int d= 10;
	    	if(this.owner.hasPower("MirrorWorldPower")) {
	    		d+=10;
	    	}
	    	AbstractDungeon.actionManager.addToTop(new VFXAction(new Dragreder_attack(this.owner.drawX - 200.00f, this.owner.drawY + 250.00f), 0F));
			CardCrawlGame.sound.playA("dragreder_attack", 0.0f);
			for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
				 if ((!monster.isDead) && (!monster.isDying)) {
					 AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,new DamageInfo(this.owner, d, DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
					 if(this.owner.hasPower("MirrorWorldPower")) {
						 x+=2;
					 }
					 else {
						 x++;
					 }
				 }
			}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DragClawPower(this.owner, x), x));
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, 10));
	    }
	   	   
	    
	   public void onVictory() {
		   AbstractDungeon.actionManager.addToTop(new VFXAction(new Dragreder_disappear(this.owner.drawX - 200.00f, this.owner.drawY + 250.00f), 0F));
		   CardCrawlGame.sound.playA("autovajindisappea", 0.0f); 
	   }

	  
	   public void updateDescription() {
		   if(this.owner.hasPower("MirrorWorldPower")) {
			   this.description = DESCRIPTIONS[1];
		   }
		   else {
			   this.description = DESCRIPTIONS[0];
		   }
	   }
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("DragrederPower");
	       NAME = DragrederPower.powerStrings.NAME;
	       DESCRIPTIONS = DragrederPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

