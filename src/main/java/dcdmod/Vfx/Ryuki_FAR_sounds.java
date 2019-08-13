package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class Ryuki_FAR_sounds extends AbstractGameEffect {

	private boolean FAR = true;
	
	public Ryuki_FAR_sounds() {

		this.duration = 3.5F;//倒数时间
		this.startingDuration = 3.5F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.5F && FAR) {
			CardCrawlGame.sound.playA("FAR_RYUKI", 0.0f);
			FAR = false;
		}
		if (this.duration < 0.0F) {
			this.isDone = true;
			CardCrawlGame.sound.stop("ryuki_BGM");
			CardCrawlGame.sound.playA("ryuki_BGM", 0F);
		}
	}

	public void render(SpriteBatch sb) {

	}

	public void dispose() {
	}
}