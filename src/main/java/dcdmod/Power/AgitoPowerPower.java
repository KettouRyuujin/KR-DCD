package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import dcdmod.Characters.Decade;

public class AgitoPowerPower extends AbstractPower {
	public static final String POWER_ID = "AgitoPowerPower";
	private static final PowerStrings powerStrings;
	public static final String NAME;
	public static final String[] DESCRIPTIONS;

	public AgitoPowerPower(AbstractCreature owner, int amt) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amt;
		this.updateDescription();
		this.img = ImageMaster.loadImage("img/powers/AgitoPowerPower.png");
		this.loadRegion("AgitoPowerPower");
		this.type = PowerType.BUFF;
	}

	public void stackPower(int stackAmount) {
		updateDescription();
		if (this.amount == 0) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "AgitoPowerPower"));
		}
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}
	
	public void onRemove() {
		if(this.owner.hasPower("KamenRideAgitoPower") && this.owner.hasPower("AgitoStormPower") && this.owner.hasPower("AgitoFlamePower")) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(17);
		}
		else if(this.owner.hasPower("KamenRideAgitoPower")) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(14);
		}
	}
	
	public float atDamageGive(final float damage, final DamageInfo.DamageType type) {
		return damage * 2;
	}
	
	public void onAfterCardPlayed(final AbstractCard usedCard) {
		   if(usedCard.type == CardType.ATTACK) {
			   AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "AgitoPowerPower", 1));
		   }
	}
	
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

	static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("AgitoPowerPower");
	       NAME = AgitoPowerPower.powerStrings.NAME;
	       DESCRIPTIONS = AgitoPowerPower.powerStrings.DESCRIPTIONS;
	}
}