package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class Kabuto_FAR_sounds extends AbstractGameEffect {

	private boolean sound =true;
	private boolean Bgm =true;
	
	public Kabuto_FAR_sounds() {

		this.duration = 3F;//倒数时间
		this.startingDuration = 3F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if(this.duration < 1.5F&&Bgm) {
			CardCrawlGame.sound.playA("kabuto_BGM", 0.0f);
			Bgm = false;
		}
		if (this.duration < 0.0F) {
			this.isDone = true;
			CardCrawlGame.sound.playA("FAR_KABUTO", 0.0f);
		}
	}

	public void render(SpriteBatch sb) {
		if(sound) {
			CardCrawlGame.sound.playA("FAR", 0F);
			sound = false;
		}
	}

	public void dispose() {
	}
}