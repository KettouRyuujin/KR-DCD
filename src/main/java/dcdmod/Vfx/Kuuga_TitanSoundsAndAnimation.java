package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Kuuga_TitanSoundsAndAnimation extends AbstractGameEffect {

	private int stage;

	public Kuuga_TitanSoundsAndAnimation() {
		this.duration = 1.91F;//倒数时间
		this.startingDuration = 1.91F;//持续时间
		this.stage = 0;
	}

	public void update() {
		if(this.duration == this.startingDuration){
			final Decade Decade = (Decade) AbstractDungeon.player;
			Decade.Trickster(107);//切换模型
		}

		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration - 0.5F && stage == 0){
			CardCrawlGame.sound.playA("titan", 0.0f);
			stage ++;
		}

		if (this.duration < 0.0F) {
			if(Decade.cf != 9){
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(4);//切换模型
			}
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) {
	}

	public void dispose() {
	}
}