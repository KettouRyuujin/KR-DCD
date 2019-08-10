 package dcdmod.Power;

 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
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
 import dcdmod.Vfx.*;


 public class KamenRideKuugaPower extends AbstractPower
 {
	  public static final String POWER_ID = "KamenRideKuugaPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	   
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

	 @Override
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

	 @Override
	 public void atEndOfRound() {
		 TurnTimer.atEndOfRound();
	 }

	 @Override
	 public int onAttacked(DamageInfo info, int damageAmount) {
		 if(info.owner != this.owner && info.type == DamageInfo.DamageType.NORMAL && damageAmount < 1){
		 	if(this.owner.hasPower("KuugaTitanPower")||this.owner.hasPower("RisingTitanPower")){
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_TitanDefend(), 0F));
			}
		 	else{
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_defend(), 0F));
			}
		 }
		 if(info.owner != this.owner && info.type == DamageInfo.DamageType.NORMAL && damageAmount > 0){
			 AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_attacked(), 0F));
		 }
		 return super.onAttacked(info, damageAmount);
	 }

	 @Override
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

	 @Override
	 public void updateDescription() {
		 this.description = DESCRIPTIONS[0];
	 }

	 static {
		 powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideKuugaPower");
		 NAME = KamenRideKuugaPower.powerStrings.NAME;
		 DESCRIPTIONS = KamenRideKuugaPower.powerStrings.DESCRIPTIONS;
	 }
 }

