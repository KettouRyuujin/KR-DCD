package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;

public class Kuuga_FAR_kick2 extends AbstractGameEffect {

	private AbstractCreature source, target;
	private Vector2 start;
	private final int damage;
	private int stage;
	private AbstractAnimation kuuga_far_kick = null;

	Kuuga_FAR_kick2(AbstractCreature source, AbstractCreature target, int x, float drawX, float drawY) {
		this.duration = 1.6F;//倒数时间
		this.startingDuration = 1.6F;//持续时间
		this.source = source;
		this.target = target;
		this.damage = x;
		this.start = new Vector2(drawX, drawY);
		this.stage = 0;
	}

	public void update() {
		if(this.duration == this.startingDuration){
			String KUUGA_KICK_ATLAS = "img/char/DCD_Animation/kuuga/FAR/Kuuga_FAR6.atlas";
			String KUUGA_KICK_JSON = "img/char/DCD_Animation/kuuga/FAR/Kuuga_FAR6.json";
			new AbstractAnimation("kuuga_far_kick", KUUGA_KICK_ATLAS, KUUGA_KICK_JSON, 0.8f, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0f);
			kuuga_far_kick = AbstractAnimation.getAnimation("kuuga_far_kick");
			kuuga_far_kick.setMovable(false);
			kuuga_far_kick.state.setAnimation(0, "FAR6", false);
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_SpecialPower(this.target)));
			CardCrawlGame.sound.playA("kuuga_attack", 0.0f);
		}

		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration && stage == 0){
			this.source.drawX -= 1000 * Settings.scale * Gdx.graphics.getDeltaTime();
			kuuga_far_kick.drawX -= 1000 * Settings.scale * Gdx.graphics.getDeltaTime();
		}

		if(this.duration < this.startingDuration -0.37F && stage == 0){
			stage ++;
		}
		else if(this.duration < this.startingDuration -0.96 && stage == 1){
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Boom(this.target , true)));
			AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target,new DamageInfo(this.source, this.damage, DamageInfo.DamageType.NORMAL)));
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KuugaSpecialPower"));
			stage ++;
		}


		if (this.duration < 0.0F) {
			this.isDone = true;
			AbstractAnimation.clear("kuuga_far_kick");
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(4);//时间结束后切换回原本模型
			this.source.drawX = this.start.x;
			this.source.drawY = this.start.y;
		}
	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}