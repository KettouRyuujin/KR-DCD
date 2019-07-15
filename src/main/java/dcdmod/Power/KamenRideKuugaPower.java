 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dcdmod.Actions.RemoveFormRideAction;
import dcdmod.Actions.ReturnRandomNumberAction;
import dcdmod.Actions.TurnTimer;
import dcdmod.Characters.Decade;
import dcdmod.Vfx.Allformbacktodcd;
import dcdmod.Vfx.Kuuga_backtodcd;

 
 public class KamenRideKuugaPower extends AbstractPower
 {
	  public static final String POWER_ID = "KamenRideKuugaPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	  int power;
	  int x = 0;
	   
	   public KamenRideKuugaPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = -1;
	    this.img = ImageMaster.loadImage("img/powers/KamenRideKuugaPower.png");
	   updateDescription();
	   loadRegion("KamenRideKuugaPower");
	   }
	   
	   public void onRemove() {
		   if(this.owner.hasPower("KamenRideDecadePower")&&(this.owner.hasPower("KuugaDragonPower")||this.owner.hasPower("RisingDragonPower")||this.owner.hasPower("KuugaPegasusPower")||this.owner.hasPower("RisingPegasusPower")||this.owner.hasPower("KuugaTitanPower")||this.owner.hasPower("RisingTitanPower"))) {
		   //(this.owner.hasPower("KamenRideDecadePower")&&!this.owner.hasPower("KuugaDragonPower")&&!this.owner.hasPower("RisingDragonPower")&&!this.owner.hasPower("KuugaTitanPower")&&!this.owner.hasPower("RisingTitanPower")&&!this.owner.hasPower("KuugaPegasusPower")&&!this.owner.hasPower("RisingPegasusPower")) {
			   AbstractDungeon.actionManager.addToBottom(new VFXAction(new Allformbacktodcd(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 2F));
			   AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
		   }
		   else if(this.owner.hasPower("KamenRideDecadePower")) {//&&(this.owner.hasPower("KuugaDragonPower")||this.owner.hasPower("RisingDragonPower")||this.owner.hasPower("KuugaPegasusPower")||this.owner.hasPower("RisingPegasusPower")||this.owner.hasPower("KuugaTitanPower")||this.owner.hasPower("RisingTitanPower"))) {
			   AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_backtodcd(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 2F));
			   AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
		   }
	   }
	   
	   public void atEndOfRound() {
		   TurnTimer.atEndOfRound();
	   }
	   
	   public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {
		   if(target != this.owner && info.type != DamageType.THORNS) {
			   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,this.owner, new KuugaSpecialPower(target,1), 1));
		   }
	   }
	   
	   public void onVictory() {
		   TurnTimer.atNextBattle();
		   CardCrawlGame.sound.playA("victory_normal", 0.0f);
		   if(ReturnRandomNumberAction.ReturnRandomNumber()>5.0D) {
			   CardCrawlGame.sound.playA("victory1", 0.0f);
		   }
		   else{
			   CardCrawlGame.sound.playA("victory2", 0.0f);
		   }
		   final Decade Decade = (Decade)AbstractDungeon.player;
		   Decade.Trickster(3);
	   }

	  
	   public void updateDescription() {
			 this.description = DESCRIPTIONS[0];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideKuugaPower");
	       NAME = KamenRideKuugaPower.powerStrings.NAME;
	       DESCRIPTIONS = KamenRideKuugaPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

