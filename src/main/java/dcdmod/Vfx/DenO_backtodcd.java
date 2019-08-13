package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class DenO_backtodcd extends AbstractGameEffect {

	private boolean Start = true;
	private boolean End = true;

	public DenO_backtodcd(float x, float y) {

		this.duration = 2.0F;//倒数时间
		this.startingDuration = 2.0F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 1.5F) {
			if(End) {
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(10);//中途切换模型
				End = false;
			}
		}
		if (this.duration < 0.4F) {
			this.isDone = true;
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(1);//时间结束后切换回原本模型
		}
	}

	public void render(SpriteBatch sb) {

		if(Start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(65);//切换模型
			Start = false;
		}
	}

	public void dispose() {
	}
}