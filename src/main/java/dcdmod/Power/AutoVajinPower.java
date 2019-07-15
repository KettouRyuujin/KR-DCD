 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import dcdmod.Helper.SpecialAutoVajin;
import dcdmod.Vfx.AutoVajin_attack;
import dcdmod.Vfx.AutoVajin_defend;

 
 public class AutoVajinPower extends AbstractPower
 {
	  public static final String POWER_ID = "AutoVajinPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	  public static boolean AutoVajinAttack = false;
	  boolean noformme;
	   
	   public AutoVajinPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = amt;
	    this.img = ImageMaster.loadImage("img/powers/AutoVajinPower.png");
	   updateDescription();
	   loadRegion("AutoVajinPower");
	   }
	   
	   public void onRemove() {
			SpecialAutoVajin.a = 3;
			SpecialAutoVajin.update();
			CardCrawlGame.sound.playA("autovajindisappear", 0.0f); 
	   }
	   
	    public void atStartOfTurn() {
	    	this.updateDescription();
	    	if (this.amount == 0) {
	    		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "AutoVajinPower"));
	    	}
	    	else {
	    		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "AutoVajinPower", 1));
	    	}
	    }
	   
	    public void atEndOfTurn(final boolean isPlayer) {
	    	AbstractDungeon.actionManager.addToBottom(new VFXAction(new AutoVajin_defend(AbstractDungeon.player.drawX, AbstractDungeon.player.drawY), 0F));
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, 8));
	    }
	   
	   public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {
		   if(target != this.owner && AutoVajinAttack) {
			   AbstractDungeon.actionManager.addToBottom(new VFXAction(new AutoVajin_attack(AbstractDungeon.player.drawX, AbstractDungeon.player.drawY), 0F));
				for(int i=0;i<3;i++) {
					CardCrawlGame.sound.playA("autovajinattack", 0.0f); 
					AbstractDungeon.actionManager.addToTop(new VFXAction(this.owner, new CleaveEffect(), 0.0F));
					AbstractDungeon.actionManager.addToTop(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true),new DamageInfo(this.owner, 3, DamageType.NORMAL)));	
				}
				AutoVajinAttack = false;
		   }
	   }
	   
	    public int onAttacked(final DamageInfo info, final int damageAmount) {
			if(info.owner != this.owner){
				noformme = true;
			}
	    	return damageAmount;
	    }
	   
	    public int onLoseHp(final int damageAmount) {
	    	if(noformme) {
	    		noformme = false;
	    		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "AutoVajinPower"));
	    		return 0;
	    	}
	        return damageAmount;
	    }
	   
	   public void onVictory() {
			SpecialAutoVajin.a = 3;
			SpecialAutoVajin.update();
	   }

	  
	   public void updateDescription() {
			 this.description = DESCRIPTIONS[0];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("AutoVajinPower");
	       NAME = AutoVajinPower.powerStrings.NAME;
	       DESCRIPTIONS = AutoVajinPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

