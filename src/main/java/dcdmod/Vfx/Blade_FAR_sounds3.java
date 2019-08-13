package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class Blade_FAR_sounds3 extends AbstractGameEffect {

	private boolean FAR = true;
	
	public Blade_FAR_sounds3() {

		this.duration = 4.0F;//倒数时间
		this.startingDuration = 4.0F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 1.0F) {
			if(FAR) {
				CardCrawlGame.sound.playA("FAR_BLADE", 0.0f);
				FAR = false;
			}
		}
		if(this.duration < 0.0F) {
			this.isDone = true;
			CardCrawlGame.sound.playA("blade_LightningSonic", 0.0f);
		}
	}

	public void render(SpriteBatch sb) {

	}

	public void dispose() {
	}
}