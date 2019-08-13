package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class Faiz_axelsounds extends AbstractGameEffect {


	public Faiz_axelsounds() {

		this.duration = 0.5F;//倒数时间
		this.startingDuration = 0.5F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			this.isDone = true;
			CardCrawlGame.sound.playA("axel_sound", 0.0f);
		}
	}

	public void render(SpriteBatch sb) {

	}

	public void dispose() {
	}
}