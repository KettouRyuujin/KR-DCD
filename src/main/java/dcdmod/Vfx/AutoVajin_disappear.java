package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Helper.SpecialAutoVajin;
import dcdmod.Patches.AbstractAnimation;
import dcdmod.Patches.AbstractSummonedAnimation;

public class AutoVajin_disappear extends AbstractGameEffect {
	private int stage = 0;

	public AutoVajin_disappear() {
		this.duration = 0.43F;//倒数时间
		this.startingDuration = 0.43F;//持续时间
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if(this.duration <0.21F && stage == 1){
			CardCrawlGame.sound.playA("autovajindisappear", 0.0f);
			stage ++;
		}
		if (this.duration < 0.0F) {
			AbstractSummonedAnimation.clear("AutoVajin");
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) {
		if(stage == 0) {
			SpecialAutoVajin.a = 3;
			SpecialAutoVajin.update();
			stage ++;
		}
	}

	public void dispose() {
	}
}