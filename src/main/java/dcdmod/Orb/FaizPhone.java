package dcdmod.Orb;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import basemod.abstracts.CustomOrb;
import dcdmod.Actions.EnterButtonAction;
import dcdmod.Vfx.Faiz_gunattack;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class FaizPhone extends CustomOrb {
	public static final String ORB_ID = "FaizPhone";
	private static final OrbStrings orbString;
	public static final String[] DESC;
	
	public FaizPhone() {
		super(ORB_ID, orbString.NAME, 1, 1, DESC[0], DESC[1], "img/orbs/SB-555P.png");
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

	public void onEndOfTurn() {
		if(EnterButtonAction.FaizPhone) {
			for(int i=0;i<3;i++) {
				AbstractDungeon.actionManager.addToTop(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.0F));
				AbstractDungeon.actionManager.addToTop(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true),new DamageInfo(AbstractDungeon.player, 3, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));	
			}
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_gunattack(AbstractDungeon.player.drawX, AbstractDungeon.player.drawY), 0F));
		}
    }
	
	public void render(SpriteBatch sb) {
		if(!EnterButtonAction.FaizPhone && this.name != "SB-555P(off)") {
			this.name = "SB-555P(off)";
			this.img = ImageMaster.loadImage("img/orbs/SB-555P_N.png");
		}
		if(EnterButtonAction.FaizPhone && this.name != "SB-555P(on)") {
			this.name = "SB-555P(on)";
			this.img = ImageMaster.loadImage("img/orbs/SB-555P.png");
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
		return new FaizPhone();
	}

	static {
		orbString = CardCrawlGame.languagePack.getOrbString("FaizPhone");
		DESC = orbString.DESCRIPTION;
	}
}