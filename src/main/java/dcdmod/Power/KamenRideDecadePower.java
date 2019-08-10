 package dcdmod.Power;

 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import dcdmod.Actions.ReturnRandomNumberAction;
 import dcdmod.Actions.TurnTimer;
 import dcdmod.Characters.Decade;
 import dcdmod.Helper.SpecialRideBooker;
 import dcdmod.Vfx.Decade_attacked;
 import dcdmod.Vfx.Decade_defend;


 public class KamenRideDecadePower extends AbstractPower {
	public static final String POWER_ID = "KamenRideDecadePower";
	private static final PowerStrings powerStrings;
	public static final String NAME;
	public static final String[] DESCRIPTIONS;
	   
	public KamenRideDecadePower(AbstractCreature owner, int amt) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = -1;
		this.img = ImageMaster.loadImage("img/powers/KamenRideDecadePower.png");
		updateDescription();
		loadRegion("KamenRideDecadePower");
	}

	 @Override
	 public int onAttacked(DamageInfo info, int damageAmount) {
		if(info.owner != this.owner && info.type == DamageInfo.DamageType.NORMAL && damageAmount < 1){
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Decade_defend(), 0F));
		}
		if(info.owner != this.owner && info.type == DamageInfo.DamageType.NORMAL && damageAmount > 0){
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Decade_attacked(), 0F));
		}
		return super.onAttacked(info, damageAmount);
	 }


	 @Override
	 public void atStartOfTurn() {
		SpecialRideBooker.haskamenpower = true;
	 }

	 @Override
	 public void atEndOfTurn(boolean isPlayer) {
		SpecialRideBooker.haskamenpower = false;
	 }

	 @Override
	 public void atEndOfRound() {
		TurnTimer.atEndOfRound();
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
		powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideDecadePower");
		NAME = KamenRideDecadePower.powerStrings.NAME;
		DESCRIPTIONS = KamenRideDecadePower.powerStrings.DESCRIPTIONS;
	 }

 }

