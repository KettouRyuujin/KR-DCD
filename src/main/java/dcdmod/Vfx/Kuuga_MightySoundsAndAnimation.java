package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Kuuga_MightySoundsAndAnimation extends AbstractGameEffect {

	private int stage;

	public Kuuga_MightySoundsAndAnimation() {
		this.duration = 1.79F;//倒数时间
		this.startingDuration = 1.79F;//持续时间
		this.stage = 0;
	}

	public void update() {
		if(this.duration == this.startingDuration){
			final Decade Decade = (Decade) AbstractDungeon.player;
			Decade.Trickster(143);//切换模型
		}

		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration - 0.1F && stage ==0){
			CardCrawlGame.sound.playA("pegasus_charge", 0.0f);
			stage ++;
		}

		if(this.duration < this.startingDuration - 1.17F && stage == 1){
			CardCrawlGame.sound.playA("kuuga_currentsound", 0.0f);
			stage ++;
		}

		if (this.duration < 0.0F) {
			if(Decade.cf != 8){
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