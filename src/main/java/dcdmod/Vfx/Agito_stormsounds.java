package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class Agito_stormsounds extends AbstractGameEffect {


	public Agito_stormsounds() {

		this.duration = 0.5F;//倒数时间
		this.startingDuration = 0.5F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			this.isDone = true;
			CardCrawlGame.sound.playA("storm", 0.0f);
		}
	}

	public void render(SpriteBatch sb) {

	}

	public void dispose() {
	}
}