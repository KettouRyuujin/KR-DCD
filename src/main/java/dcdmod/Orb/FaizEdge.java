package dcdmod.Orb;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import basemod.abstracts.CustomOrb;
import dcdmod.Actions.EnterButtonAction;
import dcdmod.Power.Flex1;
import dcdmod.Power.Flex2;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class FaizEdge extends CustomOrb {
	public static final String ORB_ID = "FaizEdge";
	private static final OrbStrings orbString;
	public static final String[] DESC;
	
	public FaizEdge() {
		super(ORB_ID, orbString.NAME, 1, 1, DESC[0], DESC[1], "img/orbs/SB-555H.png");
		this.evokeAmount = this.baseEvokeAmount;
		this.passiveAmount = this.basePassiveAmount;
		this.updateDescription();
	}

	public void updateDescription() {
		this.applyFocus();
		this.description = DESC[0] + DESC[1];
	}

	public void onEvoke() {

	}

	public void onStartOfTurn() {
		if(EnterButtonAction.FaizEdge) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Flex1(AbstractDungeon.player, 1), 1));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Flex2(AbstractDungeon.player, 1), 1));
		}
	}
	
	public void render(SpriteBatch sb) {
		if(!EnterButtonAction.FaizEdge && this.name != "SB-555H(off)") {
			this.name = "SB-555H(off)";
			this.img = ImageMaster.loadImage("img/orbs/SB-555H_N.png");
		}
		if(EnterButtonAction.FaizEdge && this.name != "SB-555H(on)"){
			this.name = "SB-555H(on)";
			this.img = ImageMaster.loadImage("img/orbs/SB-555H.png");
		}
		sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.c.a / 2.0F));
		sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F,
				this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, this.scale, this.angle, 0,
				0, 96, 96, false, false);
		sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.c.a / 2.0F));
		sb.setBlendFunction(770, 1);
		sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale,
				this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, -this.angle, 0, 0, 96, 96,
				false, false);
		sb.setBlendFunction(770, 771);
		this.hb.render(sb);
	}

	public void triggerEvokeAnimation() {
		//CardCrawlGame.sound.play("ORB_DARK_CHANNEL", 0.1F);放Exceed Charge的音效？
	}


	public void playChannelSFX() {
		//CardCrawlGame.sound.play("ORB_DARK_CHANNEL", 0.1F);放Exceed Charge的音效？
	}

	public CustomOrb makeCopy() {
		return new FaizEdge();
	}

	static {
		orbString = CardCrawlGame.languagePack.getOrbString("FaizEdge");
		DESC = orbString.DESCRIPTION;
	}
}